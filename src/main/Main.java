package main;

public class Main {
	
	private final static int BLOCKLENGTH = 75;
	
	public static void main(String[] args) {
		MainFrame fen = new MainFrame(BLOCKLENGTH);
		fen.setTitle("Sudoku Solver");
	}
}
