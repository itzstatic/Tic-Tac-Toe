package com.brandon.tictactoe;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.ui.screen.ScreenPlayGame;

public interface ServerFactory {
	Server createServer(ScreenPlayGame spg, StateMachine screenMachine);
}
