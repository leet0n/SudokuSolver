package sudoku;

public class Cell implements CellInterface {
	
	private int value;
	private int posR;
	private int posC;
	private GridInterface masterGrid;
	
	
	public Cell(int value, int posR, int posC, GridInterface masterGrid){
		this.value = value;
		this.posR = posR;
		this.posC = posC;
		this.masterGrid = masterGrid;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setValue(int value) {
		if (this.value != -1) masterGrid.refreshRCB(posR, posC, this.value, false);
		this.value = value;
		if (value != -1) masterGrid.refreshRCB(posR, posC, value, true);
	}

	@Override
	public boolean isPossible(int value) {
		boolean[] row = masterGrid.getRow(posR);
		boolean[] column = masterGrid.getColumn(posC);
		boolean[] block = masterGrid.getBlock(posR, posC);
		return (!row[value] && !column[value] && !block[value]);
	}

	@Override
	public int getNumberOfPossibilities() {
		int counter = 0;
		for(int k = 0; k < 9; k++){
			if (isPossible(k)) counter++;
		}
		return counter;
	}

}
