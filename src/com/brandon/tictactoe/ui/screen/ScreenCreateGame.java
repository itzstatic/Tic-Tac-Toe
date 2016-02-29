package com.brandon.tictactoe.ui.screen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.teamresistance.util.state.StateTransition;

import com.brandon.tictactoe.game.Game;
import com.brandon.tictactoe.ui.SwingScreen;


public class ScreenCreateGame extends SwingScreen {

	private static final int MAXIMUM_DIMENSION = 10;
	
	//width, height, win
	private JTextField[] fields;
	private String[] names;
	private Game game;
	
	public ScreenCreateGame(JFrame frame) {
		super(frame);
		names = new String[] {
			"Width",
			"Height",
			"Squares to win"
		};
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> previous());
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(this::onNext);

		int fieldLength = Integer.toString(MAXIMUM_DIMENSION).length();		
		fields = new JTextField[3];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(fieldLength);
			fields[i].addActionListener(this::onEnter);
		}
		
		JPanel[] panels = new JPanel[3];
		JPanel pnlControls = new JPanel();
		
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setLayout(new GridLayout(1, 2));
			panels[i].add(new JLabel(names[i] + ": "));
			panels[i].add(fields[i]);
		}
		
		pnlControls.setLayout(new GridLayout(1, 2));
		pnlControls.add(btnBack);
		pnlControls.add(btnNext);
		
		setLayout(new GridLayout(5, 1));
		add(new JLabel("Create Game"));
		add(panels[0]);
		add(panels[1]);
		add(panels[2]);
		add(pnlControls);
	}
	
	private void onEnter(ActionEvent e) {
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
		onNext(e);
	}
	
	private void onNext(ActionEvent e) {
		int[] params = validateParameters();
		if (params == null) {
			return;
		}
		game = new Game(params[0], params[1], params[2]);
		next();
	}
	
	private int[] validateParameters() {
		int[] params = new int[fields.length];
		
		for (int i = 0; i < fields.length; i++) {
			String param = fields[i].getText().trim();
			try {
				params[i] = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				return showErrorDialog(names[i] + " must be numerical", i);
			}
		}
		
		for (int i = 0; i < 2; i++) {
			if (params[i] > MAXIMUM_DIMENSION) {
				return showErrorDialog("Width and height cannot be greater than " + MAXIMUM_DIMENSION, i);
			}
		}
		
		if (params[2] > Math.min(params[0], params[1])) {
			return showErrorDialog("Squares to win too large", 2);
		}
		return params;
	}
	
	private int[] showErrorDialog(String message, int fieldIndex) {
		JOptionPane.showMessageDialog(
			getFrame(), 
			message, 
			"Error", 
			JOptionPane.ERROR_MESSAGE
		);
		fields[fieldIndex].selectAll();
		return null;
	}
	
	public Game getGame() {
		return game;
	}
	
	@Override
	public void onEntry(StateTransition e) {
		super.onEntry(e);
		for (JTextField field : fields) {
			field.setText("");
		}
		fields[0].requestFocus();
		game = null;
	}
}
