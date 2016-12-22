package gui;

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
		try{
			cell.setValue(Integer.parseInt(field.getText()) - 1);
		}catch(NumberFormatException e){}
	}
}
