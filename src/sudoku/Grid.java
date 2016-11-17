package sudoku;

public class Grid implements GridInterface {
	
	public static void main(String[] args){
		Grid sudokuTest = new Grid();
		sudokuTest.printGrid();
	}
	
	private CellInterface[][] grid;
	private boolean[][] rows;
	private boolean[][] columns;
	private boolean[][] blocks;
	
	public Grid(){
		rows = new boolean[9][9];
		columns = new boolean[9][9];
		blocks = new boolean[9][9];
		grid = new CellInterface[9][9];
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				grid[i][j] = new Cell(-1, i, j, this);
			}
		}
	}

	@Override
	public void printGrid() {
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print((grid[i][j].getValue()+1) + " ");
			}
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
}
