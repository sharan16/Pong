import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuGUI extends JFrame implements ActionListener{
	Container frame;

	JButton play, store, highScores, logout, exit;

	Picture background;

	public MenuGUI() {

		super ("Menu Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		play = new JButton (new ImageIcon ("Images/play.jpg"));
		play.setBounds(348, 309, 126, 61);
		play.setBorderPainted(false);
		play.addActionListener(this);
		frame.add(play);
		
		store = new JButton (new ImageIcon ("Images/store.jpg"));
		store.setBounds(346, 380, 145, 58);
		store.setBorderPainted(false);
		store.addActionListener(this);
		frame.add(store);
		
		highScores = new JButton (new ImageIcon ("Images/highScores.jpg"));
		highScores.setBounds(346, 450, 342, 66);
		highScores.setBorderPainted(false);
		highScores.addActionListener(this);
		frame.add(highScores);
		
		logout = new JButton (new ImageIcon ("Images/logout.jpg"));
		logout.setBounds(330, 523, 219, 62);
		logout.setBorderPainted(false);
		logout.addActionListener(this);
		frame.add(logout);

		exit = new JButton (new ImageIcon ("Images/exit.jpg"));
		exit.setBounds(333, 590, 117, 47);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		frame.add(exit);
		
		background = new Picture (0,0, new ImageIcon ("Images/main menu.jpg"));
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible(true);
	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == play){
			setVisible(false);
		}
		else if (e.getSource() == store){
			setVisible(false);
		}
		else if (e.getSource() == highScores){
			setVisible(false);
		}
		else if (e.getSource() == logout){
			setVisible (false);
			new PongGUI();
		}
		else if (e.getSource() == exit){
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		new MenuGUI();

	}

}
