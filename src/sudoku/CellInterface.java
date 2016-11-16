package sudoku;

public interface CellInterface {
	public int getValue();
	public void setValue(int value);
	public boolean isPossible(int value);
	public void refreshRCB(int value, boolean state);
	public int getNumberOfPossibilities();
}
