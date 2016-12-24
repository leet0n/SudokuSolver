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

public class AboutMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public AboutMenu(MainFrame frame){
		super("About");
		
		JMenuItem credits = new JMenuItem("Credits");
		credits.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Developped by Olivier Roques, 2016\n"
						+ "olivier.roques@telecom-paristech.fr", "Credits", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		credits.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		add(credits);
	}
}