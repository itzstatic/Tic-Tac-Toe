package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.factory.HotseatServerFactory;
import com.brandon.tictactoe.factory.SingleplayerServerFactory;
import com.brandon.tictactoe.ui.LinkedScreen;
import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenMainMenu extends SwingScreen {
	
	private ServerFactory single, hotseat;
	
	public ScreenMainMenu(JFrame frame) {
		super(frame);
		
		single = new SingleplayerServerFactory();
		hotseat = new HotseatServerFactory();
		
		JButton btnSingle = new JButton("Singleplayer");
		btnSingle.addActionListener(this::playSingleplayer);

		JButton btnMulti = new JButton("Multiplayer");
		btnMulti.addActionListener(e -> {
			gotoState("ScreenMultiMenu");
		});
		
		JButton btnHotseat = new JButton("Hotseat");
		btnHotseat.addActionListener(this::playHotseat);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> {
			System.exit(0);
		});
		
		JLabel lblTitle = new JLabel("Tic Tac Toe");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new GridLayout(5, 1));
		add(lblTitle);
		add(btnSingle);
		add(btnMulti);
		add(btnHotseat);
		add(btnExit);
	}
	
	private void playSingleplayer(ActionEvent e) {
		((ScreenWait) stateMachine.getState("ScreenWait")).setServerFactory(single);
		play();
	}
	
	private void playHotseat(ActionEvent e) {
		((ScreenWait) stateMachine.getState("ScreenWait")).setServerFactory(hotseat);
		play();
	}
	
	private void play() {
		LinkedScreen.link(
			this,
			(LinkedScreen) stateMachine.getState("ScreenCreateGame"),
			(LinkedScreen) stateMachine.getState("ScreenWait"),
			(LinkedScreen) stateMachine.getState("ScreenPlayGame")
		);
		next();
	}
	
}
