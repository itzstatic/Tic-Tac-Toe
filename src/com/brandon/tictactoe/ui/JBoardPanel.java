package com.brandon.tictactoe.ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public class JBoardPanel extends JPanel {
	
	private JButton[][] btnSquares;
	private volatile Move move;
	
	public JBoardPanel(ScreenPlayGame scrnPlay, int dim) {
		btnSquares = new JButton[dim][dim];
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				btnSquares[x][y] = newJButton(x, y);
			}
		}
	}
	
	private JButton newJButton(int x, int y) {
		JButton button = new JButton();
		button.addActionListener(e -> move = new Move(x, y));
		return button;
	}
	
	public Move getMove() {
		while (move == null);
		Move m = move;
		move = null;
		return m;
	}
	
}
