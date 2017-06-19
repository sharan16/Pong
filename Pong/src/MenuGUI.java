import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/* Author: William Huynh
 * Date: June. 2017
 * Description - Graphical user interface for menu page. User chooses what to do here (play, check scores, buy tokens, logout)
 * Method List:
 * public void actionPerformed (ActionEvent e) - performs action events
 */
public class MenuGUI extends JFrame implements ActionListener{
	// declare variables for:
	private Container frame; // frame

	private JButton play, store, highScores, logout, exit; // buttons

	private Picture background; // background image
	private PlayerRecord p[]; // player record array
	private PlayerList list; // player list object
	public MenuGUI(PlayerRecord p[]) {
		super ("Menu Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);
		setResizable(false); // set frame to not resizable 

		this.p = p; // initialize player record array

		JDialog.setDefaultLookAndFeelDecorated(true);// set jdialog look and feel

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// create and set image, size, coordinates, action event to play button
		play = new JButton (new ImageIcon ("Images/play.jpg"));
		play.setBounds(348, 309, 126, 61);
		play.setBorderPainted(false);
		play.addActionListener(this);
		frame.add(play);

		// create and set image, size, coordinates, action event to store button
		store = new JButton (new ImageIcon ("Images/store.jpg"));
		store.setBounds(346, 380, 145, 58);
		store.setBorderPainted(false);
		store.addActionListener(this);
		frame.add(store);

		// create and set image, size, coordinates, action event to high scores button
		highScores = new JButton (new ImageIcon ("Images/highScores.jpg"));
		highScores.setBounds(346, 450, 342, 66);
		highScores.setBorderPainted(false);
		highScores.addActionListener(this);
		frame.add(highScores);

		// create and set image, size, coordinates, action event to log out button
		logout = new JButton (new ImageIcon ("Images/logout.jpg"));
		logout.setBounds(330, 523, 219, 62);
		logout.setBorderPainted(false);
		logout.addActionListener(this);
		frame.add(logout);

		// create and set image, size, coordinates, action event to exit button
		exit = new JButton (new ImageIcon ("Images/exit.jpg"));
		exit.setBounds(333, 590, 117, 47);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		frame.add(exit);

		// create and set image, size, coordinaates of background image
		background = new Picture (0,0, new ImageIcon ("Images/main menu.jpg"));
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible(true); // set frame to visible
	}// perform action events
	public void actionPerformed (ActionEvent e){
		// if play button is pressed
		if (e.getSource() == play){
			// if user does not have tokens to play
			if (p[0].getTokens() == 0){
				// display error message
				JOptionPane.showMessageDialog(null, "Insuffient Tokens.\nPlease Visit the Store.",null,JOptionPane.ERROR_MESSAGE);
			}
			else{			
				list = new PlayerList(); // creates player list object
				try {
					list.loadFile("Highscores.txt"); // reads file
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// subtracts 1 token from player record array
				p[0].setTokens(p[0].getTokens()-1);
				list.incrementTok(p[0],1,false);
				list.writeFile();
				setVisible(false); // set frame to invisible
				// create objects for game type and difficulty 
				Object[] gameType = {"Single Player", "Local Multiplayer"};
				Object[] gameDiff = {"Easy", "Medium", "Hard"};

				// prompt user which game type
				int result = JOptionPane.showOptionDialog(null, "Choose Game Type",
						null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, gameType, gameType[0]);

				if (result == JOptionPane.YES_OPTION){// if user picks single player

					int find = list.linearSearch("Computer"); // searches Computer user 

					PlayerRecord players[] = new PlayerRecord[2]; // create 2 player records
					players[0] = p[0]; // set player 1 to passed through player record
					players[1] = list.getList()[find]; // set player 2 to computer

					// prompt user for game difficulty 
					int result2 = JOptionPane.showOptionDialog(null, "Choose Game Difficulty",
							null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
							null, gameDiff, gameDiff[0]);

					if (result2 == JOptionPane.YES_NO_OPTION){ // easy mode
						new SinglePlayerGame(0,false,players);
					}
					else if (result2 == JOptionPane.NO_OPTION){ // medium mode
						new SinglePlayerGame(1,false,players);
					}
					else{
						new SinglePlayerGame(2,false,players); // hard mode
					}
				}
				else{ // if user chooses local multiplayer 
					int find = list.linearSearch("Guest"); // finds Guest user

					PlayerRecord players[] = new PlayerRecord[2]; // creates 2 player records
					players[0] = p[0];// set player 1 to passed through player record
					players[1] = list.getList()[find];// set player 2 to guest

					// prompt user for game difficulty 
					int result2 = JOptionPane.showOptionDialog(null, "Choose Game Difficulty",
							null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
							null, gameDiff, gameDiff[0]);

					if (result2 == JOptionPane.YES_NO_OPTION){ // easy mode
						new SinglePlayerGame(0,true,players);
					}
					else if (result2 == JOptionPane.NO_OPTION){ // medium mode
						new SinglePlayerGame(1,true,players);
					}
					else{
						new SinglePlayerGame(2,true,players); // hard mode
					}
				}
			}
		}// closes frame and opens StoreGUI while passing player record array
		else if (e.getSource() == store){
			setVisible(false);
			new StoreGUI(this.p);
		}// closes frame and opens high scores GUI while passing player record array
		else if (e.getSource() == highScores){
			setVisible(false);
			new HighScoresGUI(this.p);
		}// closes frame and opens PongGUI while passing player record array
		else if (e.getSource() == logout){
			setVisible (false);
			new PongGUI(this.p);
		}// closes frame and exits program
		else if (e.getSource() == exit){
			System.exit(0);
		}
	}// self testing
	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Sharan","Pass",1,1,1);
		player[1]=new PlayerRecord("Campos","Pass",1,1,1);
		new MenuGUI(player);
	}
}
