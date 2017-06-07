import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StoreGUI extends JFrame implements ActionListener{

	Container frame;

	JButton buy1, buy5, buyCustom, back;

	JLabel user, tokens;

	Picture background;

	NewAccGUI info = new NewAccGUI();

	String j = info.nameField.getText();
	String i = info.tokField.getText();
	public StoreGUI() {
		super ("Buy tokens here");
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);

		Color color = new Color(121,135,172); // declare and create colors

		Font font = new Font ("arialbd",Font.BOLD, 38);// declare and create font

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		user = new JLabel (j);
		user.setBounds(530, 76, 200, 200);
		user.setFont(font);
		user.setForeground(color);
		frame.add(user);

		tokens = new JLabel (i);
		tokens.setBounds(530, 147, 200, 200);
		tokens.setFont(font);
		tokens.setForeground(color);
		frame.add(tokens);

		buy1 = new JButton (new ImageIcon ("Images/buyBtn.jpg"));
		buy1.setBounds(135, 559, 100, 42);
		buy1.addActionListener(this);
		frame.add(buy1);

		buy5 = new JButton (new ImageIcon ("Images/buyBtn.jpg"));
		buy5.setBounds(445, 559, 100, 42);
		buy5.addActionListener(this);
		frame.add(buy5);

		buyCustom = new JButton (new ImageIcon ("Images/buyBtn.jpg"));
		buyCustom.setBounds(765, 559, 100, 42);
		buyCustom.addActionListener(this);
		frame.add(buyCustom);

		back = new JButton (new ImageIcon ("Images/backBtn.jpg"));
		back.setBounds(5, 5, 117, 48);
		back.addActionListener(this);
		frame.add(back);

		background = new Picture (0,0, new ImageIcon ("Images/storePage.jpg"));
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible(true);
	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == back){
			setVisible(false);
			new MenuGUI();
		}

	}

	public static void main(String[] args) {
		new StoreGUI();
	}

}
