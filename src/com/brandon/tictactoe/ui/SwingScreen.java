package com.brandon.tictactoe.ui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.teamresistance.util.state.StateTransition;

public abstract class SwingScreen extends LinkedScreen {

	private JFrame frame;
	private List<Component> components;
	private LayoutManager layout;
	
	public SwingScreen(JFrame frame) {
		this.frame = frame;
		components = new ArrayList<>();
		layout = new FlowLayout();
	}
	
	protected void add(Component component) {
		components.add(component);
	}
	protected void remove(Component component) {
		components.remove(component);
	}
	protected void setLayout(LayoutManager layout) {
		this.layout = layout;
	}
	protected JFrame getFrame() {
		return frame;
	}
	
	@Override
	public void onEntry(StateTransition e) {
		frame.setLayout(layout);
		for (Component component : components) {
			frame.add(component);
		}
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	@Override
	public void onExit(StateTransition e) {
		frame.setLayout(new FlowLayout());
		for (Component component : components) {
			frame.remove(component);
		}
		frame.revalidate();
		frame.repaint();
	}
}
