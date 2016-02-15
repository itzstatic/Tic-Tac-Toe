package com.brandon.tictactoe.player;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;
/**
 * Represents a player over a network, from the perspective of a Server.
 * @author Brandon
 *
 */
public class RemotePlayer implements Player, Closeable {

	private ServerSocket serverSocket;
	private Socket socket;
	
	private InputStream in;
	private OutputStream out;
	
	public RemotePlayer(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeBoard(State[][] board) throws IOException {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				out.write(board[x][y].getValue());
			}
		}
	}
	
	private Move readMove() throws IOException {
		int x = in.read();
		int y = in.read();
		return new Move(x, y);
	}
	
	@Override
	public void gameStart(int width, int height, int win, State you) {
		try {
			socket = serverSocket.accept();
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			out.write(width);
			out.write(height);
			out.write(win);
			out.write(you.getValue());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setBoard(State[][] board) {
		try {
			out.write(Server.BOARD);
			writeBoard(board);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Move getMove() {
		try {
			out.write(Server.MOVE);
			out.flush();
			return readMove();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public void gameOver(State winner) {
		try {
			out.write(Server.GAME_OVER);
			out.write(winner.getValue());
			out.flush();
			in.read();
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		out.close();
		in.close();
		socket.close();
		serverSocket.close();
	}
	
}
