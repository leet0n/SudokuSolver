package sudoku;

import java.util.Stack;

public class Backtracking {
	private static Stack<CellInterface> getSortedGrid(GridInterface grid){
		CellInterface[][] sudoku = grid.getGrid();
		int[][] gridOfPossibilities = grid.getGridOfPossibilities();
		Stack<CellInterface> sortedGrid = new Stack<CellInterface>();		
		
		for(int k = 9; k > 0; k--){
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if (gridOfPossibilities[i][j] == k) sortedGrid.push(sudoku[i][j]);
				}
			}
		}
		return sortedGrid;
	}
	
	private static boolean resolveGrid(Stack<CellInterface> cells){
		
		if (cells.empty()) return true;
		
		CellInterface cell = cells.pop();
		if(cell.getValue() != -1) return resolveGrid(cells);
		
		for(int k = 0; k < 9; k++){
			if (cell.isPossible(k)){
				cell.setValue(k);
				if (resolveGrid(cells)) return true;
			}
		}
		
		cell.setValue(-1);
		cells.push(cell);
		return false;
	}
	
	public static boolean backtracking(GridInterface grid){
		Stack<CellInterface> sortedGrid = getSortedGrid(grid);
		return resolveGrid(sortedGrid);
	}
}
