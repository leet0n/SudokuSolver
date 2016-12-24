package sudoku;

import java.lang.SecurityException;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

public class Grid implements GridInterface {
	
	private CellInterface[][] grid;
	private boolean[][] rows;
	private boolean[][] columns;
	private boolean[][] blocks;
	
	private void initRCB(){		
		rows    = new boolean[9][9];
		columns = new boolean[9][9];
		blocks  = new boolean[9][9];
	}
	
	public Grid(){
		grid = new CellInterface[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				grid[i][j] = new Cell(-1, i, j, this);
			}
		}
		initRCB();
	}

	public void printGrid() {
		System.out.println();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print((grid[i][j].getValue()+1) + " ");
				if (j == 2 || j == 5) System.out.print(" ");
			}
			if (i == 2 || i == 5) System.out.println();
			System.out.println();
		}
	}
	
	@Override
	public boolean[] getRow(int i) {
		return rows[i];
	}

	@Override
	public boolean[] getColumn(int j) {
		return columns[j];
	}

	@Override
	public boolean[] getBlock(int i, int j) {
		return blocks[j/3 + 3*(i/3)];
	}
	
	@Override
	public CellInterface getCell(int i, int j){
		return grid[i][j];
	}

	@Override
	public CellInterface[][] getGrid() {
		return grid;
	}
	
	@Override
	public void refreshRCB(int i, int j, int value, boolean state) {
		rows[i][value] = state;
		columns[j][value] = state;
		blocks[j/3 + 3*(i/3)][value] = state;
	}

	@Override
	public int[][] getGridOfPossibilities() {
		int[][] gridOfPossibilities = new int[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				gridOfPossibilities[i][j] = grid[i][j].getNumberOfPossibilities();
			}
		}
		return gridOfPossibilities;
	}

	@Override
	public void initFromFile(String path) throws FileNotFoundException, IOException {
		
		FileReader fileInput;
		BufferedReader readFile;
		
		try{
			fileInput = new FileReader(path);
			readFile = new BufferedReader(fileInput);
			initRCB();
			int value;
			
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					value = Character.getNumericValue(readFile.read()) - 1;
					grid[i][j].setValue(value);
				}
				readFile.readLine();
			}
			readFile.close();
			
		} catch(FileNotFoundException e){
			throw e;
		} catch (IOException e){
			throw e;
		}
	}

	@Override
	public void saveGrid(String path) throws FileNotFoundException, IOException {
		PrintWriter writeFile;
		
		try{
			writeFile = new PrintWriter(path);
			
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					writeFile.print(grid[i][j].getValue()+1);
				}
				writeFile.println();
			}
			writeFile.close();
			
		} catch(FileNotFoundException e){
			throw e;
		} catch(SecurityException e){
			throw e;
		}
	}
	
	public void copy(Grid original){
		if (original != null){
			CellInterface[][] originalGrid = original.getGrid();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					grid[i][j].setValue(originalGrid[i][j].getValue());
				}
			}
		}
		else{
			copy(new Grid());
		}
	}
}
