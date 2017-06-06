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
import javax.swing.JTextArea;

public class NewAccGUI extends JFrame implements ActionListener{

	Container frame;

	JTextArea nameField, passField, tokField;
	JButton createAcc, back, clearName, clearPass, clearTok;

	Picture background;
	Color color;

	public NewAccGUI() {
		super ("New Account Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		color = new Color(121,135,172); // declare and create colors


		Font font = new Font ("arialbd",Font.BOLD, 28);// declare and create font

		// create text area for user login and set:
		nameField = new JTextArea();
		nameField.setBounds(316, 435, 360, 40); // position/size
		nameField.setFont(font); // font 
		nameField.setBackground(color); // text area background color
		frame.add(nameField); // add text area to frame

		// create text area for user login and set:
		passField = new JTextArea();
		passField.setBounds(316, 530, 360, 40); // position/size
		passField.setFont(font); // font 
		passField.setBackground(color); // text area background color
		frame.add(passField); // add text area to frame

		// create text area for user login and set:
		tokField = new JTextArea();
		tokField.setBounds(316, 621, 360, 40); // position/size
		tokField.setFont(font); // font 
		tokField.setBackground(color); // text area background color
		frame.add(tokField); // add text area to frame

		// create button to clear password login
		clearName = new JButton (new ImageIcon ("Images/clear.jpg")); // set image
		clearName.setBounds(676, 435, 40, 40); // set position/size
		clearName.addActionListener(this); // make button listen to actions
		frame.add(clearName); // add button to frame

		// create button to clear password login
		clearPass = new JButton (new ImageIcon ("Images/clear.jpg")); // set image
		clearPass.setBounds(676, 530, 40, 40); // set position/size
		clearPass.addActionListener(this); // make button listen to actions
		frame.add(clearPass); // add button to frame

		// create button to clear password login
		clearTok = new JButton (new ImageIcon ("Images/clear.jpg")); // set image
		clearTok.setBounds(676, 621, 40, 40); // set position/size
		clearTok.addActionListener(this); // make button listen to actions
		frame.add(clearTok); // add button to frame

		// create button to login and proceed to play screen
		createAcc = new JButton (new ImageIcon ("Images/create.jpg")); // set image
		createAcc.setBounds(513, 667, 125, 48); // set position/size
		createAcc.addActionListener(this); // make button listen to actions
		frame.add(createAcc); // add button to frame

		// create button to login and proceed to play screen
		back = new JButton (new ImageIcon ("Images/backBtnL.jpg")); // set image
		back.setBounds(388, 667, 125, 48); // set position/size
		back.addActionListener(this); // make button listen to actions
		frame.add(back); // add button to frame

		background = new Picture (0,0,new ImageIcon ("Images/createPage.jpg"));
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible(true);
	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == clearName){
			nameField.setText("");
		}
		else if (e.getSource() == clearPass){
			passField.setText("");
		}
		else if (e.getSource() == clearTok){
			tokField.setText("");
		}
		else if (e.getSource() == createAcc){
			setVisible(false);
			new MenuGUI();
		}
		else if (e.getSource() == back){
			setVisible(false);
			new PongGUI();
		}

	}
	public static void main(String[] args) {
		new NewAccGUI();

	}

}
