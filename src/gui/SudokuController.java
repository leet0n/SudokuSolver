package gui;

import javax.swing.JTextField;
import sudoku.Grid;
import sudoku.CellInterface;

public class SudokuController {
	
	private JTextField[][] gridOfTextField;
	private Grid sudoku = new Grid();
	
	public SudokuController(SudokuPanel panel){
		this.gridOfTextField = panel.getGrid();
		CellInterface[][] cells = sudoku.getGrid();
		SudokuListener ls;
		
		for(int i = 0; i < gridOfTextField.length; i++){
			for(int j = 0; j < gridOfTextField[0].length; j++){
				ls = new SudokuListener(cells[i][j], gridOfTextField[i][j]);
				gridOfTextField[i][j].getDocument().addDocumentListener(ls);
			}
		}
		
	}
}
