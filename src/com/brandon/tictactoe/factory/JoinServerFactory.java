package com.brandon.tictactoe.factory;

import java.io.IOException;
import java.net.SocketException;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.server.RemoteServer;
import com.brandon.tictactoe.ui.screen.ScreenChooseIP;
import com.brandon.tictactoe.ui.screen.ScreenChoosePort;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public class JoinServerFactory implements ServerFactory {

	private RemoteServer remote;
	
	@Override
	public Server create(StateMachine screenMachine) throws InstantiationException {
		remote = new RemoteServer(new LocalPlayer((ScreenPlayGame) screenMachine.getState("ScreenPlayGame")));
		try {
			remote.open(
				((ScreenChooseIP) screenMachine.getState("ScreenChooseIP")).getInetAddress(),
				((ScreenChoosePort) screenMachine.getState("ScreenChoosePort")).getPort()
			);
		} catch (SocketException se) {
			throw new InstantiationException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return remote;
	}
	
	@Override
	public void destroy() {
		try {
			remote.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
