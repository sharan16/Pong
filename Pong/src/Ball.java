import java.awt.Color;

public class Ball extends Thing {
	private int velX,velY;
	public Ball() {
		// TODO Auto-generated constructor stub
	}
	public Ball (int x, int y, String imageFile){
		super(x,y,imageFile);
	}
	public int getvelX() {
		return velX;
	}
	public void setvelX(int velX) {
		this.velX = velX;
	}
	public int getvelY() {
		return velY;
	}
	public void setvelY(int velY) {
		this.velY = velY;
	}
	
	private void collisionCheck(Block blocks[],Paddle paddle,Paddle paddle2){
		double directionMod;
		
		if(this.getBody().intersects(paddle.getBody())||this.getBody().intersects(paddle2.getBody())){
			if(velX>0){
			velX = -Math.abs(velX);
            velX -= 0.3;
            directionMod = super.getY() - (paddle2.getY() + paddle2.getH()/2);
			}
			else{
				velX = Math.abs(velX);
	            velX += 0.3;
	            directionMod = super.getY() - (paddle.getY() + paddle.getH()/2);
			}
            
            velY = (int)(directionMod / 16);
            if (velY==0){
            if(Math.random()<0.5){
            		velY=-1;
            	}
            	else{
            		velY=1;
            	}
            }
		}
		for (int i = 0; i < blocks.length; i++) {
			if (this.getBody().intersects(blocks[i].getBody())) {
				if ((this.getY()+this.getH()-velY > blocks[i].getY()) && ((this.getY()-velY) < (blocks[i].getY()+blocks[i].getH()))) {
					velX=-velX;
					System.out.println("Reverse X");
					System.out.println("\nthis.getY()+this.getH()-velY : "+ (this.getY()+this.getH()-velY));
					System.out.println("blocks[i].getY() : "+ (blocks[i].getY()));
					System.out.println("this.getY() +velY: "+(this.getY()+velY));
					System.out.println("blocks[i].getY()+blocks[i].getH() :"+(blocks[i].getY()+blocks[i].getH()));
					System.out.println("this.getY()"+(this.getY()));
					System.out.println("velY: "+velY);
				}
				else{
					System.out.println("Reverse Y");
					velY=-velY;	
				}
			}
		}
	}
	
	public void update(Block blocks[],Paddle paddle,Paddle paddle2){
		int x,y;
		collisionCheck(blocks,paddle,paddle2);
		x=super.getX();	
		y=super.getY();
		x+=velX;
		y+=velY;
		super.setX(x);
		super.setY(y);
		super.update();
	}

}
