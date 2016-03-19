package com.brandon.tictactoe.factory;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.Server;
import com.brandon.tictactoe.ServerFactory;
import com.brandon.tictactoe.player.LocalPlayer;
import com.brandon.tictactoe.server.LocalServer;
import com.brandon.tictactoe.ui.screen.ScreenCreateGame;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public class HotseatServerFactory implements ServerFactory {
	
	private ScreenCreateGame scg;
	private ScreenPlayGame spg;
	
	
	public HotseatServerFactory(StateMachine screenMachine) {
		this.scg = (ScreenCreateGame) screenMachine.getState("scg");
		this.spg = (ScreenPlayGame) screenMachine.getState("spg");
	}

	@Override
	public Server create() {
		return new LocalServer(
			scg.getGame(),
			new LocalPlayer(spg),
			new LocalPlayer(spg)
		);
	}
	
	@Override
	public void destroy() {
		
	}
}
