package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.brandon.tictactoe.ui.GetScreen;

public class ScreenChooseIP extends GetScreen<InetAddress> {

	private JTextField txtIP;
	
	public ScreenChooseIP(JFrame frame) {
		super(frame);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> close(null));
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(e -> {
			InetAddress ip = validateIPv4();
			if (ip == null) {
				return;
			}
			close(ip);
		});
		
		JPanel pnlControls = new JPanel();
		pnlControls.add(btnBack);
		pnlControls.add(btnNext);
		
		txtIP = new JTextField();
		txtIP.addActionListener(e -> btnNext.getActionListeners()[0].actionPerformed(e));
		
		JPanel pnlIP = new JPanel();
		pnlIP.add(new JLabel("IP: "));
		pnlIP.add(txtIP);
		
		setLayout(new GridLayout(3, 1));
		add(new JLabel("Enter IP Address"));
		add(pnlIP);
		add(pnlControls);
	}
	
	private InetAddress validateIPv4() {
		String[] strings = txtIP.getText().split(".");
		
		if (strings.length != 4) {
			JOptionPane.showMessageDialog(getFrame(), "Invalid IP!", "Error!", JOptionPane.ERROR_MESSAGE);
			txtIP.selectAll();
			return null;
		}
		
		
		byte[] bytes = new byte[strings.length];
		
		for (int i = 0; i < strings.length; i++) {
			try {
				bytes[i] = Byte.parseByte(strings[i].trim());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(getFrame(), "Invalid IP!", "Error!", JOptionPane.ERROR_MESSAGE);
				txtIP.selectAll();
				return null;
			}
		}
		
		try {
			return InetAddress.getByAddress(bytes);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(getFrame(), e.getMessage(), "Unknown Host!", JOptionPane.ERROR_MESSAGE);
			txtIP.selectAll();
			return null;
		}
	}

	@Override
	public void update() {

	}
}
