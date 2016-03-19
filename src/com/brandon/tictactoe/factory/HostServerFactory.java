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
	
	private ScreenCreateGame scg;
	private ScreenChoosePort scp;
	private ScreenPlayGame spg;
	
	public HostServerFactory(StateMachine screenMachine) {
		this.scg = (ScreenCreateGame) screenMachine.getState("scg");
		this.scp = (ScreenChoosePort) screenMachine.getState("scp");
		this.spg = (ScreenPlayGame) screenMachine.getState("spg");
	}
	
	@Override
	public Server create() throws InstantiationException {
		remote = new RemotePlayer(scp.getPort());
		try {
			remote.open();
		} catch (SocketException se) {
			throw new InstantiationException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new LocalServer(
			scg.getGame(),
			new LocalPlayer(spg),
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
