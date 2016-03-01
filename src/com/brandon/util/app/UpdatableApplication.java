package com.brandon.util.app;

import org.teamresistance.util.Time;

public abstract class UpdatableApplication implements Runnable {
	private double timePeriod;
	private boolean isRunning;
	
	public abstract void update();
	
	public UpdatableApplication(TimeStrategy time) {
		this.timePeriod = time.getTimePeriod();
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void run() {
		isRunning = true;
		
		double initialTime;
		while (isRunning) {
			initialTime = Time.getTime();
			Time.update();
			update();
			while (Time.getTime() - initialTime < timePeriod);
		}
	}
	
}
