package sudoku;

public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		Grid sudokuTest = new Grid();
		sudokuTest.initFromFile("sudokuWC");
		if (Backtracking.backtracking(sudokuTest)) sudokuTest.printGrid();
		else System.out.println("Pas de solution");		
		
		long endTime = System.currentTimeMillis();
		System.out.println("\nThat took " + (endTime - startTime) + " milliseconds\n");
		
		sudokuTest.saveGrid("saveTest");
		sudokuTest.initFromFile("saveTest");
		sudokuTest.printGrid();
	}

}
