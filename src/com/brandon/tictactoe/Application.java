package com.brandon.tictactoe;

import io.github.itzstatic.util.UpdatableApplication;
import io.github.itzstatic.util.ui.screen.ScreenManager;

import javax.swing.JFrame;

import com.brandon.tictactoe.ui.screen.ScreenMainMenu;



public class Application extends UpdatableApplication {
	
	private ScreenManager screenManager;
	private JFrame frame;
	
	public Application() {
		super(1D / 30D);
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 1280);
		frame.setVisible(true);
		
		screenManager = new ScreenManager();
		screenManager.setScreen(new ScreenMainMenu(frame));
	}
	
	@Override
	public void update() {
		screenManager.update();
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new Application().run();
	}
}