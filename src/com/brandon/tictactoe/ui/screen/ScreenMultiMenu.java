package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.ui.LinkedScreen;
import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenMultiMenu extends SwingScreen {
	
	private ServerFactory host, join;
	
	public ScreenMultiMenu(JFrame frame) {
		super(frame);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			gotoState("main");
		});
		JButton btnHost = new JButton("Host Server");
		btnHost.addActionListener(this::hostServer);
		
		JButton btnJoin = new JButton("Join Server");
		btnJoin.addActionListener(this::joinServer);
		
		setLayout(new GridLayout(4, 1));
		add(new JLabel("Multiplayer", SwingConstants.CENTER));
		add(btnHost);
		add(btnJoin);
		add(btnBack);
	}

	private void hostServer(ActionEvent e) {
		ScreenWait sw = (ScreenWait) stateMachine.getState("sw");
		sw.setServerFactory(host);
		LinkedScreen.link(
			this,
			(LinkedScreen) stateMachine.getState("scg"),
			(LinkedScreen) stateMachine.getState("scp"),
			sw,
			(LinkedScreen) stateMachine.getState("spg")
		);
		next();
	}
	
	private void joinServer(ActionEvent e) {
		ScreenWait sw = (ScreenWait) stateMachine.getState("sw");
		sw.setServerFactory(join);
		LinkedScreen.link(
			this,
			(LinkedScreen) stateMachine.getState("scip"),
			(LinkedScreen) stateMachine.getState("scp"),
			sw,
			(LinkedScreen) stateMachine.getState("spg")
		);
		next();
	}
	
	public void setHostFactory(ServerFactory host) {
		this.host = host;
	}
	
	public void setJoinFactory(ServerFactory join) {
		this.join = join;
	}
}
