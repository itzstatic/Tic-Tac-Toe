package com.brandon.tictactoe.factory;

import java.io.IOException;
import java.net.SocketException;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.player.RemotePlayer;
import com.brandon.tictactoe.server.LocalServer;
import com.brandon.tictactoe.ui.screen.ScreenChoosePort;
import com.brandon.tictactoe.ui.screen.ScreenCreateGame;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public class HostServerFactory implements ServerFactory {

	private RemotePlayer remote;
	
	@Override
	public Server create(StateMachine screenMachine) throws InstantiationException {
		remote = new RemotePlayer(((ScreenChoosePort) screenMachine.getState("ScreenChoosePort")).getPort());
		try {
			remote.open();
		} catch (SocketException se) {
			throw new InstantiationException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new LocalServer(
			((ScreenCreateGame) screenMachine.getState("ScreenCreateGame")).getGame(),
			new LocalPlayer((ScreenPlayGame) screenMachine.getState("ScreenPlayGame")),
			remote
		);
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
