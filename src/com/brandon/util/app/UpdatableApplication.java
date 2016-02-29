package com.brandon.util.app;

import org.teamresistance.util.Time;

public abstract class UpdatableApplication implements Runnable {
	private double timePeriod;
	private boolean isRunning;
	
	public abstract void update();
	
	public UpdatableApplication(double timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void run() {
		isRunning = true;
		
		while (isRunning) {
			double initialTime = Time.getTime();
			while (Time.getTime() - initialTime < timePeriod);
			Time.update();
			update();
		}
	}
	
}
