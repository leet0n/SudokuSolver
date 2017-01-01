package menubar;

import javax.swing.JMenuBar;

import main.MainFrame;
import panel.SudokuPanel;

public class SudokuMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public SudokuMenuBar(MainFrame frame, SudokuPanel panel){
		super();
		add(new FileMenu(frame, panel));
		add(new SudokuMenu(frame, panel));
		add(new ViewMenu(frame));
		add(new HelpMenu(frame));
	}
}
