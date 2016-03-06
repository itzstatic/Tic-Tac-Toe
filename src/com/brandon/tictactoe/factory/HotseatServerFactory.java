package com.brandon.tictactoe.factory;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.server.LocalServer;
import com.brandon.tictactoe.ui.screen.ScreenCreateGame;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public class HotseatServerFactory implements ServerFactory {
	@Override
	public Server create(StateMachine screenMachine) {
		ScreenPlayGame spg = (ScreenPlayGame) screenMachine.getState("ScreenPlayGame");
		return new LocalServer(
			((ScreenCreateGame) screenMachine.getState("ScreenCreateGame")).getGame(),
			new LocalPlayer(spg),
			new LocalPlayer(spg)
		);
	}
	
	@Override
	public void destroy() {
		
	}
}
