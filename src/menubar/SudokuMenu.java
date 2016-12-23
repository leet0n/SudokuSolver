package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import panel.SudokuPanel;

public class SudokuMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	public SudokuMenu(SudokuPanel panel){
		super("Sudoku");
		
		JMenuItem reinitSudoku = new JMenuItem("Reinit");
		reinitSudoku.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.reinitSudoku();
			}
		});
		add(reinitSudoku);
		
		JMenuItem checkSudoku = new JMenuItem("Check");
		add(checkSudoku);
		
		JMenuItem solveSudoku = new JMenuItem("Solve");
		add(solveSudoku);
	}
}
