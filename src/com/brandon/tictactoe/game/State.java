package com.brandon.tictactoe.game;

public enum State {
	EMPTY(0){
		@Override
		public String toString() {
			return "-";
		}
	},
	O(1),
	X(2);
	
	private int id;
	
	private State(int value) {
		this.id = value;
	}
	
	public int serialize() {
		return id;
	}
	
	public static State deserialize(int value) {
		for (State state : State.values()) {
			if (state.serialize() == value) {
				return state;
			}
		}
		return null;
	}
}
