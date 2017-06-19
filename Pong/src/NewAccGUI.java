import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/* Author: William Huynh
 * Date: June. 2017
 * Description - Graphical user interface for new account page. User creates a new account here
 * Method List:
 * public void actionPerformed (ActionEvent e) - performs action events
 */
public class NewAccGUI extends JFrame implements ActionListener{
	// declare variable for:
	private Container frame; // frame
	private JTextArea nameField, passField, tokField; // create account text areas
	private JButton createAcc, back, clearName, clearPass, clearTok; // buttons
	private Picture background; // backgroud image
	private PlayerList list; // player list object
	private PlayerRecord p[]; // player record array
	public NewAccGUI(PlayerRecord p[]) {
		super ("New Account Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);
		setResizable(false);

		this.p = p; // initialize player record array

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		Color color = new Color(121,135,172); // declare and create colors

		JDialog.setDefaultLookAndFeelDecorated(true);// set jdialog look and feel

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

		// sets image and size/coordinates of background image
		background = new Picture (0,0,new ImageIcon ("Images/createPage.jpg"));
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible(true); // set frame to visible
	} // perform action events
	public void actionPerformed (ActionEvent e){
		if (e.getSource() == clearName){ // clear name text field
			nameField.setText("");
		}
		else if (e.getSource() == clearPass){ // clear password text field
			passField.setText("");
		}
		else if (e.getSource() == clearTok){ //clear token text field
			tokField.setText("");
		}
		else if (e.getSource() == createAcc){ // is create button is pressed
			list = new PlayerList(); // create player list object

			String newUser = "";
			// get user name, password, token input
			String name = nameField.getText();
			String pass = passField.getText();
			int tok = Integer.parseInt(tokField.getText());
			newUser = name+","+pass+",0,0,0,"+tok; // format and test user to player record
			System.out.println(newUser);

			try {
				list.loadFile("Highscores.txt"); // read file
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			p[0].process(newUser); // process player record

			int loc = 
					list.linearSearch(p[0].getUserName()); // searches name

			if (loc < 0){ // if name not found - creates an account
				JOptionPane.showMessageDialog(null, "Account Created");
				list.insert(p[0]);
				list.writeFile();

				setVisible(false); // close frame
				new MenuGUI(this.p); // open MenuGUI while passing player record array
			}
			else{ // if name found - display error message
				JOptionPane.showMessageDialog(null, "Username '"+name+"' already exist");
			}
		} // closes frame and opens PongGUI while passing player record array
		else if (e.getSource() == back){
			setVisible(false);
			new PongGUI(this.p);
		}
	}// self testing
	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Campos","Pass",1,1,1);
		player[1]=new PlayerRecord("Sharan","Pass",1,1,1);
		new NewAccGUI(player);
	}
}
