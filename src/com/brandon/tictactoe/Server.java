
package com.brandon.tictactoe;



public interface Server {
	int MOVE = 0;
	int GAME_OVER = 1;
	int BOARD = 2;
	
	void start();
	void update();
	boolean isGameOver();
}
