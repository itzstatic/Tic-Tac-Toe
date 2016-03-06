package com.brandon.tictactoe.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.brandon.tictactoe.Player;
import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.game.Game;
import com.brandon.tictactoe.player.RemotePlayer;
import com.brandon.tictactoe.server.LocalServer;
import com.brandon.tictactoe.server.RemoteServer;


public class Test {

	private Scanner sc;
	private int port;
	
	public Test() {
		sc = new Scanner(System.in);
		port = 63665;
	}
	
	public static void main(String[] args) {
		Test app = new Test();
		new Thread(app::startServer).start();
		new Thread(app::startClient).start();
	}
	
	public void playServer(Server server) {
		while (!server.isGameOver()) {
			server.update();
		}
	}
	
	public void startServer() {
		Game game = new Game(3, 3, 3);
		Player p0 = new TestPlayer("Brandon", sc);
		RemotePlayer p1 = new RemotePlayer(port);
		try {
			p1.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Server server = new LocalServer(game, p0, p1);
		server.start();
		playServer(server);
	}
	public void startClient() {
		Player test = new TestPlayer("Andres", sc);
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		RemoteServer server = new RemoteServer(test);
		try {
			server.open(ip, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.start();
		playServer(server);
	}

}
