import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

 class Link {

	int x = 100;
	int y = 100;
	int speed = 10;
	View view;
	Model model;
	int width = 45, height = 45;
	static BufferedImage[] link_images = new BufferedImage[50];
	int currImage = 0;
	int pX;
	int pY;
	
	public void repelLink(Brick b) {
		if(x+width >= b.x && pX <= b.x) {
			System.out.println("Pushed left");
			x=pX;
			y=pY;
		}
		if(x <= b.x+50 && pX >= b.x + 50) {
			System.out.println("push right");
			x=pX;
			y=pY;
		}
		if(y >= b.y-50 && pY <= b.y) {
			System.out.println("push up");
			y=pY;
			x=pX;
		}
		if(y+height <= b.y && pY >= b.y + 50) {
			System.out.println("push down");
			y=pY;
			x=pX;
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
	
	Link () {
		

		
		for(int i = 1; i < 51; i ++) {
			//System.out.println("link_images\\link (" + (i) + ").png");
			link_images[i-1] = view.loadImage("link_images\\link (" + (i) + ").png"); 
			link_images[i-1] = view.resize(link_images[i-1], width, height);
		}
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

	
	public void linkDraw(Graphics g, int room_x, int room_y) {
		g.drawImage(link_images[currImage], this.x - room_x, this.y - room_y, null);
	}


	
	void update(){	
	}
	
}
