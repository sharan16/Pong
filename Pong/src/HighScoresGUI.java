import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
/* Author: William Huynh
 * Date: June. 2017
 * Description - Graphical user interface for high scores page. User can load, sort, and search high scores from here.
 * Method List:
 * public void actionPerformed (ActionEvent e) - performs action events
 */
public class HighScoresGUI extends JFrame implements ActionListener {

	// delcare variable for:
	private Container frame; // GUI frame
	private JTextArea record; // text area to place high scores
	private JButton back, load, sort, search; // buttons for back, load, sort, and search
	private String inputLoad; // string input for load file 
	private Picture background; // background image
	private PlayerList list; // playerlist object
	private PlayerRecord p[]; // player record array
	public HighScoresGUI(PlayerRecord p[]) { // constructor passes through player record array
		super ("Highscores Page");
		setSize (1022, 776); // set size of frame
		frame = getContentPane();
		frame.setLayout (null);
		setResizable(false); // set frame resizable to false

		this.p = p; // initialize player record array

		Color lightBlue = new Color(121,135,172); // declare and create colors
		Color darkBlue = new Color (22,39,86);

		Font font = new Font ("arialbd",Font.BOLD, 18);// declare and create font

		JDialog.setDefaultLookAndFeelDecorated(true);// set jdialog look and feel

		// create and set image, size, coordinates, action event to back button
		back = new JButton (new ImageIcon ("Images/backBtn.jpg"));
		back.setBounds(10, 10, 117, 48);
		back.addActionListener(this);
		frame.add(back);

		// create and set image, size, coordinates, action event to load file button
		load = new JButton (new ImageIcon ("Images/load.jpg"));
		load.setBounds(205, 650, 200, 48);
		load.addActionListener(this);
		frame.add(load);

		// create and set image, size, coordinates, action event to sort high scores button
		sort = new JButton (new ImageIcon ("Images/sort.jpg"));
		sort.setBounds(404, 650, 200, 48);
		sort.addActionListener(this);
		frame.add(sort);

		// create and set image, size, coordinates, action event to search user button
		search = new JButton (new ImageIcon ("Images/search.jpg"));
		search.setBounds(604, 650, 200, 48);
		search.addActionListener(this);
		frame.add(search);

		// create and set size, coordinates, color, font of high scores text area
		record = new JTextArea ();
		record.setBounds(205, 125, 599, 500);
		record.setForeground(darkBlue);
		record.setFont(font);
		record.setBackground(lightBlue);
		record.setEditable(false);
		frame.add(record);

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// create and set image and coordinates of background picture
		background = new Picture (0,0,new ImageIcon ("Images/highScoresPage.jpg")); 
		background.setBounds(0, 0, 1000, 720);
		frame.add(background);

		setVisible (true); // set frame to visible
	}
	// performs action events
	public void actionPerformed (ActionEvent e){
		// sets frame invisible and opens MenuGUI while passing through player record array
		if (e.getSource() == back){ 
			setVisible(false);
			new MenuGUI(this.p);
		}
		// prompts user for file to load and sets file contents to high scores text area		
		else if (e.getSource() == load){
			list = new PlayerList();
			inputLoad = JOptionPane.showInputDialog("Enter file to load:","Highscores.txt");

			try {
				list.loadFile(inputLoad);

				int lines = 0;

				// setting up the file reader to read the file
				BufferedReader file = new BufferedReader(new FileReader(inputLoad));

				// while there is info to read
				while(file.readLine() != null)
				{
					// increment the lines variable by 1
					lines++;
				}

				String records[] = new String [lines];

				for (int i = 0; i < lines ; i++){
					records[i] = p[i].getUserName() + "\t\t" + p[i].getWins() + "\t" + p[i].getLosses() +"\t"+p[i].getWinPercentage();
					record.setText("User:\t\tWins:\tLosses:\tW/L:\n"+records[i]);
				}


			} catch (IOException e1) { // catches file errors
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Please try another file.",null, JOptionPane.ERROR_MESSAGE);
			}
		} // sorts high scores from ascending order and by name 
		else if (e.getSource() == sort){
			list.insertionSort("Up", "name");
		} // prompt user for which user to search and displays user information if found
		else if (e.getSource() == search){
			String findUser = JOptionPane.showInputDialog(null, "Enter username to search:", "Search",
					JOptionPane.PLAIN_MESSAGE);
			int loc = list.linearSearch(findUser);
			if (loc < 0){
				JOptionPane.showMessageDialog(null, "User: "+findUser+" does not exist.",null,JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Username: "+list.getList()[loc].getUserName()+"\nWins: "+list.getList()[loc].getWins()
					+"\nLosses: "+list.getList()[loc].getLosses()+"\nW/L: "+list.getList()[loc].getWinPercentage(),null,JOptionPane.PLAIN_MESSAGE);
		}
	}
	// self testing 
	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Campos","Pass",1,1,1);
		player[1]=new PlayerRecord("Sharan","Pass",1,1,1);
		new HighScoresGUI(player);
	}

}
