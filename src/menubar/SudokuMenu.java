package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import main.MainFrame;
import panel.SudokuPanel;

public class SudokuMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	public SudokuMenu(MainFrame frame, SudokuPanel panel){
		super("Sudoku");
		
		JMenuItem reinitSudoku = new JMenuItem("Reinit");
		reinitSudoku.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.reinitSudoku();
			}
		});
		reinitSudoku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		add(reinitSudoku);
		
		JMenuItem checkSudoku = new JMenuItem("Check");
		checkSudoku.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.checkSudoku();
			}
		});
		checkSudoku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		add(checkSudoku);
		
		JMenuItem solveSudoku = new JMenuItem("Solve");
		solveSudoku.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.resolveSudoku();
			}
		});
		solveSudoku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		add(solveSudoku);
	}
}
