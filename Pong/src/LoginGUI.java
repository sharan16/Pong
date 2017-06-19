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
 * Description - Graphical user interface for login page. User logs into their existing account here.
 * Method List:
 * public void actionPerformed (ActionEvent e) - performs action events
 */
public class LoginGUI extends JFrame implements ActionListener{

	private Container frame; // declare container
	private JTextArea userLog, passLog;	// declare buttons for welcome page
	private JButton loginBtn, backBtn, userClear, passClear;// declare buttons for login page
	private Picture background; // declare background image
	private PlayerList list; // declare player list object
	private PlayerRecord p[]; // declare player record array
	public LoginGUI(PlayerRecord p[]) {
		super ("Login Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);
		setResizable(false); // set frame to not resizable 

		this.p = p; // initialize player record array

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		Color color = new Color(121,135,172); // declare and create color

		JDialog.setDefaultLookAndFeelDecorated(true);// set jdialog look and feel

		Font font = new Font ("arialbd",Font.BOLD, 28);// declare and create font

		// create text area for user login and set:
		userLog = new JTextArea();
		userLog.setBounds(316, 422, 360, 40); // position/size
		userLog.setFont(font); // font 
		userLog.setBackground(color); // text area background color
		frame.add(userLog); // add text area to frame

		// create text area for password login and set:
		passLog = new JTextArea();
		passLog.setBounds (316, 547, 360, 40); // position/size
		passLog.setFont(font); // font
		passLog.setBackground(color); // text area background color 
		frame.add(passLog); // add text area to frame

		// create button to clear user login 		
		userClear = new JButton (new ImageIcon ("Images/clear.jpg")); // set image
		userClear.setBounds(676, 422, 40, 40); // set position/size
		userClear.addActionListener(this); // make button listen to actions
		frame.add(userClear); // add button to frame

		// create button to clear password login
		passClear = new JButton (new ImageIcon ("Images/clear.jpg")); // set image
		passClear.setBounds(676, 547, 40, 40); // set position/size
		passClear.addActionListener(this); // make button listen to actions
		frame.add(passClear); // add button to frame

		// create button to login and proceed to play screen
		loginBtn = new JButton (new ImageIcon ("Images/login.jpg")); // set image
		loginBtn.setBounds(510, 627, 117, 48); // set position/size
		loginBtn.addActionListener(this); // make button listen to actions
		frame.add(loginBtn); // make button listen to actions

		// create button for back 
		backBtn = new JButton (new ImageIcon ("Images/backBtn.jpg")); //set image
		backBtn.setBounds(393, 627, 117, 48); // position and size
		backBtn.addActionListener(this);// make button listen to actions
		frame.add(backBtn);// make button listen to actions

		// create background and set image and position
		background = new Picture(0,0,new ImageIcon("Images/loginPage.jpg"));
		background.setBounds(0,0,1000,720);
		frame.add(background);

		setVisible(true); // make frame visible 
	}
	// perform action events
	public void actionPerformed (ActionEvent e){
		// set frame to invisible and open PongGUI while passing through player record array
		if (e.getSource() == backBtn){ 
			setVisible(false);
			new PongGUI(this.p);
		} // checks user login inputs and closes frame to open MenuGUI while passing through player record array
		else if (e.getSource() == loginBtn){
			list = new PlayerList(); // create player list

			// get login text
			String name = userLog.getText();
			String pass = passLog.getText();

			// formats player record and tests
			String checkUser = name+","+pass+",0,0,0.0";
			System.out.println(checkUser);

			try {
				list.loadFile("Highscores.txt"); // reads high scores file
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// searches user name and password from file contents
			int locName = list.linearSearch(name);

			int locPass = list.linearSearchPass(pass);

			if (locName < 0 || locPass < 0){ // name or password not found
				JOptionPane.showMessageDialog(null, "Login Information Incorrect.");		
			}
			else{ // name and password found
				JOptionPane.showMessageDialog(null, "Login Sucessful.");
				p[0] = list.getList()[locName]; // set player record array to list found
				setVisible(false); // closes frame
				new MenuGUI(this.p); // open MenuGUI while passing player record array
			}				
		}
	} // self testing
	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Campos","Pass",1,1,1);
		player[1]=new PlayerRecord("Sharan","Pass",1,1,1);
		new LoginGUI(player);
	}
}
