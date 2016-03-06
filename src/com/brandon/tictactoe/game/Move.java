package com.brandon.tictactoe.game;

import com.brandon.tictactoe.game.Move;

public class Move {
	private int x;
	private int y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		
		if (o instanceof Move) {
			Move move = (Move) o;
			return move.x == x && move.y == y;
		} else {
			return false;
		}
	}
}
