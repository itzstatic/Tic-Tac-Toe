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
	
	private int value;
	
	private State(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static State get(int value) {
		for (State state : State.values()) {
			if (state.getValue() == value) {
				return state;
			}
		}
		return null;
	}
}
