package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import main.MainFrame;

public class HelpMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public HelpMenu(MainFrame frame){
		super("Help");
		
		JMenuItem instructions = new JMenuItem("Instructions");
		instructions.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Are considered valid all numbers from 1 to 9.\n"
						+ "Don't hesitate to use shortcuts:\n"
						+ "CTRL + N: Clear the current sudoku and initialize a new empty one.\n"
						+ "CTRL + O: Open an existing sudoku.\n"
						+ "CTRL + S: Save the current sudoku.\n"
						+ "CTRL + MAJ + S: Save the current sudoku into a text file with the given name.\n"
						+ "CTRL + I: Clear the current sudoku to return to the orignal one.\n"
						+ "CTRL + T: Test whether or not there are any errors and if a given grid is the solution.\n"
						+ "CTRL + R: Resolve the sudoku (with the backtracking algorithm).\n"
						+ "CTRL + 1, 2, 3: Change the size of the main window, from small to large.\n"
						+ "CTRL + Q: Quit the application.", "Instructions & Shortcuts", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		add(instructions);
		
		JMenuItem about = new JMenuItem("About SudokuSolver");
		about.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Developped by Olivier Roques, 2016\n"
						+ "olivier.roques@telecom-paristech.fr", "About SudokuSolver", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		add(about);
	}
}