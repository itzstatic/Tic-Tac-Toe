package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.factory.HostServerFactory;
import com.brandon.tictactoe.factory.JoinServerFactory;
import com.brandon.tictactoe.ui.LinkedScreen;
import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenMultiMenu extends SwingScreen {
	
	private ServerFactory host, join;
	
	public ScreenMultiMenu(JFrame frame) {
		super(frame);
		
		host = new HostServerFactory();
		join = new JoinServerFactory();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			gotoState("ScreenMainMenu");
		});
		JButton btnHost = new JButton("Host Server");
		btnHost.addActionListener(this::hostServer);
		
		JButton btnJoin = new JButton("Join Server");
		btnJoin.addActionListener(this::joinServer);
		
		setLayout(new GridLayout(4, 1));
		add(new JLabel("Multiplayer"));
		add(btnHost);
		add(btnJoin);
		add(btnBack);
	}

	private void hostServer(ActionEvent e) {
		ScreenPlayGame spg = (ScreenPlayGame) stateMachine.getState("ScreenPlayGame");
		spg.setServerFactory(host);
		LinkedScreen.link(
			this,
			(LinkedScreen) stateMachine.getState("ScreenCreateGame"),
			(LinkedScreen) stateMachine.getState("ScreenChoosePort"),
			spg
		);
		next();
	}
	
	private void joinServer(ActionEvent e) {
		ScreenPlayGame spg = (ScreenPlayGame) stateMachine.getState("ScreenPlayGame");
		spg.setServerFactory(join);
		LinkedScreen.link(
			this,
			(LinkedScreen) stateMachine.getState("ScreenChooseIP"),
			(LinkedScreen) stateMachine.getState("ScreenChoosePort"),
			spg
		);
		next();
	}
}
