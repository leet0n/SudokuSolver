package panel;

import java.awt.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import sudoku.Backtracking;
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
		if (originalSudoku != null){
			int response = JOptionPane.showConfirmDialog(this, 
					"The opened sudoku will be deleted. Continue ?", 
					"Clear panel", 
					JOptionPane.YES_NO_OPTION);
			switch (response){
			case JOptionPane.YES_OPTION:
				originalSudoku = null;
				break;
			case JOptionPane.NO_OPTION:
				return;
		   }
		}
		sudoku.copy(null);
		refreshPanel(Color.black);
	}

	@Override
	public void reinitSudoku() {
		int response = JOptionPane.showConfirmDialog(this, 
				"The current progression will be lost. Continue ?", 
				"Reinitialize sudoku", 
				JOptionPane.YES_NO_OPTION);
		switch (response){
		case JOptionPane.YES_OPTION:
			if (originalSudoku == null){
				sudoku.copy(null);
				refreshPanel(Color.black);
			}
			else{
				sudoku.copy(originalSudoku);
				refreshPanel(Color.blue);
			}
			break;
		case JOptionPane.NO_OPTION:
			return;
		}
	}

	@Override
	public void resolveSudoku() {
		if (originalSudoku != null){
			if (checkSudoku(originalSudoku, false)){
				Grid temp = new Grid();
				temp.copy(originalSudoku);
				if (Backtracking.backtracking(temp)){
					sudoku.copy(temp);
					refreshPanel(Color.blue);
				}
				else{
					JOptionPane.showMessageDialog(this, 
							"No solutions found", "Result", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				originalSudoku = null;
			}
		}
		else{
			originalSudoku = new Grid();
			originalSudoku.copy(sudoku);
			resolveSudoku();
		}
	}

	private boolean checkSudoku(Grid argSudoku, boolean show) {
		boolean completed = true;
		boolean[][] rows = new boolean[9][9];
		boolean[][] columns = new boolean[9][9];
		boolean[][] blocks = new boolean[9][9];
		int value;
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				value = argSudoku.getCell(i, j).getValue();
				if (completed && value == -1){
					completed = false;
				}
				if (value >= 0 && 
						(!(rows[i][value] = !rows[i][value])       ||
						 !(columns[j][value] = !columns[j][value]) || 
						 !(blocks[j/3 + 3*(i/3)][value] = !blocks[j/3 + 3*(i/3)][value]))){
					JOptionPane.showMessageDialog(this,
							"Error found at line " + (i+1) + ", column " + (j+1), 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
					gridOfTextField[i][j].setForeground(Color.red);
					return false;
				}
			}
		}
		
		if (completed && show){
			JOptionPane.showMessageDialog(this,
					"Congratulations, you have solved the sudoku ! ", 
					"Result", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		else if (show){
			JOptionPane.showMessageDialog(this,
					"No errors found", 
					"Result", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}
	
	@Override
	public void checkSudoku(){
		checkSudoku(sudoku, true);
	}

	@Override
	public void initSudoku() {
		JFileChooser chooser = new JFileChooser("data");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
	    	String path = chooser.getSelectedFile().getAbsolutePath();
	    	try{
	    		sudoku.initFromFile(path);
	    		originalSudoku = new Grid();
	    		originalSudoku.copy(sudoku);
	    		refreshPanel(Color.blue);
	    	}catch(FileNotFoundException e){
	    		JOptionPane.showMessageDialog(this, "File not found",
	    				"Error", JOptionPane.ERROR_MESSAGE);
	    		reinitPanel();
	    	}catch(IOException e){
	    		JOptionPane.showMessageDialog(this, "File cannot be read", 
						"Error", JOptionPane.ERROR_MESSAGE);
	    		reinitPanel();
	    	}
	    }
	}

	@Override
	public void saveSudoku() {
		JFileChooser chooser = new JFileChooser("data");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
	    	String path = chooser.getSelectedFile().getAbsolutePath();
	    	try{
	    		sudoku.saveGrid(path);
	    	}catch(FileNotFoundException e){
	    		JOptionPane.showMessageDialog(this, "File not found", 
						"Error", JOptionPane.ERROR_MESSAGE);
	    	}catch(IOException e){
	    		JOptionPane.showMessageDialog(this, "File cannot be wrote", 
	    				"Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	}
	
	private void refreshPanel(Color color){
		int value;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				value = sudoku.getCell(i, j).getValue() + 1;
				if (value > 0){
					gridOfTextField[i][j].setText(Integer.toString(value));
					gridOfTextField[i][j].setForeground(color);
				}
				else{
					gridOfTextField[i][j].setText("");
				}
			}
		}
	}
}
