import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*; //Library for the visual GUI components
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent; //Library for the event the actionlistener uses
import java.text.*;


public class SinglePlayerGame extends JFrame implements ActionListener, KeyListener{
	private Timer timer;
	private Container frame;
	private Paddle paddle,paddle2;
	private Block blocks[];
	private Ball ball;
	private JLabel bgLbl,p1Score,p2Score;
	private boolean twoPlayer,spawn;
	public SinglePlayerGame() {
		super("Pong");
		setSize (1000, 776);    // Set the frame's size
		Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
		this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);
		//Setting the frame
		frame = getContentPane();
		frame.setLayout (null);
		timer= new Timer(10,this);
		spawn=true;
		twoPlayer=false;
		paddle=new Paddle(20,50,"Images/paddle.png");
		paddle2=new Paddle(955,50,"Images/paddle.png");
		ball= new Ball(paddle.getX()+paddle.getW()+20,paddle.getY()+paddle.getH()/2-10,"Images/Ball.png");
		p1Score=new JLabel("0");
		p1Score.setBounds(430, 25, 72, 72);
		p2Score=new JLabel("0");
		p2Score.setBounds(530, 25, 72, 72);
		bgLbl= new JLabel(new ImageIcon("Images/BackG.png"));
		bgLbl.setBounds(0,0,1000,720);
		hardMode();
		frame.setBackground(Color.black);
		frame.add(p1Score);
		frame.add(p2Score);
		for (int j = 0; j < blocks.length; j++) {
			frame.add(blocks[j].getBodyLbl());
		}
		
		frame.add(paddle.getBodyLbl());
		frame.add(paddle2.getBodyLbl());
		frame.add(ball.getBodyLbl());
		frame.add(bgLbl);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener (this);
		//setFocusable (true);
		setFocusTraversalKeysEnabled (false);
		setVisible(true);
		timer.start();
		

	}


	public void actionPerformed (ActionEvent e){
		if(spawn){
			ball.setY(paddle.getY()+paddle.getH()/2-10);
			ball.setX(paddle.getX()+paddle.getW()+20);
			ball.setvelX(0);
			ball.setvelY(0);
		}
		paddle.update(blocks);
		paddle2.update(blocks);
		ball.update(blocks,paddle,paddle2);
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].update(blocks);
		}
		if(ball.getX()<0){
			spawn=true;
			paddle2.setScore(paddle2.getScore()+1);
			scoreUpdate();
			//System.out.print(ball.getX()+" 0");
		}
		else if(ball.getX()>980){
			spawn=true;
			paddle.setScore(paddle.getScore()+1);
			scoreUpdate();
			//System.out.print(ball.getX()+" 980");
		}
		if(!twoPlayer){
			playerBot();
		}
		
	}
	public void keyPressed (KeyEvent e)  //Key Pressed Event
	{
		int key=e.getKeyCode();
		if(twoPlayer){
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
			System.out.print("Up");
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
		paddle.setvelY(0);
		paddle2.setvelY(0);
	}

	public void playerBot(){
		if(paddle2.getY()>ball.getY()){
			paddle2.setvelY(-3);
		}
		else if((paddle2.getY()+paddle2.getH())<ball.getY()){
			paddle2.setvelY(3);
		}
	}
	public void easyMode(){
		blocks= new Block[3];
		blocks[0]= new Block(0,-25,"Images/BlockTwo.png");
		blocks[1]= new Block(0,660,"Images/BlockTwo.png");
		blocks[2]=new  Block(384,25,"Images/ScoreBoard.png");
	}
	public void mediumMode(){
		blocks= new Block[4];
		blocks[0]= new Block(0,-25,"Images/BlockTwo.png");
		blocks[1]= new Block(0,660,"Images/BlockTwo.png");
		blocks[3]=new Block(361,216,"Images/BlockOne.png");
		blocks[2]=new  Block(384,25,"Images/ScoreBoard.png");
	}
	public void hardMode(){
		blocks= new Block[7];
		blocks[0]= new Block(0,-25,"Images/BlockTwo.png");
		blocks[1]= new Block(0,660,"Images/BlockTwo.png");
		blocks[2]=new  Block(384,25,"Images/ScoreBoard.png");
		int j=0;

		for (int i = 3; i < blocks.length; i++) {
			blocks[i]=new Block(150+j,50+j,"Images/BlockThree.png",true);
			j+=200;
		}

	}
	public void scoreUpdate(){
		p1Score.setText(paddle.getScore()+"");
		p2Score.setText(paddle2.getScore()+"");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SinglePlayerGame();
	}

}
