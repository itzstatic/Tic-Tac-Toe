package com.brandon.tictactoe.ui.screen;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.brandon.tictactoe.Flow;
import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.flow.FlowGetScreen;
import com.brandon.tictactoe.flow.Wrapper;
import com.brandon.tictactoe.game.Game;
import com.brandon.tictactoe.player.AIPlayer;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.server.LocalServer;
import com.brandon.tictactoe.ui.SwingScreen;


public class ScreenMainMenu extends SwingScreen {
	
	public ScreenMainMenu(JFrame frame) {
		super(frame);
		
		JButton btnSingle = new JButton("Singleplayer");
		btnSingle.setPreferredSize(new Dimension(200, 60));
		btnSingle.addActionListener(e -> {
			playLocalGame(frame, new ScreenPlayGame(frame), new AIPlayer());
		});

		JButton btnMulti = new JButton("Multiplayer");
		btnMulti.setPreferredSize(new Dimension(200, 60));
		btnMulti.addActionListener(e -> {
			super.setScreen(new ScreenMultiMenu(frame));
		});
		
		JButton btnHotseat = new JButton("Hotseat");
		btnHotseat.setPreferredSize(new Dimension(200, 60));
		btnHotseat.addActionListener(e -> {
			ScreenPlayGame spg = new ScreenPlayGame(frame);
			playLocalGame(frame, spg, new LocalPlayer(spg));
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setPreferredSize(new Dimension(200, 60));
		btnExit.addActionListener(e -> {
			System.exit(0);
		});
		
		JLabel lblTitle = new JLabel("Tic Tac Toe");
		lblTitle.setPreferredSize(new Dimension(100, 60));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new GridLayout(5, 1));
		add(lblTitle);
		add(btnSingle);
		add(btnMulti);
		add(btnHotseat);
		add(btnExit);
	}
	
	private void playLocalGame(JFrame frame, ScreenPlayGame spg, Player opponent) {
		Wrapper<Game> game = new Wrapper<>();
		Flow flow = new Flow();
		flow.add(new FlowGetScreen<>(this, new ScreenCreateGame(frame), game));
		flow.add(() -> {
			Server server = new LocalServer(
				game.get(),
				new LocalPlayer(spg),
				opponent
			);
			spg.setServer(server);
			setScreen(spg);
			return true;
		});
		new Thread(flow).start();
	}

	@Override
	public void update() {
		
	}

	
}
