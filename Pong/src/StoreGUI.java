import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StoreGUI extends JFrame implements ActionListener{

	Container frame;

	JButton buy1, buy5, buyCustom, back;

	JLabel user, tokens;

	Picture background;

	PlayerList list;
	PlayerRecord p[];
	public StoreGUI(PlayerRecord p[]) {
		super ("Buy tokens here");
		setSize (1022, 776); // set size of frame
		frame = getContentPane(); 
		frame.setLayout (null);

		this.p = p;
		
		Color color = new Color(121,135,172); // declare and create colors

		Font font = new Font ("arialbd",Font.BOLD, 38);// declare and create font

		// center GUI window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		user = new JLabel (p[0].getUserName());
		user.setBounds(530, 76, 500, 200);
		user.setFont(font);
		user.setForeground(color);
		frame.add(user);

		tokens = new JLabel (p[0].getTokens()+"");
		tokens.setBounds(530, 147, 200, 200);
		tokens.setFont(font);
		tokens.setForeground(color);
		frame.add(tokens);
		System.out.println(p[0].getTokens());

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
		
		list = new PlayerList();
		try {
			list.loadFile("Highscores.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (e.getSource() == back){
			setVisible(false);
			new MenuGUI(this.p);
		}
		else if (e.getSource() == buy1){	
			
			p[0].setTokens(p[0].getTokens()+1);
			tokens.setText(String.valueOf(p[0].getTokens()));
			
			list.incrementTok(p[0],1);
			list.writeFile();
		}
		else if (e.getSource() == buy5){
			p[0].setTokens(p[0].getTokens()+5);
			tokens.setText(String.valueOf(p[0].getTokens()));
			
			list.incrementTok(p[0],5);
			list.writeFile();
		}
		else if (e.getSource() == buyCustom){
			int amount = 0;
			try{
				amount = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tokens to buy:"));		
			}
			catch(InputMismatchException exception){
				JOptionPane.showMessageDialog(null, "Please enter a whole number of tokens");
			}
			
			p[0].setTokens(p[0].getTokens()+amount);
			tokens.setText(String.valueOf(p[0].getTokens()));
			
			list.incrementTok(p[0],amount);
			list.writeFile();
		}
	}

	public static void main(String[] args) {
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Campos","Pass",1,1,1);
		player[1]=new PlayerRecord("Sharan","Pass",1,1,1);
		new StoreGUI(player);
	}

}
