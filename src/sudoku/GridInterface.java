package sudoku;

public interface GridInterface {
	public void printGrid();
	public boolean[] getRow(int i);
	public boolean[] getColumn(int j);
	public boolean[] getBlock(int i, int j);
	public CellInterface[][] getGrid();
	public void refreshRCB(int i, int j, int value, boolean state);
	public int[][] getGridOfPossibilities();
}
