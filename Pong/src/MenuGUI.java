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

public class MenuGUI extends JFrame implements ActionListener{
	Container frame;

	JButton play, store, highScores, logout, exit;

	Picture background;
	PlayerRecord p[];
	PlayerList list;
	public MenuGUI(PlayerRecord p[]) {

		super ("Menu Page");// name of frame
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);

		this.p = p;
		System.out.println(this.p[0].toString());

		JDialog.setDefaultLookAndFeelDecorated(true);// set jdialog look and feel

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

			if (p[0].getTokens() == 0){
				JOptionPane.showMessageDialog(null, "Insuffient Tokens.\nPlease Visit the Store.",null,JOptionPane.ERROR_MESSAGE);
			}
			else{
				
				list = new PlayerList();
				try {
					list.loadFile("Highscores.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				p[0].setTokens(p[0].getTokens()-1);
				list.incrementTok(p[0],1,false);
				list.writeFile();
				setVisible(false);

				Object[] gameType = {"Single Player", "Local Multiplayer"};
				Object[] gameDiff = {"Easy", "Medium", "Hard"};

				int result = JOptionPane.showOptionDialog(null, "Choose Game Type",
						null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, gameType, gameType[0]);

				if (result == JOptionPane.YES_OPTION){

					int find = list.linearSearch("Computer");

					PlayerRecord players[] = new PlayerRecord[2];
					players[0] = p[0];
					players[1] = list.getList()[find];

					int result2 = JOptionPane.showOptionDialog(null, "Choose Game Difficulty",
							null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
							null, gameDiff, gameDiff[0]);

					if (result2 == JOptionPane.YES_NO_OPTION){
						new SinglePlayerGame(0,false,players);
					}
					else if (result2 == JOptionPane.NO_OPTION){
						new SinglePlayerGame(1,false,players);
					}
					else{
						new SinglePlayerGame(2,false,players);
					}
				}
				else{
					int find = list.linearSearch("Guest");

					PlayerRecord players[] = new PlayerRecord[2];
					players[0] = p[0];
					players[1] = list.getList()[find];

					int result2 = JOptionPane.showOptionDialog(null, "Choose Game Difficulty",
							null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
							null, gameDiff, gameDiff[0]);

					if (result2 == JOptionPane.YES_NO_OPTION){
						new SinglePlayerGame(0,true,players);
					}
					else if (result2 == JOptionPane.NO_OPTION){
						new SinglePlayerGame(1,true,players);
					}
					else{
						new SinglePlayerGame(2,true,players);
					}
				}
			}
		}
		else if (e.getSource() == store){
			setVisible(false);
			new StoreGUI(this.p);
		}
		else if (e.getSource() == highScores){
			setVisible(false);
			new HighScoresGUI(this.p);
		}
		else if (e.getSource() == logout){
			setVisible (false);
			new PongGUI(this.p);
		}
		else if (e.getSource() == exit){
			System.exit(0);
		}

	}
	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Sharan","Pass",1,1,1);
		player[1]=new PlayerRecord("Campos","Pass",1,1,1);
		new MenuGUI(player);

	}

}
