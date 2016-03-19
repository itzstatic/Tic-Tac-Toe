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
	
	private ScreenChooseIP scip;
	private ScreenChoosePort scp;
	private ScreenPlayGame spg;
	
	public JoinServerFactory(StateMachine screenMachine) {
		this.scip = (ScreenChooseIP) screenMachine.getState("scip");
		this.scp = (ScreenChoosePort) screenMachine.getState("scp");
		this.spg = (ScreenPlayGame) screenMachine.getState("spg");
	}
	
	@Override
	public Server create() throws InstantiationException {
		remote = new RemoteServer(new LocalPlayer(spg));
		try {
			remote.open(
				scip.getInetAddress(),
				scp.getPort()
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
