package com.brandon.tictactoe.ui;

import io.github.itzstatic.util.ui.screen.Screen;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public abstract class SwingScreen extends Screen {

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
	protected void onShow() {
		frame.setLayout(layout);
		for (Component component : components) {
			frame.add(component);
		}
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	@Override
	protected void onHide() {
		frame.setLayout(new FlowLayout());
		for (Component component : components) {
			frame.remove(component);
		}
		frame.revalidate();
		frame.repaint();
	}
}
