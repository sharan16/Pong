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
	
	private void collisionCheck(Thing blocks[],Paddle paddle,Paddle paddle2){
		double directionMod;
		if(this.getBody().intersects(paddle.getBody())||this.getBody().intersects(paddle2.getBody())){
			if(velX>0){
			velX = -Math.abs(velX);
            velX -= 1;
            directionMod = super.getY() - (paddle2.getY() + paddle2.getH()/2);
			}
			else{
				velX = Math.abs(velX);
	            velX += 1;
	            directionMod = super.getY() - (paddle.getY() + paddle.getH()/2);
			}
            
            velY = (int)(directionMod / 16);
		}
		for (int i = 0; i < blocks.length; i++) {
			if (this.getBody().intersects(blocks[i].getBody())) {
				if ((this.getY() > blocks[i].getY()) && (this.getY() - this.getH()) < blocks[i].getY()) {
					velX=-velX;
				}
				else{
					velY=-velY;	
				}
			}
		}
	}
	
	public void update(Thing blocks[],Paddle paddle,Paddle paddle2){
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
