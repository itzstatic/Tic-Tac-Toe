package com.brandon.tictactoe.server;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.game.Game;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;

/**
 * Runs the game rules on this machine.
 * @author Brandon
 *
 */
public class LocalServer implements Server {

	private Game game;
	private Player[] players;
	private State[] states;
	private int current;
	
	public LocalServer(Game game, Player playerO, Player playerX) {
		this.game = game;
		players = new Player[]{ playerO, playerX };
		states = new State[]{ State.O, State.X };
		current = 0;
		
		int width = game.getWidth();
		int height = game.getHeight();
		int win = game.getWin();
		players[0].gameStart(width, height, win, states[0]);
		players[1].gameStart(width, height, win, states[1]);
	}
	
	@Override
	public void update() {
		Move move;
		State[][] board = game.getBoard();
		
		//update both player's boards
		players[0].setBoard(board);
		players[1].setBoard(board);
		
		//get a legal move
		do {
			move = players[current].getMove();
		} while (!game.isInBounds(move) || !game.isEmpty(move));
		
		//make a move with the state associated with the player
		game.setMove(move, states[current]);
	
		//game over check
		if (game.isGameOver()) {
			State winner = game.getWinner();
			board = game.getBoard();
			players[0].setBoard(board);
			players[0].gameOver(winner);
			players[1].setBoard(board);
			players[1].gameOver(winner);
			return;
		}
		
		//invert current player
		current = Math.abs(current - 1);
	}

	@Override
	public boolean isGameOver() {
		return game.isGameOver();
	}
}
