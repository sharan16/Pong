import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * @author William Huynh
 * Date: May. 2017
 * Description: Picture class
 */
public class Picture extends JComponent {

	/**
	 * class data
	 */
	private Color c;
	private int x, y;
	private int width, height;
	private ImageIcon img;
	private boolean useImage;
	/*
	 * Default constructor  (Creates a read picture at 0,0)
	 */
	public Picture() {
		this.c = Color.RED;
		this.x = 0;
		this.y = 0;
		this.width = 100;
		this.height = 50;
		this.useImage = false;
		this.img = null;
		repaint();
	}

	public Picture (int x, int y, int width, int height, Color c){
		this.c = c;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.useImage = false;
		this.img = null;
		repaint();
	}
	public Picture (int x, int y, ImageIcon img){
		this.x = x;
		this.y = y;
		this.width = img.getIconWidth();
		this.height = img.getIconHeight();
		this.useImage = true;
		this.img = img;
		repaint();
	}
	public Picture (int x, int y, String text){
		this.x = x;
		this.y = y;
		this.useImage = false;

		repaint();
	}
	/*
	 * The components paint method
	 */
	public void paint (Graphics g){
		if (!useImage){
			g.setColor (this.c);
			g.drawRect(x, y, width, height);
		}
		else if (useImage){
			img.paintIcon(this, g, x, y);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Picture p0 = new Picture();

		f.setSize(400, 300);
		f.add(p0);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "wait a minute");

		f.remove(p0);
		Picture p1 = new Picture (50, 50, 50, 100, Color.BLUE);
		f.add(p1);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "wait a minute");

		f.remove(p1);
		Picture p2 = new Picture (10, 10, new ImageIcon ("backspace.jpg"));
		f.add(p2);
		f.setVisible(true);
	}

}
