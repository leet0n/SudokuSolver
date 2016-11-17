package sudoku;

public interface CellInterface {
	public int getValue();
	public void setValue(int value);
	public boolean isPossible(int value);
	public int getNumberOfPossibilities();
}
