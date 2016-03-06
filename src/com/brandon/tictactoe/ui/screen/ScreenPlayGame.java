package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.teamresistance.util.state.StateTransition;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.game.Move;
import com.brandon.tictactoe.game.State;
import com.brandon.tictactoe.ui.JBoardPanel;
import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenPlayGame extends SwingScreen {

	private JBoardPanel pnlBoard;
	private JLabel lblState;
	private JLabel lblWin;
	
	private Server server;
	private boolean isRunning;
	private volatile Move move;
	
	public ScreenPlayGame(JFrame frame) {
		super(frame);
		
		JPanel pnlControls = new JPanel();
		pnlBoard = new JBoardPanel(this);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(e -> {
			move = new Move(-1, -1);
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
		if (isRunning) {
			return;
		}
		isRunning = true;
		pnlBoard.gameStart(width, height);
		lblWin.setText(Integer.toString(win));
	}
	public void setBoard(State[][] board) {
		pnlBoard.setBoard(board);
	}
	public Move getMove(State state) {
		lblState.setText(state.toString());
		pnlBoard.setEnabled(true);
		
		while(move == null);
		Move move = this.move;
		this.move = null;
		
		pnlBoard.setEnabled(false);
		lblState.setText(State.not(state).toString());
		return move;
	}
	public void gameOver(State winner) {
		if (!isRunning) {
			return;
		}
		isRunning = false;
		showGameOverMessage(winner);
		pnlBoard.gameOver();
		gotoState("ScreenMainMenu");
	}
	
	private void showGameOverMessage(State winner) {
		String message;
		if (winner == null) {
			message = "Forfeit.";
		}else if (winner == State.EMPTY) {
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
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	public void setMove(int x, int y) {
		this.move = new Move(x, y);
	}
	
	@Override
	public void onEntry(StateTransition e) {
		super.onEntry(e);
		server.start();
	}
	
	@Override
	public void update() {
		if (isRunning && !server.isGameOver()) {
			server.update();
		}
	}
}
