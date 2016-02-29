package com.brandon.tictactoe.ui;

import org.teamresistance.util.state.State;

public abstract class LinkedScreen extends State {

	private String previous;
	private String next;

	public void setPrevious(State previous) {
		this.previous = previous.getName();
	}

	public void setNext(State next) {
		this.next = next.getName();
	}

	protected void previous() {
		gotoState(previous);
	}
	
	protected void next() {
		gotoState(next);
	}
	
	public static void link(LinkedScreen... screens) {
		for (int i = 0; i < screens.length; i++) {
			if (i != 0) {
				screens[i].setPrevious(screens[i - 1]);
			}
			
			if (i != screens.length - 1) {
				screens[i].setNext(screens[i + 1]);
			}
		}
	}

}
