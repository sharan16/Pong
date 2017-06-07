import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Paddle extends Thing{
	private int velY;
	public Paddle() {
		super();
	}
	Paddle (int x, int y, String imageFile){
		super(x,y,imageFile);
	}
	public int getvelY() {
		return velY;
	}
	public void setvelY(int velY) {
		this.velY = velY;
	}
	public void update(Thing blocks[]){
		//		int y;
		//		if(blocks[0].getBody().intersects(this.getBody())){
		//			y=30;
		//			System.out.println("Top");
		//		}
		//		else if(blocks[1].getBody().intersects(this.getBody())){
		//			y=660-10-super.getH();
		//			System.out.println("Bottom");
		//		}
		int y=super.getY();
		if(blocks[0].getBody().intersects(this.getBody())){
			y+=1;
			velY=0;
			System.out.println("Top");
		}
		else if(blocks[1].getBody().intersects(this.getBody())){
			velY=0;
			y-=1;
			System.out.println("Bottom");
		}
		else{
			y=super.getY();	
			y+=velY;
		}

		super.setY(y);
		super.update();
	}

}
