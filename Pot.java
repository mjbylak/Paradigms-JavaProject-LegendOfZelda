//Mitchell Bylak 3/20/2022 Homework 5

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pot extends Sprite{
	
	int currImage;
	int delay = 100; //amount of time until shards fade
	boolean isBroke = false;
	boolean isSchmovin = false; //is the pot being moved
	int dir;
	int slideSpeed = 3;

	 static BufferedImage[] image = new BufferedImage[2];
			 boolean broken = false;
		
	Pot (int x_pos, int y_pos) {
		this.x = x_pos;
		this.y = y_pos;
		this.h = 50;
		this.w = 50;
		this.currImage = 0;
		
			if (image[0] == null || image[1] == null) {
				image[0] = View.loadImage("pot.png"); 
				image[1] = View.loadImage("pot_broken.png");
			}
		
	}
	
	public void potBreak() {
		currImage = 1;
		isBroke = true;
	}
	
	public void potMovement(Link link) {
		if(!isBroke) {
			dir = link.dir;
			isSchmovin = true;
		}
	}
	
	@Override 
	public String toString()
	{
		return "Pot (x,y) = (" + x + ", " + y + ")";
	}
	
	public void main () {
		
	}
	
	// Marshals this object into a JSON DOM
	 Json marshal() {
	     Json ob = Json.newObject();
	     ob.add("x_pos", x);
	     ob.add("y_pos", y);
	     return ob;
	 }

	 //Unmarshalling constructor
	 public Pot(Json ob) {
	   x = (int)ob.getDouble("x_pos");
	   y = (int)ob.getDouble("y_pos");
	   w = 50;
	   h = 50;
	   if (image[0] == null || image[1] == null) {
			image[0] = View.loadImage("pot.png"); 
			image[1] = View.loadImage("pot_broken.png");
		}
	  }

	public void draw(Graphics g, int room_x, int room_y) {
		g.drawImage(image[currImage], x - room_x, y - room_y, null);
	}
	
	public boolean update() {
		if(isSchmovin) {
			if(dir == 0) this.y-=slideSpeed;
			else if(dir == 1) this.x+=slideSpeed;
			else if(dir == 2) this.y+=slideSpeed;
			else if(dir == 3) this.x-=slideSpeed;
		}
		if(isBroke) {
			isSchmovin = false;
			delay--;
		}
		if(delay <= 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isPot() {
		return true;
	}
	

}
