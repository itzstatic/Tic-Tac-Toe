package com.brandon.tictactoe;

import javax.swing.JFrame;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.ui.screen.ScreenChooseIP;
import com.brandon.tictactoe.ui.screen.ScreenChoosePort;
import com.brandon.tictactoe.ui.screen.ScreenCreateGame;
import com.brandon.tictactoe.ui.screen.ScreenMainMenu;
import com.brandon.tictactoe.ui.screen.ScreenMultiMenu;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;
import com.brandon.util.app.UpdatableApplication;



public class Application extends UpdatableApplication {
	
	private StateMachine screenMachine;
	private JFrame frame;
	
	public Application() {
		super(1D / 30D);
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 1280);
		frame.setVisible(true);
		
		screenMachine = new StateMachine();
		screenMachine.addState(new ScreenChooseIP(frame));
		screenMachine.addState(new ScreenChoosePort(frame));
		screenMachine.addState(new ScreenCreateGame(frame));
		screenMachine.addState(new ScreenMainMenu(frame));
		screenMachine.addState(new ScreenMultiMenu(frame));
		screenMachine.addState(new ScreenPlayGame(frame));
		screenMachine.setState("ScreenMainMenu");
	}
	
	@Override
	public void update() {
		screenMachine.update();
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new Application().run();
	}
}