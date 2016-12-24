package sudoku;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest{

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		Grid sudokuTest = new Grid();
		
		try{
			sudokuTest.initFromFile("data/sudokuWC.txt");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		if (Backtracking.backtracking(sudokuTest)) sudokuTest.printGrid();
		else System.out.println("Pas de solution");		
		
		long endTime = System.currentTimeMillis();
		System.out.println("\nThat took " + (endTime - startTime) + " milliseconds\n");
	}

}
