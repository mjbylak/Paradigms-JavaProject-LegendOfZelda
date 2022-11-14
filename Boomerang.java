//Mitchell Bylak 3/20/2022 Homework 5

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boomerang extends Sprite{
	
	static BufferedImage[] boomerang_images = new BufferedImage[4];
	int dir; // 0 up, 1 right, 2 down, 3 left
	int speed = 5;
	int currImage = dir*10;
	
	
	public Boomerang (int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.w = 10;
		this.h = 10;
		
		for(int i = 0; i < 4; i ++) {
			if (boomerang_images[i] == null) boomerang_images[i] = View.loadImage("boomerang" + (i) + ".png");
		}
	}
	
	public void draw(Graphics g, int room_x, int room_y) {
		int i = currImage/15;
		g.drawImage(boomerang_images[i], x - room_x, y - room_y, null);
	}

	
	public boolean update() {
		if(dir == 0) {
			this.y-=speed;
		}
		else if(dir == 1) {
			this.x+=speed;
		}
		else if(dir == 2) {
			this.y+=speed;
		}
		else if(dir == 3) {
			this.x-=speed;
		}
		//making the boomerang pictures change slower
		if(currImage == 45) currImage = 0;
		else currImage ++;
		return true;
	}

	
	Json marshal() {
		return null;
	}
	
	@Override
	public boolean isBoomerang() {
		return true;
	}

}
