package com.brandon.tictactoe;

import org.teamresistance.util.state.StateMachine;

public interface ServerFactory {
	Server create(StateMachine screenMachine) throws InstantiationException;
	void destroy();
}
