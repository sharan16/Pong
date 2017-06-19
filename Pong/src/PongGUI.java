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
/* Author: William Huynh
 * Date: June. 2017
 * Description - Graphical user interface for opening page. User chooses to login or create an account here
 * Method List:
 * public void actionPerformed (ActionEvent e) - performs action events
 */
public class PongGUI extends JFrame implements ActionListener {
	// declare variables for:
	private Container welPage; // frame
	private JButton welExit, welLogin, welCreate; // buttons
	private Picture background; // backgroud image
	private Color color = new Color(121,135,172); // declare and create color
	private Font font = new Font ("arialbd",Font.BOLD, 28);// declare and createfont
	private PlayerRecord p[]; // player list object
	public PongGUI(PlayerRecord p[]) {
		// create and set size of welcome frame
		super ("Welcome Page"); // name of frame
		setSize (1022, 776);
		welPage = getContentPane(); 
		welPage.setLayout (null);
		setResizable(false);

		this.p = p; // initializes player record array

		//center GUI 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// create and set image, size, coordinates, action event to exit button
		welExit = new JButton (new ImageIcon ("Images/exitBtn.jpg"));
		welExit.setBounds(440, 635, 117, 48);
		welExit.setBorderPainted(false);
		welExit.addActionListener(this);
		welPage.add(welExit);

		// create and set image, size, coordinates, action event to login button
		welLogin = new JButton (new ImageIcon ("Images/loginBtn.jpg"));
		welLogin.setBounds(64, 403, 428, 197);
		welLogin.addActionListener(this);
		welPage.add(welLogin);

		// create and set image, size, coordinates, action event to create button
		welCreate = new JButton (new ImageIcon ("Images/createBtn.jpg"));
		welCreate.setBounds(512, 403, 428, 197);
		welCreate.addActionListener(this);
		welPage.add(welCreate);

		// create and set image and coordinates/size of background image
		background = new Picture(0,0,new ImageIcon("Images/welcome.jpg"));
		background.setBounds(0,0,1000,720);
		welPage.add(background);

		setVisible(true); // set frame to visible
	}// perform action events
	public void actionPerformed (ActionEvent e){
		if (e.getSource() == welExit){ // exit program
			System.exit(0);
		} // closes frame and opens LoginGUI while passing player record array
		else if (e.getSource() == welLogin){
			setVisible(false);
			new LoginGUI(this.p);
		}// closes frame and opens NewAccGUI while passing player record array
		else if (e.getSource() == welCreate){
			setVisible(false);
			new NewAccGUI(this.p);
		}
	} //self testing
	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Campos","Pass",1,1,1);
		player[1]=new PlayerRecord("Sharan","Pass",1,1,1);
		new PongGUI(player);
	}
}
