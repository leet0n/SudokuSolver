package sudoku;

public class Cell implements CellInterface {
	
	private int value;
	private final int POSR;
	private final int POSC;
	private final GridInterface MASTERGRID;
	
	
	public Cell(int value, int posR, int posC, GridInterface masterGrid){
		this.value = value;
		this.POSR = posR;
		this.POSC = posC;
		this.MASTERGRID = masterGrid;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setValue(int value) {
		if (this.value != -1) MASTERGRID.refreshRCB(POSR, POSC, this.value, false);
		this.value = value;
		if (value != -1) MASTERGRID.refreshRCB(POSR, POSC, value, true);
	}

	@Override
	public boolean isPossible(int value) {
		boolean[] row = MASTERGRID.getRow(POSR);
		boolean[] column = MASTERGRID.getColumn(POSC);
		boolean[] block = MASTERGRID.getBlock(POSR, POSC);
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
