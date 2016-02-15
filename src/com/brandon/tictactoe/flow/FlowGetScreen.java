package com.brandon.tictactoe.flow;

import io.github.itzstatic.util.ui.screen.Screen;

import java.util.function.BooleanSupplier;

import com.brandon.tictactoe.ui.GetScreen;

public class FlowGetScreen<T> implements BooleanSupplier {
	
	private Screen parent;
	private GetScreen<T> screen;
	private Wrapper<T> wrapper;
	
	public FlowGetScreen(Screen parent, GetScreen<T> screen, Wrapper<T> wrapper) {
		this.parent = parent;
		this.screen = screen;
		this.wrapper = wrapper;
	}
	
	@Override
	public boolean getAsBoolean() {
		wrapper.set(null);
		parent.setScreen(screen);
		T value = screen.get();
		wrapper.set(value);
		if (value == null) {
			screen.setScreen(parent);
			return false;
		}
		return true;
	}
	
}
