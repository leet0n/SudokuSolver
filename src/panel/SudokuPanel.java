package panel;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sudoku.Grid;

public class SudokuPanel extends JPanel implements SudokuPanelInterface{

	private static final long serialVersionUID = 1L;
	
	private SudokuTextField[][] gridOfTextField = new SudokuTextField[9][9];
	private Grid sudoku = new Grid();
	private Grid originalSudoku;

	public SudokuPanel(int blockLength) {
		super(new GridLayout(3, 3));
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black, 3));
		
		JPanel block;
		SudokuListener ls;
		for(int k = 0; k < 3; k++){
			for(int l = 0; l < 3; l++){
				block = new JPanel(new GridLayout(3, 3));
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						gridOfTextField[3*k + i][3*l + j] = new SudokuTextField(1);
						gridOfTextField[3*k + i][3*l + j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
						gridOfTextField[3*k + i][3*l + j].setHorizontalAlignment(JTextField.CENTER);
						gridOfTextField[3*k + i][3*l + j].setFont(new Font("SansSerif", Font.BOLD, 2*blockLength/3));
						ls = new SudokuListener(sudoku.getCell(3*k + i, 3*l + j), gridOfTextField[3*k + i][3*l + j]);
						gridOfTextField[3*k + i][3*l + j].getDocument().addDocumentListener(ls);
						block.add(gridOfTextField[3*k + i][3*l + j]);
					}
				}
				block.setBorder(BorderFactory.createLineBorder(Color.black, 3));
				add(block);
			}
		}
	}

	@Override
	public void reinitPanel() {
		sudoku = new Grid();
		refreshPanel();
	}

	@Override
	public void reinitSudoku() {
		if (originalSudoku == null){
			reinitPanel();
		}
		else{
			sudoku = originalSudoku.copy();
			refreshPanel();
		}
	}

	@Override
	public boolean resolveSudoku() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkSudoku() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean initSudoku() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveSudoku() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void refreshPanel(){
		int value;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				value = sudoku.getCell(i, j).getValue() + 1;
				if (value > 0){
					gridOfTextField[i][j].setText(Integer.toString(value));
				}
				else{
					gridOfTextField[i][j].setText("");
				}
			}
		}
	}
}
