package com.brandon.tictactoe.factory;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.server.RemoteServer;
import com.brandon.tictactoe.ui.screen.ScreenChooseIP;
import com.brandon.tictactoe.ui.screen.ScreenChoosePort;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public class JoinServerFactory implements ServerFactory {

	@Override
	public Server createServer(ScreenPlayGame spg, StateMachine screenMachine) {
		return new RemoteServer(
			((ScreenChooseIP) screenMachine.getState("ScreenChooseIP")).getInetAddress(),
			((ScreenChoosePort) screenMachine.getState("ScreenChoosePort")).getPort(),
			new LocalPlayer(spg)
		);
	}

}
