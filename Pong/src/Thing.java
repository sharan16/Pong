import javax.swing.*;
import java.awt.Color;
import java.awt.geom.*;

/**
 * @author Bikramjit Saini
 * Date: 5/29/2017
 * Desc: Thing class
 */
public class Thing {
	// Creating the class data
	private Rectangle2D.Double body;
	private JLabel bodyLbl;
	private ImageIcon img;
	private String imgName;
	private int x, y, w, h;

	// Creating the default constructor
	public Thing () {
		this.bodyLbl = new JLabel ();
		this.body = new Rectangle2D.Double ();
	}

	// Creating overloaded constructors
	public Thing (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.bodyLbl = new JLabel ();
		bodyLbl.setBounds (x, y, w, h);
		this.body = new Rectangle2D.Double (x, y, w, h);
	}

	public Thing (int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.bodyLbl = new JLabel ();
		bodyLbl.setBounds (x, y, w, h);
		bodyLbl.setBackground (c);
		this.body = new Rectangle2D.Double (x, y, w, h);
	}

	public Thing (int x, int y, String imgName) {
		this.x = x;
		this.y = y;
		this.img = new ImageIcon (imgName);
		this.w = img.getIconWidth ();
		this.h = img.getIconHeight ();
		this.imgName = imgName;
		this.bodyLbl = new JLabel (this.img);
		bodyLbl.setBounds(x,y,w, h);
		this.body = new Rectangle2D.Double (x, y, w, h);
	}

	// Creating the getters and setters
	public Rectangle2D.Double getBody () {
		return body;
	}

	public void setBody (Rectangle2D.Double body) {
		this.body = body;
	}

	public JLabel getBodyLbl () {
		return bodyLbl;
	}

	public void setBodyLbl (JLabel bodyLbl) {
		this.bodyLbl = bodyLbl;
	}

	public String getImgName () {
		return imgName;
	}

	public void setImgName (String imgName) {
		this.imgName = imgName;
	}

	public int getX () {
		return x;
	}
	public void setX (int x) {
		this.x = x;
	}

	public int getY () {
		return y;
	}
	public void setY (int y) {
		this.y = y;
	}

	public int getW () {
		return w;
	}
	public void setW (int w) {
		this.w = w;
	}

	public int getH () {
		return h;
	}
	public void setH (int h) {
		this.h = h;
	}
	public void update(){
		bodyLbl.setBounds (x,y,w, h);
		//bodyLbl.repaint();
		this.body = new Rectangle2D.Double (x, y, w, h);
	}
	// Creating the self-testing main method
	public static void main (String[] args) {

	}
}
