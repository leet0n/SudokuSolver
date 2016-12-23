package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import main.MainFrame;

public class ViewMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	private final static int BLOCKLENGTH_SMALL = 60;
	private final static int BLOCKLENGTH_MEDIUM = 75;
	private final static int BLOCKLENGTH_LARGE = 90;

	public ViewMenu(MainFrame frame){
		super("View");
		
		JMenuItem small = new JMenuItem("Small");
		small.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setSize(BLOCKLENGTH_SMALL*9, BLOCKLENGTH_SMALL*9);
			}
		});
		add(small);
		
		JMenuItem medium = new JMenuItem("Medium");
		medium.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setSize(BLOCKLENGTH_MEDIUM*9, BLOCKLENGTH_MEDIUM*9);
			}
		});
		add(medium);
		
		JMenuItem large = new JMenuItem("Large");
		large.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setSize(BLOCKLENGTH_LARGE*9, BLOCKLENGTH_LARGE*9);
			}
		});
		add(large);
	}
}
