package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import main.MainFrame;
import panel.SudokuPanel;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	
	public FileMenu(MainFrame frame, SudokuPanel panel){
		super("File");
		
		JMenuItem emptySudoku = new JMenuItem("Empty sudoku");
		emptySudoku.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.reinitPanel();
			}
		});
		add(emptySudoku);
		
		JMenuItem open = new JMenuItem("Open");
		add(open);
		
		JMenuItem saveAs = new JMenuItem("Save as");
		add(saveAs);
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(
						frame,
						"Really quit ?",
						"Quit application",
						JOptionPane.YES_NO_OPTION);
			   switch (response){
			   case JOptionPane.YES_OPTION:
				   System.exit(0);
			   case JOptionPane.NO_OPTION:
				   break;
			   }
			}
		});
		add(quit);
	}
}
