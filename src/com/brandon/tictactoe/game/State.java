package com.brandon.tictactoe.game;

public enum State {
	EMPTY(0){
		@Override
		public String toString() {
			return " ";
		}
	},
	O(1),
	X(2);
	
	private int id;
	
	private State(int id) {
		if (id == -1) {
			throw new IllegalArgumentException(Integer.toString(id));
		}
		this.id = id;
	}
	
	public static State not(State state) {
		switch (state) {
		case X: return O;
		case O: return X;
		default: throw new IllegalArgumentException(state.toString());
		}
	}
	
	public static State deserialize(int id) {
		if (id == -1) {
			return null;
		}
		for (State state : State.values()) {
			if (state.id == id) {
				return state;
			}
		}
		throw new IllegalArgumentException(Integer.toString(id));
	}
	
	public static int serialize(State state) {
		if (state == null) {
			return -1;
		} else {
			return state.id;
		}
	}
}
