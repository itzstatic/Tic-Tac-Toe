package com.brandon.util.app;

import org.teamresistance.util.Time;

public abstract class UpdatableApplication implements Runnable {
	
	private double timePeriod;
	private volatile boolean isRunning;
	
	public UpdatableApplication(TimeStrategy time) {
		this.timePeriod = time.getTimePeriod();
	}
	
	public abstract void update();

	public boolean isRunning() {
		return isRunning;
	}
	
	public void stop() {
		isRunning = false;
	}
	
	@Override
	public void run() {
		isRunning = true;
		double initialTime;
		while (isRunning) {
			initialTime = Time.getTime();
			Time.update();
			this.update();
			while (Time.getTime() - initialTime < timePeriod) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
