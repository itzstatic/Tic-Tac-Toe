package com.brandon.tictactoe.ui;

import com.brandon.tictactoe.ui.screen.ScreenWait;
import com.brandon.util.app.UpdatableApplication;
import com.brandon.util.app.time.TimeFrequency;

public class WaitApplication extends UpdatableApplication {

	final private static int DOTS = 4;
	
	private int dots;
	private ScreenWait sw;
	
	public WaitApplication(ScreenWait sw) {
		super(new TimeFrequency(2));
		this.sw = sw;
	}
	
	@Override
	public void run() {
		dots = 0;
		super.run();
	}
	
	@Override
	public void update() {
		dots = ++dots % DOTS;
		
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < dots; i++) {
			str.append(".");
		}
		
		int spaces = DOTS - dots;
		for (int i = 0; i < spaces; i++) {
			str.append(" ");
		}
		sw.setDots(str.toString());
	}
}
