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
		emptySudoku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		add(emptySudoku);
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.initSudoku();
			}
		});
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		add(open);
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.saveSudoku();
			}
		});
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		add(save);
		
		JMenuItem saveAs = new JMenuItem("Save as");
		saveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.saveSudokuAs();
			}
		});
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));
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
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		add(quit);
	}
}
