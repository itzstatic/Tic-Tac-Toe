package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.teamresistance.util.state.StateTransition;

import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenChoosePort extends SwingScreen {

	private JTextField txtPort;
	private int port;
	
	public ScreenChoosePort(JFrame frame) {
		super(frame);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> previous());
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(this::onEnter);
		
		txtPort = new JTextField(6);
		txtPort.addActionListener(this::onEnter);
		
		JPanel pnlPort = new JPanel();
		pnlPort.add(new JLabel("Port: "));
		pnlPort.add(txtPort);
		
		JPanel  pnlControls = new JPanel();
		pnlControls.add(btnBack);
		pnlControls.add(btnNext);
		
		setLayout(new GridLayout(3, 1));
		add(new JLabel("Enter Port"));
		add(pnlPort);
		add(pnlControls);
	}

	private void onEnter(ActionEvent e) {
		port = validatePort();
		if (port == -1) {
			return;
		}
		next();
	}
	
	private int validatePort() {
		int port;
		String sPort = txtPort.getText().trim();
		
		if (sPort.isEmpty()) {
			return -1;
		}
		
		try {
			port = Integer.parseInt(sPort);
		} catch (NumberFormatException nfe) {
			return showErrorDialog("Must be numerical");
		}
		
		if (port < 0 || 65535 < port) {
			return showErrorDialog("Must be from 0 to 65535");
		}
		return port;
	}
	
	private int showErrorDialog(String message) {
		JOptionPane.showMessageDialog(
			getFrame(), 
			message, 
			"Error", 
			JOptionPane.ERROR_MESSAGE
		);
		txtPort.selectAll();
		return -1;
	}
	
	public int getPort() {
		return port;
	}
	
	@Override
	public void onEntry(StateTransition e) {
		super.onEntry(e);
		txtPort.setText("");
		txtPort.requestFocus();
		port = -1;
	}
	
}
