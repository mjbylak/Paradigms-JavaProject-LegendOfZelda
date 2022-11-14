//Mitchell Bylak 3/20/2022 Homework 5

import java.awt.Graphics;
import java.awt.image.BufferedImage;

 public class Brick extends Sprite{

	 static BufferedImage image;
	
	Brick (int x_pos, int y_pos) {
		this.x = x_pos;
		this.y = y_pos;
		this.h = 50;
		this.w = 50;
	}
	
	@Override 
	public String toString()
	{
		return "Brick (x,y) = (" + x + ", " + y + ")";
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

  //Unmarshaling constructor
  public Brick(Json ob) {
    x = (int)ob.getDouble("x_pos");
    y = (int)ob.getDouble("y_pos");
    w = 50;
    h = 50;
    if (image == null) {
    	image = View.loadImage("brick.jpg");
    }
   }

public void draw(Graphics g, int room_x, int room_y) {
	g.drawImage(image, x - room_x, y - room_y, null);
}

public boolean update() {
	return true;
}

@Override
public boolean isBrick() {
	return true;
}

}
