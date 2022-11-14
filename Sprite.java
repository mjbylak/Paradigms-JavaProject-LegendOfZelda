//Mitchell Bylak 3/20/2022 Homework 5

import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite {
	int x, y, w, h;
	boolean isActive = true;
	
	public Sprite() {
	}
	
	abstract public void draw(Graphics g, int room_x, int room_y);
	abstract public boolean update();
	abstract Json marshal();
	
	public boolean isLink() {
		return false;
	}
	
	public boolean isBrick() {
		return false;
	}
	
	public boolean isPot() {
		return false;
	}
	
	public boolean isBoomerang() {
		return false;
	}
	
}
