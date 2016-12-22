package gui;

import java.awt.*;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = -5716102550654155917L;
	
	private Container container;
	private SudokuPanel panel;
	private SudokuController controller;
	private final int BLOCKLENGTH;
	
	public MainWindow(int blockLength){
		super();
		this.BLOCKLENGTH = blockLength;
		setSize(BLOCKLENGTH*9, BLOCKLENGTH*9);
		this.container = getContentPane();
		this.panel = new SudokuPanel(BLOCKLENGTH);
		container.add(panel);
		controller = new SudokuController(panel);
		setVisible(true);
		
		Dimension dimension = panel.getSize();
		int widthToAdd  = (int)(BLOCKLENGTH*9 - dimension.getWidth());
		int heightToAdd = (int)(BLOCKLENGTH*9 - dimension.getHeight());
		setSize(BLOCKLENGTH*9 + widthToAdd, BLOCKLENGTH*9 + heightToAdd);
		setResizable(false);
	}
}
