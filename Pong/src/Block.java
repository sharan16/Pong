import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Block extends Thing{
	private int velY;
	private boolean move;
	public Block() {
		super();
	}
	Block (int x, int y, String imageFile){
		super(x,y,imageFile);
		move=false;

	}
	Block (int x, int y, String imageFile,boolean move){
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

	public int getvelY() {
		return velY;
	}
	public void setvelY(int velY) {
		this.velY = velY;
	}
	public void update(Block blocks[]){
		if(this.move){
			int y=super.getY();
			if(blocks[0].getBody().intersects(this.getBody())||blocks[2].getBody().intersects(this.getBody())){
				y+=1;
				velY=1;
				System.out.println("Top");
			}
			else if (blocks[1].getBody().intersects(this.getBody())){
				y-=1;
				velY=-1;

				System.out.println("Bottom");
			}

			y=super.getY();	
			y+=velY;

			super.setY(y);
			super.update();
		}
	}

}
