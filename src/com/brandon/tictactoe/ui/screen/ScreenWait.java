package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.teamresistance.util.state.StateTransition;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.ui.SwingScreen;
import com.brandon.tictactoe.ui.WaitApplication;

public class ScreenWait extends SwingScreen {

	private Server server;
	private ServerFactory serverFactory;
	private WaitApplication wait;
	private JLabel lblDots;
	
	public ScreenWait(JFrame frame) {
		super(frame);
		wait = new WaitApplication(this);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this::onBack);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(this::onMenu);
		
		JPanel pnlControls = new JPanel();
		pnlControls.add(btnBack);
		pnlControls.add(btnMenu);
		
		JLabel lblWait = new JLabel("Setting up game");
		lblDots = new JLabel();
		
		JPanel pnlWait = new JPanel();
		pnlWait.add(lblWait);
		pnlWait.add(lblDots);
		
		setLayout(new GridLayout(3, 1));
		add(new JLabel("Waiting", SwingConstants.CENTER));
		add(pnlWait);
		add(pnlControls);
		
	}

	public void setDots(String dots) {
		lblDots.setText(dots);
	}
	
	private void onBack(ActionEvent e) {
		serverFactory.destroy();
		previous();
	}
	
	private void onMenu(ActionEvent e) {
		serverFactory.destroy();
		gotoState("ScreenMainMenu");
	}

	private void createServer() {
		try {
			server = serverFactory.create(stateMachine);
		} catch (InstantiationException ie) {
			return;
		}
		((ScreenPlayGame) stateMachine.getState("ScreenPlayGame")).setServer(server);
		next();
	}
	
	public void setServerFactory(ServerFactory factory) {
		serverFactory = factory;
	}
	
	@Override
	public void onEntry(StateTransition e) {
		super.onEntry(e);
		new Thread(wait).start();
		new Thread(this::createServer).start();
	}
	
}
