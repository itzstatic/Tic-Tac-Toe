package com.brandon.tictactoe.server;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;

/**
 * Translates from a Player's input to another server over a network.
 * @author Brandon
 *
 */
public class RemoteServer implements Server, Closeable {
	
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	
	private State winner;
	private int width;
	private int height;
	
	private Player player;
	
	public RemoteServer(InetAddress ip, int port, Player player) {
		this.player = player;
		try {
			socket = new Socket(ip, port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			width = in.read();
			height = in.read();
			int win = in.read();
			State state = State.get(in.read());
			player.gameStart(width, height, win, state);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private State[][] readBoard() throws IOException{
		State[][] board = new State[width][height];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = State.get(in.read());
			}
		}
		return board;
	}
	
	@Override
	public void update() {
		try {
			switch(in.read()) {
			case MOVE:
				Move move = player.getMove();
				out.write(move.getX());
				out.write(move.getY());
				out.flush();
				break;
			case BOARD:
				player.setBoard(readBoard());
				break;
			case GAME_OVER:
				winner = State.get(in.read());
				player.gameOver(winner);
				out.write(0);
				out.flush();
				close();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		out.close();
		in.close();
		socket.close();
	}

	@Override
	public boolean isGameOver() {
		return winner != null;
	}
}
