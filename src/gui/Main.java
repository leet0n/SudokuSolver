package gui;

public class Main {
	
	private final static int BLOCKLENGTH = 80;
	
	public static void main(String[] args) {
		MainWindow fen = new MainWindow(BLOCKLENGTH);
		fen.setTitle("Sudoku");
	}
}
