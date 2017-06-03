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


public class Game extends JFrame implements ActionListener, KeyListener{
	private Timer timer;
	private Container frame;
	private Paddle paddle,paddle2;
	private Thing blocks[];
	private Ball ball;
	
	public Game() {
		super("Pong");
		setSize (1022, 776);    // Set the frame's size
		Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
		this.setLocation (dim.width / 2 - this.getSize ().width / 2, dim.height / 2 - this.getSize ().height / 2);
		//Setting the frame
		frame = getContentPane();
		frame.setLayout (null);
		timer= new Timer(10,this);
		//
		 paddle=new Paddle(20,50,"Images/paddle.png");
		 paddle2=new Paddle(980,50,"Images/paddle.png");
		 blocks= new Thing[2];
		 blocks[0]= new Thing(0,-25,"Images/wall.png");
		 blocks[1]= new Thing(0,660,"Images/wall.png");
		 ball= new Ball(50,50,"Images/ball.png");
		frame.setBackground(Color.black);
		
		for (int j = 0; j < blocks.length; j++) {
			frame.add(blocks[j].getBodyLbl());
		}
		frame.add(paddle.getBodyLbl());
		frame.add(paddle2.getBodyLbl());
		frame.add(ball.getBodyLbl());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener (this);
		//setFocusable (true);
		setFocusTraversalKeysEnabled (false);
		setVisible(true);
		timer.start();
		ball.setvelY(2);
		ball.setvelX(2);
		
	}


	public void actionPerformed (ActionEvent e){
		paddle.update(blocks);
		paddle2.update(blocks);
		ball.update(blocks,paddle,paddle2);
	}
	public void keyPressed (KeyEvent e)  //Key Pressed Event
	{
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_UP){
			System.out.print("Up");
			paddle.setvelY(-10);
		}
		if(key==KeyEvent.VK_DOWN){
			System.out.print("Up");
			paddle.setvelY(10);
		}
		if(key==KeyEvent.VK_W){
			System.out.print("Up");
			paddle2.setvelY(-10);
		}
		if(key==KeyEvent.VK_S){
			System.out.print("Up");
			paddle2.setvelY(10);
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}

}
