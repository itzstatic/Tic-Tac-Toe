package com.brandon.tictactoe.test;

import java.util.Scanner;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;

public class TestPlayer implements Player {

	private Scanner in;
	private String name;
	private State you;
	
	public TestPlayer(String name, Scanner in) {
		this.name = name;
		this.in = in;
	}
	
	@Override
	public void gameStart(int width, int height, int win, State you) {
		this.you = you;
	}

	@Override
	public void setBoard(State[][] board) {
		printBoard(board);
	}
	
	@Override
	public Move getMove() {
		System.out.print(name + "'s move (" + you + "): ");
		int x = in.nextInt();
		int y = in.nextInt();
		return new Move(x, y);
	}

	@Override
	public void gameOver(State winner) {
		System.out.println("Game over. " + name + " " + (winner == you ? "won" : "lost") + ".");
	}
	
	private void printBoard(State[][] board) {
		printBoundary(board.length + 1);
		for (int y = 0; y < board[0].length; y++) {
			for (int x = 0; x < board.length; x++) {
				System.out.print(" " + board[x][y]);
			}
			System.out.println();
		}
		printBoundary(board.length + 1);
	}

	private void printBoundary(int n) {
		int length = 2 * n;
		for (int i = 0; i < length; i++) {
			System.out.print("_");
		}
		System.out.println();
	}
}
