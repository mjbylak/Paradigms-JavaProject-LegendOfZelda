//Mitchell Bylak 3/20/2022 Homework 5

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

 public class Link extends Sprite{

	int pX, pY;
	int speed = 10;
	int currImage = 0;
	static BufferedImage[] link_images = new BufferedImage[50];
	int dir = 2; // 0 up, 1 right, 2 down, 3 left
	
	public Link (int x, int y) {
		
		this.x = x;
		this.y = y;
		this.w = 45;
		this.h = 45;
		
		for(int i = 1; i < 51; i ++) {
			if(link_images[i-1] == null) {
			link_images[i-1] = View.loadImage("link_images\\link (" + (i) + ").png"); 
			link_images[i-1] = View.resize(link_images[i-1], w, h);
			}
		}
	}
	
	public void repelLink(Sprite sprite) {
		if(x+w >= sprite.x && pX <= sprite.x) {
//			System.out.println("Pushed left");
			x=pX; 
//			y=pY;
		}
		if(x <= sprite.x+50 && pX >= sprite.x + 50) {
			//System.out.println("push right");
			x=pX;//-pX%50; //for cleaner wall contact
//			y=pY;
		}
		if(y >= sprite.y-50 && pY <= sprite.y-50) {
//			System.out.println("push up");
			y=pY;
//			x=pX;
		}
		if(y-h <= sprite.y && pY >= sprite.y + 50) {
//			System.out.println("push down");
			y=pY;
//			x=pX;
		}
	}
	
	public void getPrevious() {
		pX = x;
		pY = y;
	}
	
	@Override 
	public String toString()
	{
		return "Brick (x,y) = (" + x + ", " + y + ")";
	}
	
	/* Image Notes
	 * Forward link 4-13
	 * Left link 14-23
	 * Right link 30-39
	 * Back link 42-50
	 * Neutral forw 1-3
	 * Back noot 40-41
	 * Left noot 24-26
	 * Right noot 27-29
	 */

	@Override
	public boolean isLink() {
		return true;
	}

	public void draw(Graphics g, int room_x, int room_y) {
		g.drawImage(link_images[currImage], this.x - room_x, this.y - room_y, null);		
	}

	public boolean update() {
		return true;
	}

	Json marshal() {
		Json ob = Json.newObject();
		ob.add("linkX", x);
		ob.add("linkY", y);
		return ob;
	}
	
	
}
