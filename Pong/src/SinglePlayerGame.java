import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*; //Library for the visual GUI components

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent; //Library for the event the actionlistener uses
import java.text.*;
//Sharan Somaskanthan
//June 17th 2017
//Description: GUI for the main pong gmae
//Method List
//public void actionPerformed (ActionEvent e){//For every action event
//public void keyPressed (KeyEvent e)  //Key Pressed Event
//public void highScoreUpdate(boolean p1win) {//Highscore Update
//public void playerBot(){ //Computer simulated player
//public void easyMode(){//easy mode load up
//public void mediumMode(){//medium mode load up
//public void hardMode(){//Hard mode load up
public class SinglePlayerGame extends JFrame implements ActionListener, KeyListener{
	private Timer timer;
	private Container frame;
	private Paddle paddle,paddle2;
	private Block blocks[];
	private Ball ball;
	private JLabel bgLbl,p1Score,p2Score;
	private boolean twoPlayer,spawn;
	private String userNames[];
	private JLabel userLbl[];
	PlayerRecord p[];
	public SinglePlayerGame(int mode,boolean twoPlayer,PlayerRecord p[]) {
		super("Pong");
		setSize (1000, 776);    // Set the frame's size
		Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
		this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);
		//Setting the frame
		frame = getContentPane();
		frame.setLayout (null);
		timer= new Timer(10,this);
		spawn=true;
		this.p=p;//Retrieving the player record array
		this.twoPlayer=twoPlayer;

		//Store usernames in an array
		userNames= new String[2];
		userNames[0]=p[0].getUserName();
		userNames[1]=p[1].getUserName();
		paddle=new Paddle(20,50,"Images/paddle.png");
		paddle2=new Paddle(955,50,"Images/paddle.png");

		//Setting up all the JLabels
		userLbl=new JLabel[2];
		userLbl[0]=new JLabel();
		userLbl[1]=new JLabel();
		userLbl[0].setBounds(50,10,100,10);
		userLbl[1].setBounds(910,10,100,10);

		paddle.setUserName(userNames[0]);
		userLbl[0].setText(userNames[0]);
		paddle2.setUserName(userNames[1]);
		userLbl[1].setText(userNames[1]);

		ball= new Ball(paddle.getX()+paddle.getW()+20,paddle.getY()+paddle.getH()/2-10,"Images/Ball.png");
		p1Score=new JLabel("0");
		p1Score.setBounds(430, 25, 72, 72);
		p1Score.setForeground(Color.white);
		p2Score=new JLabel("0");
		p2Score.setBounds(530, 25, 72, 72);
		p2Score.setForeground(Color.white);

		bgLbl= new JLabel(new ImageIcon("Images/BackG.png"));
		bgLbl.setBounds(0,0,1000,720);

		//Depending on the mode the user picked call that according method
		switch(mode){
		case 0:{
			easyMode();
			break;
		}
		case 1:{
			mediumMode();
			break;
		}
		case 2:{
			hardMode();
			break;
		}
		default:{
			easyMode();

		}
		}
		//Adding all elements to the JFrame
		frame.setBackground(Color.black);
		frame.add(p1Score);
		frame.add(p2Score);
		frame.add(userLbl[0]);
		frame.add(userLbl[1]);
		for (int j = 0; j < blocks.length; j++) {
			frame.add(blocks[j].getBodyLbl());
		}

		frame.add(paddle.getBodyLbl());
		frame.add(paddle2.getBodyLbl());
		frame.add(ball.getBodyLbl());
		frame.add(bgLbl);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener (this);//Add a key listener
		//setFocusable (true);
		setFocusTraversalKeysEnabled (false);
		setVisible(true);
		timer.start();//Set timer


	}


	public void actionPerformed (ActionEvent e){//For every action event
		if(paddle.getScore()>1){//If the game is done
			JOptionPane.showMessageDialog(null,paddle.getUserName()+" won\nFinal Score " + paddle.getScore() +"-"+paddle2.getScore());
			highScoreUpdate(true);
			timer.stop();
			dispose();
		}
		else if(paddle2.getScore()>1){//If the game is done
			JOptionPane.showMessageDialog(null,paddle2.getUserName()+" won\nFinal Score " + paddle2.getScore() +"-"+paddle.getScore());
			highScoreUpdate(false);
			timer.stop();
			dispose();
		}
		if(spawn){// If the ball needs to respawn
			ball.setY(paddle.getY()+paddle.getH()/2-10);
			ball.setX(paddle.getX()+paddle.getW()+20);
			ball.setvelX(0);
			ball.setvelY(0);
		}
		//Update paddles,blocks and balls positions
		paddle.update(blocks);
		paddle2.update(blocks);
		ball.update(blocks,paddle,paddle2);
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].update(blocks);
		}

		if(ball.getX()<0){//If the ball left the boundary
			spawn=true;
			paddle2.setScore(paddle2.getScore()+1);
			scoreUpdate();
			//System.out.print(ball.getX()+" 0");
		}
		else if(ball.getX()>980){//If the ball left the boundary
			spawn=true;
			paddle.setScore(paddle.getScore()+1);
			scoreUpdate();
			//System.out.print(ball.getX()+" 980");
		}
		if(!twoPlayer){//If twoplayer is off
			playerBot();//Run the computer simulated player
		}


	}
	public void keyPressed (KeyEvent e)  //Key Pressed Event
	{
		int key=e.getKeyCode();
		if(twoPlayer){//If 2 player is on
			if(key==KeyEvent.VK_UP){
				System.out.print("Up");
				paddle2.setvelY(-10);
			}
			if(key==KeyEvent.VK_DOWN){
				System.out.print("Down");
				paddle2.setvelY(10);
			}
		}

		if(key==KeyEvent.VK_W){
			System.out.print("Up");
			paddle.setvelY(-10);
		}
		if(key==KeyEvent.VK_S){
			System.out.print("Down");
			paddle.setvelY(10);
		}
		if(spawn){
			if(key==KeyEvent.VK_SPACE){
				spawn=false;
				ball.setvelX(2);
			}
		}
	}
	public void keyTyped (KeyEvent e)  //You need to have this for keylistening
	{
	}
	public void keyReleased (KeyEvent e)  //If no keys are pressed
	{
		//Set paddle velocities to 0
		paddle.setvelY(0);
		paddle2.setvelY(0);
	}

	public void playerBot(){ //Computer simulated player
		if(paddle2.getY()>ball.getY()){//If ball is out of range
			paddle2.setvelY(-3);

		}
		else if((paddle2.getY()+paddle2.getH())<ball.getY()){//If ball is out of range
			paddle2.setvelY(3);

		}
		//		else{
		//			paddle2.setvelY(0);
		//		}
	}
	public void highScoreUpdate(boolean p1win) {//Highscore Update
		PlayerList list= new PlayerList();
		try {
			list.loadFile("Highscores.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Increment the score
		list.increment(p[0], p1win);
		list.increment(p[1], !p1win);
		list.writeFile();


	}
	public void easyMode(){//Easy mode load up
		//Make 3 blocks 2 for walls and one for the scoreboard
		blocks= new Block[3];
		blocks[0]= new Block(0,-25,"Images/BlockTwo.png");
		blocks[1]= new Block(0,660,"Images/BlockTwo.png");
		blocks[2]=new  Block(384,25,"Images/ScoreBoard.png");
	}
	public void mediumMode(){//Medium mode load up
		blocks= new Block[4];
		//Make 4 blocks 2 for walls,one for the scoreboard, and one middle stationary obstacle
		blocks[0]= new Block(0,-25,"Images/BlockTwo.png");
		blocks[1]= new Block(0,660,"Images/BlockTwo.png");
		blocks[3]=new Block(361,216,"Images/BlockOne.png");
		blocks[2]=new  Block(384,25,"Images/ScoreBoard.png");
	}
	public void hardMode(){//Hard mode load up
		blocks= new Block[7];
		//Make 3 blocks 2 for walls and one for the scoreboard
		blocks[0]= new Block(0,-25,"Images/BlockTwo.png");
		blocks[1]= new Block(0,660,"Images/BlockTwo.png");
		blocks[2]=new  Block(384,25,"Images/ScoreBoard.png");
		int j=0;
		
		//Create blocks that move
		for (int i = 3; i < blocks.length; i++) {
			blocks[i]=new Block(150+j,50+j,"Images/BlockThree.png",true);
			j+=200;
		}

	}
	public void scoreUpdate(){//Score update method
		p1Score.setText(paddle.getScore()+"");
		p2Score.setText(paddle2.getScore()+"");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlayerRecord[] player=new PlayerRecord[2];
		player[0]=new PlayerRecord("Campos","Pass",1,1,1);
		player[1]=new PlayerRecord("Sharan","Pass",1,1,1);
		new SinglePlayerGame(2,true,player);
	}

}
