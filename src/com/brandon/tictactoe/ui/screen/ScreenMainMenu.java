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

public class ScreenMainMenu extends SwingScreen {
	
	private ServerFactory single, hotseat;
	
	public ScreenMainMenu(JFrame frame) {
		super(frame);
		
		JButton btnSingle = new JButton("Singleplayer");
		btnSingle.addActionListener(this::playSingleplayer);

		JButton btnMulti = new JButton("Multiplayer");
		btnMulti.addActionListener(e -> {
			gotoState("multi");
		});
		
		JButton btnHotseat = new JButton("Hotseat");
		btnHotseat.addActionListener(this::playHotseat);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> {
			System.exit(0);
		});
		
		setLayout(new GridLayout(5, 1));
		add(new JLabel("Tic Tac Toe", SwingConstants.CENTER));
		add(btnSingle);
		add(btnMulti);
		add(btnHotseat);
		add(btnExit);
	}
	
	private void playSingleplayer(ActionEvent e) {
		((ScreenWait) stateMachine.getState("sw")).setServerFactory(single);
		play();
	}
	
	private void playHotseat(ActionEvent e) {
		((ScreenWait) stateMachine.getState("sw")).setServerFactory(hotseat);
		play();
	}
	
	private void play() {
		LinkedScreen.link(
			this,
			(LinkedScreen) stateMachine.getState("scg"),
			(LinkedScreen) stateMachine.getState("sw"),
			(LinkedScreen) stateMachine.getState("spg")
		);
		next();
	}
	
	public void setSingleFactory(ServerFactory single) {
		this.single = single;
	}
	
	public void setHotseatFactory(ServerFactory hotseat) {
		this.hotseat = hotseat;
	}
}
