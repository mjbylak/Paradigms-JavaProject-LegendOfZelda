//Mitchell Bylak 3/20/2022 Homework 5

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;


class View extends JPanel
{

	
	View() {
	}

	Model model;
	
	//global variables for offset of room camera
	int room_x=0;
	int room_y=0; 
	
	View(Controller c, Model m) {
		model = m;
		
		c.setView(this);

		
		
		try {
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(66, 34, 63)); //better color, less painful to eyes
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite sprite = model.sprites.get(i);
			sprite.draw(g, room_x, room_y);
		}
	}
	
	static BufferedImage loadImage(String filename) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filename));
			System.out.println(filename + " was loaded.");
		} catch(Exception e) {
			System.out.println("Error loading image!");
			e.printStackTrace(System.err);
			System.exit(1);;
		}
		return image;
	}
	
	//CODE TO RESIZE SPRITES
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
}
