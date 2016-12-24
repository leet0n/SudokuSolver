package panel;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sudoku.CellInterface;

public class SudokuListener implements DocumentListener {

	private CellInterface cell;
	private JTextField field;
	
	public SudokuListener(CellInterface cell, JTextField field){
		this.cell = cell;
		this.field = field;
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
		field.setForeground(Color.black);
		String value = field.getText();
		if (value.equals("0")){
			field.setForeground(Color.red);
			JOptionPane.showMessageDialog(null, "Integers must be between 1 and 9",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try{
				cell.setValue(Integer.parseInt(value) - 1);
			}catch(NumberFormatException e){
				if (value.length() > 0){
					field.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "Invalid character", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					cell.setValue(-1);
				}
			}
		}
	}
}
