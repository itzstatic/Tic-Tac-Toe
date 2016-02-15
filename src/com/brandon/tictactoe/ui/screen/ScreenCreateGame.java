package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.brandon.tictactoe.game.Game;
import com.brandon.tictactoe.ui.GetScreen;


public class ScreenCreateGame extends GetScreen<Game> {

	private JTextField[] fields;
	private String[] errors;
	
	public ScreenCreateGame(JFrame frame) {
		super(frame);
		errors = new String[] {
			"Invalid width!",
			"Invalid height!",
			"Invalid squares to win!"
		};
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			close(null);
		});
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(e -> {
			int[] params = validateParameters();
			if (params == null) {
				return;
			}
			close(new Game(params[0], params[1], params[2]));
		});
		
		fields = new JTextField[3];
		ActionListener fieldListener = e -> {
			JTextComponent source = (JTextComponent) e.getSource();
			if (source.getText().trim().isEmpty()) {
				return;
			}
			
			for (JTextField field : fields) {
				if (field.getText().trim().isEmpty()) {
					field.requestFocus();
					return;
				}
			}
			
			btnCreate.getActionListeners()[0].actionPerformed(e);
		};
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(2);
			fields[i].addActionListener(fieldListener);
		}
		
		JPanel pnlWidth = new JPanel();
		JPanel pnlHeight = new JPanel();
		JPanel pnlToWin = new JPanel();
		JPanel pnlControls = new JPanel();
		
		pnlWidth.setLayout(new GridLayout(1, 2));
		pnlWidth.add(new JLabel("Width: "));
		pnlWidth.add(fields[0]);
		
		pnlHeight.setLayout(new GridLayout(1, 2));
		pnlHeight.add(new JLabel("Height: "));
		pnlHeight.add(fields[1]);
		
		pnlToWin.setLayout(new GridLayout(1, 2));
		pnlToWin.add(new JLabel("Squares to Win: "));
		pnlToWin.add(fields[2]);
		
		pnlControls.setLayout(new GridLayout(1, 2));
		pnlControls.add(btnBack);
		pnlControls.add(btnCreate);
		
		setLayout(new GridLayout(5, 1));
		add(new JLabel("Create Game"));
		add(pnlWidth);
		add(pnlHeight);
		add(pnlToWin);
		add(pnlControls);
	}
	
	private int[] validateParameters() {
		int[] params = new int[fields.length];
		
		for (int i = 0; i < fields.length; i++) {
			String param = fields[i].getText().trim();
			
			
			try {
				params[i] = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				fields[i].selectAll();
				JOptionPane.showMessageDialog(getFrame(), errors[i], "Error!", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}
		
		if (params[2] > Math.min(params[0], params[1])) {
			fields[2].selectAll();
			JOptionPane.showMessageDialog(getFrame(), "Squares to win too large!", "Error!", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return params;
	}
	
	@Override
	public void update() {
		
	}
}
