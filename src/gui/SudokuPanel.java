package gui;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuPanel extends JPanel {

	private static final long serialVersionUID = -6873135922637510147L;
	private JTextField[][] gridOfTextField = new JTextField[9][9];
	private final int BLOCKLENGTH;

	public SudokuPanel(int blockLength) {
		super(new GridLayout(3, 3));
		this.BLOCKLENGTH = blockLength;
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black, 3));
		
		JPanel block;
		for(int k = 0; k < 3; k++){
			for(int l = 0; l < 3; l++){
				block = new JPanel(new GridLayout(3, 3));
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						gridOfTextField[3*k + i][3*l + j] = new JTextField(1);
						gridOfTextField[3*k + i][3*l + j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
						gridOfTextField[3*k + i][3*l + j].setHorizontalAlignment(JTextField.CENTER);
						gridOfTextField[3*k + i][3*l + j].setFont(new Font("SansSerif", Font.BOLD, BLOCKLENGTH));
						block.add(gridOfTextField[3*k + i][3*l + j]);
					}
				}
				block.setBorder(BorderFactory.createLineBorder(Color.black, 3));
				add(block);
			}
		}
	}
	
	public JTextField[][] getGrid(){
		return gridOfTextField;
	}
}
