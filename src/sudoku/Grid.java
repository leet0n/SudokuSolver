package sudoku;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

public class Grid implements GridInterface {
	
	public static void main(String[] args){
		Grid sudokuTest = new Grid();
		sudokuTest.initFromFile("sudokuWC");
		if (Backtracking.backtracking(sudokuTest)) sudokuTest.printGrid();
		else System.out.println("Pas de solution");
	}
	
	private CellInterface[][] grid;
	private boolean[][] rows;
	private boolean[][] columns;
	private boolean[][] blocks;
	
	private void init(){
		grid    = new CellInterface[9][9];
		rows    = new boolean[9][9];
		columns = new boolean[9][9];
		blocks  = new boolean[9][9];
	}
	
	public Grid(){
		init();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				grid[i][j] = new Cell(-1, i, j, this);
			}
		}
	}

	public void printGrid() {
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
	public void initFromFile(String name) {
		
		FileReader fileInput;
		BufferedReader writeFile;
		
		try{
			fileInput = new FileReader(name);
			writeFile = new BufferedReader(fileInput);
			init();
			int value;
			
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					value = Character.getNumericValue(writeFile.read()) - 1;
					grid[i][j] = new Cell(value, i, j, this);
					if (value != -1) refreshRCB(i, j, value, true);
				}
				writeFile.readLine();
			}
			writeFile.close();
			
		} catch(FileNotFoundException e){
			e.printStackTrace();			
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
