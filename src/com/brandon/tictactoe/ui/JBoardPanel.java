package com.brandon.tictactoe.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

@SuppressWarnings("serial")
public class JBoardPanel extends JPanel {
	
	private ScreenPlayGame spg;
	private JSquarePanel[][] squares;
	
	public JBoardPanel(ScreenPlayGame spg) {
		this.spg = spg;
	}

	public void gameStart(int width, int height) {
		squares = new JSquarePanel[width][height];
		setLayout(new GridLayout(height, width));
		
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
	
	public void gameOver() {
		for (int y = 0; y < squares[0].length; y++) {
			for (int x = 0; x < squares.length; x++) {
				remove(squares[x][y]);
			}	
		}
	}
	
	public void setMove(int x, int y) {
		spg.setMove(x, y);
	}
}
