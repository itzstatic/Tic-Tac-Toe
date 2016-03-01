package com.brandon.tictactoe.game;

import com.brandon.tictactoe.game.loop.ConstantLoopStrategy;
import com.brandon.tictactoe.game.loop.DecreasingLoopStrategy;
import com.brandon.tictactoe.game.loop.IncreasingLoopStrategy;


public class Game {
	
	private State[][] board;
	private int win;
	private State winner;
	private int moves;
	
	private LoopStrategy increase, constant, decrease;
	
	public Game(int width, int height, int win) {
		int min = Math.min(width, height);
		if (win > min) {
			throw new IllegalArgumentException(win + " > " + min);
		}
		if (min < 1) {
			throw new IllegalArgumentException(min + " < " + 1);
		}
		
		this.win = win;
		winner = null;
		moves = 0;
		
		board = new State[width][height];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = State.EMPTY;
			}
		}
		
		increase = new IncreasingLoopStrategy(win);
		decrease = new DecreasingLoopStrategy(win);
		constant = new ConstantLoopStrategy(0);
	}
	
	public int getWidth() {
		return board.length;
	}
	public int getHeight() {
		return board[0].length;
	}
	public int getWin() {
		return win;
	}
	
	public boolean isGameOver() {
		return winner != null;
	}
	
	public boolean isEmpty(Move square) {
		return board[square.getX()][square.getY()] == State.EMPTY;
	}
	public boolean isInBounds(Move square) {
		int x = square.getX();
		int y = square.getY();
		return 0 <= x && x < board.length &&
				0 <= y && y < board[0].length;
	}
	
	public void setMove(Move square, State state) {
		board[square.getX()][square.getY()] = state;
		
		checkRow(square);
		checkColumn(square);
		checkDiag(square);
		checkAntiDiag(square);
		
		if (winner == null && ++moves == board.length * board[0].length) {
			winner = State.EMPTY;
		}
	}
	
	public State getWinner() {
		return winner;
	}
	
	public State[][] getBoard() {
		State[][] board = new State[this.board.length][this.board[0].length];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = this.board[x][y];
			}
		}
		return board;
	}
	
	private void check(LoopStrategy loopX, LoopStrategy loopY, Move move) {
		if (isGameOver()) {
			return;
		}
		
		State winState = null;
		int winCount = 0;
		
		for (int dx = loopX.init(), dy = loopY.init();
			loopX.condition(dx) && loopY.condition(dy);
			dx = loopX.increment(dx), dy = loopY.increment(dy)
		) {
			int x = move.getX() + dx;
			int y = move.getY() + dy;
			
			if (!isInBounds(new Move(x, y))) {
				continue;
			}
			
			if (board[x][y] == State.EMPTY) {
				winState = null;
				winCount = 0;
			} else if (board[x][y] == winState){
				winCount++;
			} else {
				winState = board[x][y];
				winCount = 1;
			}
			
			if (winCount == win) {
				winner = winState;
				return;
			}
		}
	}
	
	
	private void checkRow(Move square) {
		check(increase, constant, square);
	}
	
	private void checkColumn(Move square) {
		check(constant, increase, square);
	}
	
	private void checkDiag(Move square) {
		check(increase, increase, square);
	}

	private void checkAntiDiag(Move square) {
		check(increase, decrease, square);
	}
}
