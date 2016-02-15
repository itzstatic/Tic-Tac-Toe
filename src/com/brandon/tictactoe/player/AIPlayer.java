package com.brandon.tictactoe.player;

import java.util.Random;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;

public class AIPlayer implements Player {

	private Random rng;
	private int width;
	private int height;
	
	public AIPlayer() {
		rng = new Random();
	}
	
	@Override
	public void gameStart(int width, int height, int win, State you) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void setBoard(State[][] board) {
		
	}
	
	@Override
	public Move getMove() {
		return new Move(rng.nextInt(width), rng.nextInt(height));
	}

	@Override
	public void gameOver(State winner) {
		System.out.println("AI senses game over.");
	}
}
