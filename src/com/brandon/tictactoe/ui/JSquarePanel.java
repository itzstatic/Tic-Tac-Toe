package com.brandon.tictactoe.ui;

import javax.swing.JButton;

import com.brandon.tictactoe.game.State;


public class JSquarePanel extends JButton {
	
	public JSquarePanel(JBoardPanel pnlBoard, int x, int y) {
		setText(State.EMPTY.toString());
		addActionListener(e -> pnlBoard.setMove(x, y));
	}
	
	public void setState(State state) {
		setText(state.toString());
		setEnabled(state == State.EMPTY);
	}
}
