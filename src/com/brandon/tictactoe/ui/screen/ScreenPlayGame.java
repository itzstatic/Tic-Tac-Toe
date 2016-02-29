package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.teamresistance.util.state.StateTransition;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.ui.JBoardPanel;
import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenPlayGame extends SwingScreen {

	private JBoardPanel pnlBoard;
	private JLabel lblState;
	private JLabel lblWin;
	
	private Server server;
	private ServerFactory serverFactory;
	private boolean isGameOver;
	
	public ScreenPlayGame(JFrame frame) {
		super(frame);
		
		JPanel pnlControls = new JPanel();
		pnlBoard = new JBoardPanel();
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(e -> {
			server.gameOver();
			gotoState("ScreenMainMenu");
		});
		lblState = new JLabel();
		lblWin = new JLabel();
		
		pnlControls.add(btnMenu);
		pnlControls.add(new JLabel("Player: "));
		pnlControls.add(lblState);
		pnlControls.add(new JLabel("To win: "));
		pnlControls.add(lblWin);
		
		setLayout(new GridLayout(2, 1));
		add(pnlBoard);
		add(pnlControls);
	}
	
	public void gameStart(int width, int height, int win) {
		pnlBoard.gameStart(width, height);
		lblWin.setText(Integer.toString(win));
	}
	
	public void setBoard(State[][] board) {
		pnlBoard.setBoard(board);
	}
	public Move getMove(State state) {
		lblState.setText(state.toString());
		pnlBoard.setEnabled(true);
		Move move = pnlBoard.getMove();
		pnlBoard.setEnabled(false);
		return move;
	}
	public void gameOver(State winner) {
		if (isGameOver) {
			return;
		}
		isGameOver = true;
		
		String message;
		if (winner == State.EMPTY) {
			message = "It's a tie!";
		} else {
			message = winner.toString() + " wins!";
		}
		JOptionPane.showMessageDialog(
			getFrame(), 
			message,
			"Game over", 
			JOptionPane.INFORMATION_MESSAGE
		);
		gotoState("ScreenMainMenu");
	}
	public void setServerFactory(ServerFactory factory) {
		serverFactory = factory;
	}
	
	@Override
	public void onEntry(StateTransition e) {
		super.onEntry(e);
		server = serverFactory.createServer(this, stateMachine);
	}
	
	@Override
	public void update() {
		if (server == null) {
			return;
		}
		if (!server.isGameOver()) {
			server.update();
		}
	}
}
