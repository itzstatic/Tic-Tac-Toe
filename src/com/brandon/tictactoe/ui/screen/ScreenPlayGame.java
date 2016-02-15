package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.ui.JBoardPanel;
import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenPlayGame extends SwingScreen {

	private JBoardPanel pnlBoard;
	private JPanel pnlControls;
	
	private JButton btnMenu;
	
	private Server server;
	
	public ScreenPlayGame(JFrame frame) {
		super(frame);
		
		pnlControls = new JPanel();
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(e -> {
			super.setScreen(new ScreenMainMenu(frame));
		});
		pnlControls.add(btnMenu);
		
		setLayout(new GridLayout(2, 1));
		add(pnlBoard);
		add(pnlControls);
	}
	
	public void setServer(Server server) {
		this.server = server;
	}

	public void setBoard(State[][] board) {
		for (int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[0].length; y++) {
				
			}
		}
	}
	
	public Move getMove() {
		
	}
	
	public void setEnabled(boolean b) {
		pnlBoard.setEnabled(b);
	}
	
	@Override
	public void update() {
		if (server.isGameOver()) {
			
		} else {
			server.update();
		}
	}
}
