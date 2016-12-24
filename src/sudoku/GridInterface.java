package sudoku;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GridInterface {
	public void initFromFile (String path) throws FileNotFoundException, IOException;
	public void saveGrid(String path) throws FileNotFoundException, IOException;
	public boolean[] getRow(int i);
	public boolean[] getColumn(int j);
	public boolean[] getBlock(int i, int j);
	public CellInterface getCell(int i, int j);
	public CellInterface[][] getGrid();
	public void refreshRCB(int i, int j, int value, boolean state);
	public int[][] getGridOfPossibilities();
}
