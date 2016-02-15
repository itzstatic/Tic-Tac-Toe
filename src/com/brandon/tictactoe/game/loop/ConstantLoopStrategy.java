package com.brandon.tictactoe.game.loop;

import com.brandon.tictactoe.game.LoopStrategy;

public class ConstantLoopStrategy implements LoopStrategy {

	private int value;
	
	public ConstantLoopStrategy(int v) {
		value = v;
	}

	@Override
	public int init() {
		return value;
	}

	@Override
	public boolean condition(int n) {
		return true;
	}

	@Override
	public int increment(int n) {
		return n;
	}

}
