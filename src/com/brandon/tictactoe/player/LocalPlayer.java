package com.brandon.tictactoe.player;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

/**
 * Represents the human clicking on the UI on this machine.
 * @author Brandon
 *
 */
public class LocalPlayer implements Player {

	private ScreenPlayGame scrnPlay;
	
	public LocalPlayer(ScreenPlayGame scrnPlay) {
		this.scrnPlay = scrnPlay;
	}
	
	@Override
	public void gameStart(int width, int height, int win, State you) {
		
	}
	
	@Override
	public void setBoard(State[][] board) {
		scrnPlay.setBoard(board);
	}
	
	@Override
	public Move getMove() {
		scrnPlay.setEnabled(true);
		Move move = scrnPlay.getMove();
		scrnPlay.setEnabled(false);
		return move;
	}

	@Override
	public void gameOver(State winner) {
		
	}
}
