package com.brandon.tictactoe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.brandon.tictactoe.game.State;


public class JSquarePanel extends JButton {
	
	public JSquarePanel(JBoardPanel pnlBoard, int x, int y) {
		setText(State.EMPTY.toString());
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlBoard.setMove(x, y);
			}
		});
	}
	
	public void setState(State state) {
		setText(state.toString());
		setEnabled(state == State.EMPTY);
	}
}
