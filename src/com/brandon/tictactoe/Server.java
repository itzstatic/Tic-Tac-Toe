
package com.brandon.tictactoe;



public interface Server {
	int MOVE = 0;
	int GAME_OVER = 1;
	int BOARD = 2;
	
	void update();
	boolean isGameOver();
	void setGameOver();
}
