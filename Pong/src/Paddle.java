import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
//Sharan Somaskanthan
//June 17th 2017
//Description: Paddle object class
//Method List
//Getters and setters
//public void setScore(int score) {
//public String getUserName() {
//public void setUserName(String userName) {
//public int getvelY() {
//public void setvelY(int velY) {
//Constructors
//public Paddle() {
//Paddle (int x, int y, String imageFile){
//Update object method
//public void update(Thing blocks[]){
public class Paddle extends Thing{
	private int velY,score;
	private String userName;
	//Getters and setters
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Paddle() {
		super();
	}
	Paddle (int x, int y, String imageFile){
		super(x,y,imageFile);
		this.score=0;
	}
	public int getvelY() {
		return velY;
	}
	public void setvelY(int velY) {
		this.velY = velY;
	}
	//Method to update position of paddle
	public void update(Thing blocks[]){
		int y;
		//If it touches the top wall or the bottom wall
		if(blocks[0].getBody().intersects(this.getBody())){
			y=30;
			System.out.println("Top");
		}
		else if(blocks[1].getBody().intersects(this.getBody())){
			y=660-10-super.getH();
			System.out.println("Bottom");
		}
		else{
		y=super.getY();	
		y+=velY;
		}
		//Update the position
		super.setY(y);
		super.update();
	}
	public static void main(String[] args) {
		Paddle paddle=new Paddle();
		paddle.setH(0);
		paddle.setW(1);
		paddle.setY(2);
		paddle.setX(3);
		paddle.setScore(300);
		System.out.println(	paddle.getH());
		System.out.println(	paddle.getW());
		System.out.println(	paddle.getY());
		System.out.println(	paddle.getX());
		System.out.println(	paddle.getScore());
	}

}
