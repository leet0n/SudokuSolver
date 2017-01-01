package panel;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sudoku.CellInterface;

public class SudokuListener implements DocumentListener, FocusListener {

	private final CellInterface CELL;
	private final JTextField FIELD;
	
	public SudokuListener(CellInterface cell, JTextField field){
		this.CELL = cell;
		this.FIELD = field;
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		action();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		action();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		action();
	}
	
	private void action(){
		FIELD.setForeground(new Color(52, 73, 94));
		String value = FIELD.getText();
		if (value.equals("0")){
			FIELD.setForeground(new Color(231, 76, 60));
			JOptionPane.showMessageDialog(null, "Integers must be between 1 and 9",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try{
				CELL.setValue(Integer.parseInt(value) - 1);
			}catch(NumberFormatException e){
				if (value.length() > 0){
					FIELD.setForeground(new Color(231, 76, 60));
					JOptionPane.showMessageDialog(null, "Invalid character", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					CELL.setValue(-1);
				}
			}
		}
	}

	@Override
	public void focusGained(FocusEvent ev) {
		FIELD.setBackground(new Color(245, 215, 110));
	}

	@Override
	public void focusLost(FocusEvent ev) {
		FIELD.setBackground(Color.white);
	}
}
