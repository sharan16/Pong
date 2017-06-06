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

public class PongGUI extends JFrame implements ActionListener {

	Container welPage;
	// declare buttons for welcome page
	JButton welExit, welLogin, welCreate;

	Picture background;
	
	Color color = new Color(121,135,172);
	Font font = new Font ("arialbd",Font.BOLD, 28);// declare and initialize font
	
	public PongGUI() {
		// create and set size of welcome frame
		super ("Welcome Page"); // name of frame
		setSize (1022, 776);
		welPage = getContentPane(); 
		welPage.setLayout (null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		welExit = new JButton (new ImageIcon ("Images/exitBtn.jpg"));
		welExit.setBounds(440, 635, 117, 48);
		welExit.setBorderPainted(false);
		welExit.addActionListener(this);
		welPage.add(welExit);

		welLogin = new JButton (new ImageIcon ("Images/loginBtn.jpg"));
		welLogin.setBounds(64, 403, 428, 197);
		welLogin.addActionListener(this);
		welPage.add(welLogin);

		welCreate = new JButton (new ImageIcon ("Images/createBtn.jpg"));
		welCreate.setBounds(512, 403, 428, 197);
		welCreate.addActionListener(this);
		welPage.add(welCreate);

		background = new Picture(0,0,new ImageIcon("Images/welcome.jpg"));
		background.setBounds(0,0,1000,720);
		welPage.add(background);

		setVisible(true);
	}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == welExit){
			System.exit(0);
		}
		else if (e.getSource() == welLogin){
			setVisible(false);
			new LoginGUI();
		}
		else if (e.getSource() == welCreate){
			setVisible(false);
			new NewAccGUI();
		}

	}
	public static void main(String[] args) {
		new PongGUI();

	}

}
