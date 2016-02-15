package com.brandon.tictactoe.game.loop;

import com.brandon.tictactoe.game.LoopStrategy;

public class DecreasingLoopStrategy implements LoopStrategy {

	private int range;
	
	public DecreasingLoopStrategy(int r) {
		range = r;
	}

	@Override
	public int init() {
		return range - 1;
	}

	@Override
	public boolean condition(int n) {
		return -range + 1 <= n;
	}

	@Override
	public int increment(int n) {
		return n - 1;
	}

}
