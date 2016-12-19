package gui;

public class Main {
	
	private final static int WIDTH = 720;
	private final static int HEIGHT = 720;
	
	public static void main(String[] args) {
		MainWindow fen = new MainWindow(WIDTH, HEIGHT);
		fen.setTitle("Sudoku");
		fen.setVisible(true);
	}

}
