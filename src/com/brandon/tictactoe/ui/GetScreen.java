package com.brandon.tictactoe.ui;

import javax.swing.JFrame;

public abstract class GetScreen<T> extends SwingScreen {

	private volatile boolean open;
	private volatile T value;
	
	public GetScreen(JFrame frame) {
		super(frame);
		open = true;
		value = null;
	}
	
	protected void close(T value) {
		open = false;
		this.value = value;
	}
	public T get() {
		while (open);
		T value = this.value;
		this.value = null;
		return value;
	}
}
