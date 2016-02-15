package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.brandon.tictactoe.ui.GetScreen;

public class ScreenChoosePort extends GetScreen<Integer> {

	private JTextField txtPort;
	
	public ScreenChoosePort(JFrame frame) {
		super(frame);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			super.close(null);
		});
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(e -> {
			Integer port = validatePort();
			if (port == null) {
				return;
			}
			close(port);
		});
		
		txtPort = new JTextField(6);
		txtPort.addActionListener(e -> btnNext.getActionListeners()[0].actionPerformed(e));
		
		JPanel pnlPort = new JPanel();
		pnlPort.add(new JLabel("Port: "));
		pnlPort.add(txtPort);
		
		JPanel  pnlControls = new JPanel();
		pnlControls.add(btnBack);
		pnlControls.add(btnNext);
		
		setLayout(new GridLayout(3, 1));
		add(new JLabel("Choose Port"));
		add(pnlPort);
		add(pnlControls);
	}
	
	private Integer validatePort() {
		int port;
		String sPort = txtPort.getText().trim();
		
		if (sPort.isEmpty()) {
			return null;
		}
		
		try {
			port = Integer.parseInt(sPort);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(getFrame(), "Invalid port!.", "Error!", JOptionPane.ERROR_MESSAGE);
			txtPort.selectAll();
			return null;
		}
		
		if (port < 0 || 65535 < port) {
			JOptionPane.showMessageDialog(getFrame(), "Ports must be from 0 to 65535.", "Error!", JOptionPane.ERROR_MESSAGE);
			txtPort.selectAll();
			return -1;
		}
		return new Integer(port);
	}
	
	@Override
	public void update() {

	}

}
