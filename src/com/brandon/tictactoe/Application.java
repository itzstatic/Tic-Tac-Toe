package com.brandon.tictactoe;

import javax.swing.JFrame;

import org.teamresistance.util.state.StateMachine;

import com.brandon.tictactoe.factory.HostServerFactory;
import com.brandon.tictactoe.factory.HotseatServerFactory;
import com.brandon.tictactoe.factory.JoinServerFactory;
import com.brandon.tictactoe.factory.SingleplayerServerFactory;
import com.brandon.tictactoe.ui.screen.ScreenChooseIP;
import com.brandon.tictactoe.ui.screen.ScreenChoosePort;
import com.brandon.tictactoe.ui.screen.ScreenCreateGame;
import com.brandon.tictactoe.ui.screen.ScreenMainMenu;
import com.brandon.tictactoe.ui.screen.ScreenMultiMenu;
import com.brandon.tictactoe.ui.screen.ScreenPlayGame;
import com.brandon.tictactoe.ui.screen.ScreenWait;
import com.brandon.util.app.UpdatableApplication;
import com.brandon.util.app.time.TimeFrequency;

public class Application extends UpdatableApplication {
	
	private StateMachine screenMachine;
	private JFrame frame;
	
	public Application() {
		super(new TimeFrequency(30));
		
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ScreenMainMenu main = new ScreenMainMenu(frame);
		ScreenMultiMenu multi = new ScreenMultiMenu(frame);
		
		screenMachine = new StateMachine();
		screenMachine.addState(new ScreenChooseIP(frame), "scip");
		screenMachine.addState(new ScreenChoosePort(frame), "scp");
		screenMachine.addState(new ScreenCreateGame(frame), "scg");
		screenMachine.addState(main, "main");
		screenMachine.addState(multi, "multi");
		screenMachine.addState(new ScreenPlayGame(frame), "spg");
		screenMachine.addState(new ScreenWait(frame), "sw");
		
		main.setSingleFactory(new SingleplayerServerFactory(screenMachine));
		main.setHotseatFactory(new HotseatServerFactory(screenMachine));
		
		multi.setHostFactory(new HostServerFactory(screenMachine));
		multi.setJoinFactory(new JoinServerFactory(screenMachine));
		
		screenMachine.setState("main");
		frame.setVisible(true);
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