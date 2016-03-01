package com.brandon.tictactoe.ui;

import javax.swing.JButton;

import com.brandon.tictactoe.game.State;


public class JSquarePanel extends JButton {
	
	public JSquarePanel(JBoardPanel pnlBoard, int x, int y) {
		setState(State.EMPTY);
		addActionListener(e -> {
			if (getState() == State.EMPTY) {
				pnlBoard.setMove(x, y);
			}
		});
	}
	
	public void setState(State state) {
		setText(state.toString());
		setEnabled(state == State.EMPTY);
	}
	
	public State getState() {
		for (State state : State.values()) {
			if (state.toString().equals(getText())) {
				return state;
			}
		}
		return null;
	}
}
