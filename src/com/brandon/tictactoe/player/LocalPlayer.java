package com.brandon.tictactoe.player;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

/**
 * Represents the human clicking on the UI on this machine.
 * @author Brandon
 *
 */
public class LocalPlayer implements Player {

	private ScreenPlayGame scrnPlay;
	private State state;
	
	public LocalPlayer(ScreenPlayGame spg) {
		scrnPlay = spg;
	}
	
	@Override
	public void gameStart(int width, int height, int win, State you) {
		state = you;
		scrnPlay.gameStart(width, height, win);
	}
	
	@Override
	public void setBoard(State[][] board) {
		scrnPlay.setBoard(board);
	}
	
	@Override
	public Move getMove() {
		return scrnPlay.getMove(state);
	}

	@Override
	public void gameOver(State winner) {
		scrnPlay.gameOver(winner);
	}
}
