import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class HighScoresGUI extends JFrame implements ActionListener {

	Container frame;

	JTextArea record;
	JScrollPane scroller;

	JButton back, load, save, sort, search;

	String inputLoad, inputSave;

	Object[] possibilities = {"Rank", "Name", "User"}; // create button objects

	Picture background;
	public HighScoresGUI() {
		super ("Highscores Page");
		setSize (1022, 776); // set size of frame
		frame = getContentPane();
		frame.setLayout (null);

		Color lightBlue = new Color(121,135,172); // declare and create colors
		Color darkBlue = new Color (22,39,86);

		Font font = new Font ("arialbd",Font.BOLD, 18);// declare and create font

		JDialog.setDefaultLookAndFeelDecorated(true);// set jdialog look and feel

		UIManager.put("OptionPane.okButtonText", "Select"); // set text of panel button to 'Select'

		back = new JButton (new ImageIcon ("Images/backBtn.jpg"));
		back.setBounds(10, 10, 117, 48);
		back.addActionListener(this);
		frame.add(back);

		load = new JButton (new ImageIcon ("Images/load.jpg"));
		load.setBounds(85, 650, 200, 48);
		load.addActionListener(this);
		frame.add(load);

		save = new JButton (new ImageIcon ("Images/save.jpg"));
		save.setBounds(300, 650, 200, 48);
		save.addActionListener(this);
		frame.add(save);

		sort = new JButton (new ImageIcon ("Images/sort.jpg"));
		sort.setBounds(515, 650, 200, 48);
		sort.addActionListener(this);
		frame.add(sort);

		search = new JButton (new ImageIcon ("Images/search.jpg"));
		search.setBounds(730, 650, 200, 48);
		search.addActionListener(this);
		frame.add(search);

		record = new JTextArea ("Rank:\t\tName:\t\tWins:");
		record.setBounds(85, 125, 843, 500);
		record.setForeground(darkBlue);
		record.setFont(font);
		record.setBackground(lightBlue);
		record.setEditable(false);
		frame.add(record);

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		background = new Picture (0,0,new ImageIcon ("Images/highScoresPage.jpg")); 
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible (true);
	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == back){
			setVisible(false);
			new MenuGUI();
		}
		else if (e.getSource() == load){
			inputLoad = JOptionPane.showInputDialog("Enter file to load:");
		}
		else if (e.getSource() == save){
			inputSave = JOptionPane.showInputDialog("Enter file name to save as:");
		}
		else if (e.getSource() == search){
			String choice = (String) JOptionPane.showInputDialog(null, "Search by:", "Search Options",
					JOptionPane.PLAIN_MESSAGE,null, possibilities, "Rank");
		}
	}

	public static void main(String[] args) {
		new HighScoresGUI();
	}

}
