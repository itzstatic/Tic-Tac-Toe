package com.brandon.tictactoe;

import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.game.Move;



public interface Player {
	void gameStart(int width, int height, int win, State you);
	void setBoard(State[][] board);
	Move getMove();
	void gameOver(State winner);
}
