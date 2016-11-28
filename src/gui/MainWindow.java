package gui;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = -5716102550654155917L;
	
	private Container container;
	private SudokuPanel panel;
	
	public MainWindow(int width, int height){
		super();
		this.setSize(width, height);
		this.setResizable(false);
		container = this.getContentPane();
		panel = new SudokuPanel();
		container.add(panel);
	}
}
