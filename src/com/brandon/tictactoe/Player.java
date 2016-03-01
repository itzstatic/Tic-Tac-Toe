package com.brandon.tictactoe;

import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;



public interface Player {
	void gameStart(int width, int height, int win, State you);
	void setBoard(State[][] board);
	Move getMove();
	void gameOver(State winner);
}
