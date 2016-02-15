package com.brandon.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Flow implements Runnable {
	private List<BooleanSupplier> flows;
	
	public Flow() {
		flows = new ArrayList<>();
	}
	
	public void add(BooleanSupplier flow) {
		flows.add(flow);
	}
	
	public void remove(BooleanSupplier flow) {
		flows.remove(flow);
	}
	
	public void clear() {
		flows.clear();
	}
	
	@Override
	public void run() {
		int index = 0;
		
		while(true) {
			BooleanSupplier current = flows.get(index);
			if (current.getAsBoolean()) {
				if (index == flows.size() - 1) {
					return;
				} else {
					index++;
				}
			} else {
				if (index == 0) {
					return;
				} else {
					index--;
				}
			}
		}
	}
	
	
}
