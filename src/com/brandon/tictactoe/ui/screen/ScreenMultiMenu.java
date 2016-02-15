package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.brandon.tictactoe.Flow;
import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.flow.FlowGetScreen;
import com.brandon.tictactoe.flow.Wrapper;
import com.brandon.tictactoe.game.Game;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.player.RemotePlayer;
import com.brandon.tictactoe.server.LocalServer;
import com.brandon.tictactoe.server.RemoteServer;
import com.brandon.tictactoe.ui.SwingScreen;



public class ScreenMultiMenu extends SwingScreen {
	
	public ScreenMultiMenu(JFrame frame) {
		super(frame);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			super.back();
		});
		JButton btnCreate = new JButton("Create Server");
		btnCreate.addActionListener(e -> {
			createServer(frame);
		});
		JButton btnJoin = new JButton("Join Server");
		btnJoin.addActionListener(e -> {
			joinServer(frame);
		});
		
		setLayout(new GridLayout(4, 1));
		add(new JLabel("Multiplayer"));
		add(btnCreate);
		add(btnJoin);
		add(btnBack);
	}

	private void createServer(JFrame frame) {
		Wrapper<Game> game = new Wrapper<>();
		Wrapper<Integer> port = new Wrapper<>();
		
		Flow flow = new Flow();
		flow.add(new FlowGetScreen<>(this, new ScreenCreateGame(frame), game));
		flow.add(new FlowGetScreen<>(this, new ScreenChoosePort(frame), port));
		flow.add(() -> {
			ScreenPlayGame spg = new ScreenPlayGame(frame);
			Server server = new LocalServer(
					game.get(),
					new LocalPlayer(spg),
					new RemotePlayer(port.get())
			);
			spg.setServer(server);
			super.setScreen(spg);
			return true;
		});
		new Thread(flow).start();
	}
	
	private void joinServer(JFrame frame) {
		Wrapper<InetAddress> ip = new Wrapper<>();
		Wrapper<Integer> port = new Wrapper<>();
		
		Flow flow = new Flow();
		flow.add(new FlowGetScreen<>(this, new ScreenChooseIP(frame), ip));
		flow.add(new FlowGetScreen<>(this, new ScreenChoosePort(frame), port));
		flow.add(() -> {
			ScreenPlayGame spg = new ScreenPlayGame(frame);
			Server server = new RemoteServer(
					ip.get(), 
					port.get().intValue(), 
					new LocalPlayer(spg)
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
