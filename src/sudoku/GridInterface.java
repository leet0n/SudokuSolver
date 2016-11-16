package sudoku;

public interface GridInterface {
	public void printGrid();
	public CellInterface[][] getGrid();
	public CellInterface[][] getGridOfPossibilities();
}
