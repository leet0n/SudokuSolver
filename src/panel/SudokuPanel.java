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
	
	private final SudokuTextField[][] GRIDOFTEXTFIELD = new SudokuTextField[9][9];
	private final Grid SUDOKU = new Grid();
	private Grid originalSudoku;
	private String path;

	public SudokuPanel(int blockLength) {
		super(new GridLayout(3, 3));
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 3));
		
		JPanel block;
		SudokuListener ls;
		for(int k = 0; k < 3; k++){
			for(int l = 0; l < 3; l++){
				block = new JPanel(new GridLayout(3, 3));
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						GRIDOFTEXTFIELD[3*k + i][3*l + j] = new SudokuTextField(1);
						GRIDOFTEXTFIELD[3*k + i][3*l + j]
								.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 1));
						GRIDOFTEXTFIELD[3*k + i][3*l + j]
								.setHorizontalAlignment(JTextField.CENTER);
						GRIDOFTEXTFIELD[3*k + i][3*l + j]
								.setFont(new Font("SansSerif", Font.BOLD, 2*blockLength/3));
						ls = new SudokuListener(SUDOKU.getCell(3*k + i, 3*l + j), 
								GRIDOFTEXTFIELD[3*k + i][3*l + j]);
						GRIDOFTEXTFIELD[3*k + i][3*l + j]
								.getDocument().addDocumentListener(ls);
						GRIDOFTEXTFIELD[3*k + i][3*l + j].addFocusListener(ls);
						block.add(GRIDOFTEXTFIELD[3*k + i][3*l + j]);
					}
				}
				block.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 3));
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
				path = null;
				break;
			case JOptionPane.NO_OPTION:
				return;
		   }
		}
		SUDOKU.copy(null);
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
				SUDOKU.copy(null);
				refreshPanel(Color.black);
			}
			else{
				SUDOKU.copy(originalSudoku);
				refreshPanel(new Color(34, 167, 240));
			}
			break;
		case JOptionPane.NO_OPTION:
			return;
		}
	}

	@Override
	public void resolveSudoku() {
		if (originalSudoku != null){
			int response = JOptionPane.showConfirmDialog(this, 
					"Solving the initial sudoku may overwrite your progression. Continue ?", 
					"Solving sudoku", 
					JOptionPane.YES_NO_OPTION);
			switch (response){
			case JOptionPane.YES_OPTION:
				if (checkSudoku(originalSudoku, false)){
					Grid temp = new Grid();
					temp.copy(originalSudoku);
					if (Backtracking.backtracking(temp)){
						SUDOKU.copy(temp);
						refreshPanel(new Color(34, 167, 240));
					}
					else{
						JOptionPane.showMessageDialog(this, 
								"No solutions found", "Result",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					originalSudoku = null;
				}
				break;
			case JOptionPane.NO_OPTION:
				return;
			}
		}
		else{
			originalSudoku = new Grid();
			originalSudoku.copy(SUDOKU);
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
					GRIDOFTEXTFIELD[i][j].setForeground(new Color(231, 76, 60));
					return false;
				}
			}
		}
		
		if (completed && show){
			refreshPanel(new Color(46, 204, 113));
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
		checkSudoku(SUDOKU, true);
	}

	@Override
	public void initSudoku() {
		JFileChooser chooser = new JFileChooser("data");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
	    	this.path = chooser.getSelectedFile().getAbsolutePath();
	    	try{
	    		SUDOKU.initFromFile(path);
	    		originalSudoku = new Grid();
	    		originalSudoku.copy(SUDOKU);
	    		refreshPanel(new Color(34, 167, 240));
	    	}catch(FileNotFoundException e){
	    		JOptionPane.showMessageDialog(this, "File not found",
	    				"Error", JOptionPane.ERROR_MESSAGE);
	    		reinitPanel();
	    		path = null;
	    	}catch(IOException e){
	    		JOptionPane.showMessageDialog(this, "File cannot be read", 
						"Error", JOptionPane.ERROR_MESSAGE);
	    		reinitPanel();
	    		path = null;
	    	}
	    }
	}
	
	@Override
	public void saveSudoku(){
		if (path == null){
			saveSudokuAs();
		}
		else{
			try{
	    		SUDOKU.saveGrid(path);
	    	}catch(FileNotFoundException e){
	    		JOptionPane.showMessageDialog(this, "File not found", 
						"Error", JOptionPane.ERROR_MESSAGE);
	    		path = null;
	    	}catch(IOException e){
	    		JOptionPane.showMessageDialog(this, "File cannot be wrote", 
	    				"Error", JOptionPane.ERROR_MESSAGE);
	    		path = null;
	    	}
		}
	}

	@Override
	public void saveSudokuAs() {
		JFileChooser chooser = new JFileChooser("data");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION){
	    	this.path = chooser.getSelectedFile().getAbsolutePath();
	    	try{
	    		int end = path.length();
	    		if (end < 5 || !path.substring(end - 4).equals(".txt")){
	    			path = path.concat(".txt");
	    		}
	    		SUDOKU.saveGrid(path);
	    	}catch(FileNotFoundException e){
	    		JOptionPane.showMessageDialog(this, "File not found", 
						"Error", JOptionPane.ERROR_MESSAGE);
	    		path = null;
	    	}catch(IOException e){
	    		JOptionPane.showMessageDialog(this, "File cannot be wrote", 
	    				"Error", JOptionPane.ERROR_MESSAGE);
	    		path = null;
	    	}
	    }
	}
	
	private void refreshPanel(Color color){
		int value;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				value = SUDOKU.getCell(i, j).getValue() + 1;
				if (value > 0){
					GRIDOFTEXTFIELD[i][j].setText(Integer.toString(value));
					GRIDOFTEXTFIELD[i][j].setForeground(color);
				}
				else{
					GRIDOFTEXTFIELD[i][j].setText("");
				}
			}
		}
	}
}
