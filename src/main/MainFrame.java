package main;

import java.awt.*;
import javax.swing.JFrame;
import menubar.SudokuMenuBar;
import panel.SudokuPanel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public MainFrame(int blockLength){
		super();
		setSize(blockLength*9, blockLength*9);
		Container container = getContentPane();
		SudokuPanel panel = new SudokuPanel(blockLength);
		container.add(panel);
		SudokuMenuBar menuBar = new SudokuMenuBar(this, panel);
		setJMenuBar(menuBar);
		
		setVisible(true);
		setResizable(false);
	}
}
