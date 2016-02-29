package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.teamresistance.util.state.StateTransition;

import com.brandon.tictactoe.ui.SwingScreen;

public class ScreenChooseIP extends SwingScreen {

	private JTextField txtIP;
	private InetAddress ip;
	
	public ScreenChooseIP(JFrame frame) {
		super(frame);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> previous());
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(this::onEnter);
		
		JPanel pnlControls = new JPanel();
		pnlControls.add(btnBack);
		pnlControls.add(btnNext);
		
		txtIP = new JTextField(16);
		txtIP.addActionListener(this::onEnter);
		
		JPanel pnlIP = new JPanel();
		pnlIP.add(new JLabel("IP: "));
		pnlIP.add(txtIP);
		
		setLayout(new GridLayout(3, 1));
		add(new JLabel("Enter IP Address"));
		add(pnlIP);
		add(pnlControls);
	}
	
	private void onEnter(ActionEvent e) {
		ip = validateIPv4();
		if (ip == null) {
			return;
		}
		next();
	}
	
	private InetAddress validateIPv4() {
		String[] strings = txtIP.getText().trim().split("\\.");
		byte[] bytes = new byte[4];
		
		if (strings.length != 4) {
			return showErrorDialog("Must have 4 bytes");
		}
		
		for (int i = 0; i < strings.length; i++) {
			int integer;
			try {
				integer = Integer.parseInt(strings[i]);
			} catch (NumberFormatException e) {
				return showErrorDialog(strings[i] + " is not numerical");
			}
			
			if (integer < 0 || integer > 255) {
				return showErrorDialog(integer + " must be between 0 and 255");
			}
			
			bytes[i] = (byte) integer;
		}
		
		try {
			return InetAddress.getByAddress(bytes);
		} catch (UnknownHostException e) {
			return showErrorDialog("Unknown Host");
		}
	}
	
	private InetAddress showErrorDialog(String message) {
		JOptionPane.showMessageDialog(
			getFrame(), 
			message, 
			"Error", 
			JOptionPane.ERROR_MESSAGE);
		txtIP.selectAll();
		return null;
	}
	
	public InetAddress getInetAddress() {
		return ip;
	}

	@Override
	public void onEntry(StateTransition e) {
		super.onEntry(e);
		txtIP.setText("");
		txtIP.requestFocus();
		ip = null;
	}
	
}
