package com.brandon.tictactoe;


public interface ServerFactory {
	Server create() throws InstantiationException;
	void destroy();
}
