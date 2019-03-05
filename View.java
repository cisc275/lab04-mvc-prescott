import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
@SuppressWarnings("serial")
public class View extends JPanel {
	/** [direction][frame] */
	BufferedImage[][] pics;
	final int frameCount = 10;
	int picNum = 0;
	int x;
	int y;
	Direction facing;
	
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	
	public View() {
		JFrame frame = new JFrame();
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(View.frameWidth, View.frameHeight);
		frame.setVisible(true);
		frame.getContentPane().add(this);
		pics = new BufferedImage[8][10];
		for (int h = 0; h < 8; h++) {
			Direction dirk = Direction.atIndex(h);
			BufferedImage img = createImage("orc/orc_forward_"+dirk.getName()+".png");
			for(int i = 0; i < frameCount; i++)
				pics[h][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
	}
	
	public void update(int x, int y, Direction direct) {
		this.x = x;
		this.y = y;
		this.facing = direct;
		this.repaint();
	}
	
	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(pics[facing.getIndex()][picNum], this.x, this.y, Color.gray, this);
	}
	
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	private BufferedImage createImage(String sauce) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(sauce));
			return bufferedImage;
		} catch (IOException e) {
			System.out.println(sauce);
			e.printStackTrace();
		}
		return null;
	}
}