package gui;

import java.awt.*;
import javax.swing.JPanel;

public class SudokuPanel extends JPanel {

	private static final long serialVersionUID = -6873135922637510147L;

	public SudokuPanel() {
		this.setBackground(Color.white);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.fillRect(0, 0, 8, 810);
		g.fillRect(262, 0, 16, 810);
		g.fillRect(532, 0, 16, 810);
		g.fillRect(802, 0, 8, 810);
		
		g.fillRect(0, 0, 810, 8);
		g.fillRect(0, 262, 810, 16);
		g.fillRect(0, 532, 810, 16);
		g.fillRect(0, 802, 810, 8);
		
		g.fillRect(86, 0, 10, 810);
		g.fillRect(174, 0, 10, 810);
		g.fillRect(356, 0, 10, 810);
		g.fillRect(444, 0, 10, 810);
		g.fillRect(626, 0, 10, 810);
		g.fillRect(714, 0, 10, 810);
		
		g.fillRect(0, 86, 810, 10);
		g.fillRect(0, 174, 810, 10);
		g.fillRect(0, 356, 810, 10);
		g.fillRect(0, 444, 810, 10);
		g.fillRect(0, 626, 810, 10);
		g.fillRect(0, 714, 810, 10);
	}

}
