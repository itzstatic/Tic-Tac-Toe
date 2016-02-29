package com.brandon.tictactoe.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;

public class JBoardPanel extends JPanel {
	
	private JSquarePanel[][] squares;
	private volatile Move move;

	public void gameStart(int width, int height) {
		squares = new JSquarePanel[width][height];
		setLayout(new GridLayout(height, width, 5, 5));
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				squares[x][y] = new JSquarePanel(this, x, y);
				add(squares[x][y]);
			}	
		}
	}
	
	public void setBoard(State[][] board) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				squares[x][y].setState(board[x][y]);
			}
		}
	}
	
	public Move getMove() {
		while (move == null);
		Move move = this.move;
		this.move = null;
		return move;
	}
	
	void setMove(int x, int y) {
		move = new Move(x, y);
	}
}
