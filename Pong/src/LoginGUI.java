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

public class LoginGUI extends JFrame implements ActionListener{

	Container frame; // declare container

	// declare buttons for welcome page
	JTextArea userLog, passLog;

	// declare buttons for login page
	JButton loginBtn, backBtn, userClear, passClear;
	Picture background;

	public LoginGUI() {
		super ("Login Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		Color color = new Color(121,135,172); // declare and create color

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
		userClear = new JButton (new ImageIcon ("clear.jpg")); // set image
		userClear.setBounds(676, 422, 40, 40); // set position/size
		userClear.addActionListener(this); // make button listen to actions
		frame.add(userClear); // add button to frame
		
		// create button to clear password login
		passClear = new JButton (new ImageIcon ("clear.jpg")); // set image
		passClear.setBounds(676, 547, 40, 40); // set position/size
		passClear.addActionListener(this); // make button listen to actions
		frame.add(passClear); // add button to frame

		// create button to login and proceed to play screen
		loginBtn = new JButton (new ImageIcon ("login.jpg")); // set image
		loginBtn.setBounds(510, 627, 117, 48); // set position/size
		loginBtn.addActionListener(this); // make button listen to actions
		frame.add(loginBtn); // add button to frame
		
		// 
		backBtn = new JButton (new ImageIcon ("backBtn.jpg"));
		backBtn.setBounds(393, 627, 117, 48);
		backBtn.addActionListener(this);
		frame.add(backBtn);

		background = new Picture(0,0,new ImageIcon("loginPage.jpg"));
		background.setBounds(0,0,1000,720);
		frame.add(background);

		setVisible(true);
	}
	public void actionPerformed (ActionEvent e){
		if (e.getSource() == backBtn){
			setVisible(false);
			new PongGUI();
		}
		else if (e.getSource() == loginBtn){
			setVisible(false);
			new MenuGUI();
		}

	}
	public static void main(String[] args) {
		new LoginGUI();

	}

}
