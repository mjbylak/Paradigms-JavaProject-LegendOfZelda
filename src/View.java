//Mitchell Bylak 2/15/2022 Homework 3

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

	
	View()
	{	}

	BufferedImage brick_image;
	Model model;
	Brick brick;
	Link link;
	
	//global variables for offset of room camera
	int room_x=0;
	int room_y=0; 
	
	View(Controller c, Model m)
	{
		model = m;
		
		c.setView(this);

		
		
		try {
			this.brick_image =
					ImageIO.read(new File("brick.jpg"));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(66, 34, 63)); //better color, less painful to eyes
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.drawImage(brick_image, b.x - room_x, b.y - room_y, null);
		}
		//dg.drawImage(link_images[currImage], link.x - room_x, link.y - room_y, null);
		model.link.linkDraw(g, room_x, room_y);
	}
	
//RELATING TO LINKS IMAGE LOADING	
	
	//current link image to be accessed
	
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
	//IMPORTED CODE TO RESIZE LINK TO FIT BETWEEN 1x1 SPACES
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
}
