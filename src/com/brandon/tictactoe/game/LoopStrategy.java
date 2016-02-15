package com.brandon.tictactoe.game;

public interface LoopStrategy {
	int init();
	boolean condition(int n);
	int increment(int n);
}
