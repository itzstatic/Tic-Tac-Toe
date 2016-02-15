package com.brandon.tictactoe.flow;

public class Wrapper<T> {
	private T value;
	
	public T get() {
		return value;
	}
	public void set(T value) {
		this.value = value;
	}
}
