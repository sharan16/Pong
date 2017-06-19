import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
/* Author: William Huynh
 * Date: June. 2017
 * Description: A program that inherits from the Thing class and is used as an obstacle in the harder difficulties of the game. 
 * 				The obstacle changes the ball's projectile motion.
 * Method List:
 * Constructor - inherits from Thing class
 * public int getvelY() - returns Y velocity 
 * public void setvelY(int velY) - sets Y velocity 
 * public void update(Block blocks[]) - updates Y velocity depending on interactions 
 */

public class Block extends Thing{ // inherits from Thing class
	// declare variables for Y velocity and move 
	private int velY;
	private boolean move;
	public Block() {
		super();
	}
	Block (int x, int y, String imageFile){ // allow block to have an image icon and coordinates 
		super(x,y,imageFile);
		move=false; // block set to not move

	}
	Block (int x, int y, String imageFile,boolean move){ // allow block to move
		super(x,y,imageFile);
		this.move=move;
		if(this.move){
			if(Math.random()>0.5){
				velY=1;
			}
			else{
				velY=-1;
			}
		}
	}

	public int getvelY() { // get Y velocity
		return velY;
	}
	public void setvelY(int velY) { // set Y velocity
		this.velY = velY;
	}
	public void update(Block blocks[]){ // update Y velocity depending on interactions 
		if(this.move){
			int y=super.getY();
			if(blocks[0].getBody().intersects(this.getBody())||blocks[2].getBody().intersects(this.getBody())){
				y+=1;
				velY=1;
				System.out.println("Top"); // test movement when block hits with top of game
			}
			else if (blocks[1].getBody().intersects(this.getBody())){
				y-=1;
				velY=-1;

				System.out.println("Bottom"); // test movement when block hits with bottom of game
			}

			y=super.getY();	
			y+=velY;

			super.setY(y);
			super.update();
		}
	}

}
