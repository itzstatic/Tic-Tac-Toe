package com.brandon.util.app.time;

import com.brandon.util.app.TimeStrategy;

public class TimeFrequency implements TimeStrategy {

	private double timePeriod;
	
	public TimeFrequency(double hertz) {
		timePeriod = 1 / hertz;
	}
	
	@Override
	public double getTimePeriod() {
		return timePeriod;
	}

}
