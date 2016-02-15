package com.brandon.tictactoe.game.loop;

import com.brandon.tictactoe.game.LoopStrategy;

public class IncreasingLoopStrategy implements LoopStrategy {

	private int range;
	
	public IncreasingLoopStrategy(int r) {
		range = r;
	}
	
	@Override
	public int init() {
		return -range + 1;
	}

	@Override
	public boolean condition(int n) {
		return n <= range - 1;
	}

	@Override
	public int increment(int n) {
		return n + 1;
	}
	
}
