//Mitchell Bylak 3/20/2022 Homework 5

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	Brick brick;
	Pot pot;
	
	// ______
	// __x___
	// _xxxx_
	// _xxxx_
	// ______
	
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	
	boolean quitting = false;
	boolean saving = false;
	boolean load = false;
	boolean editing = false;
	boolean startup = true;
	boolean placingPots = false;
	boolean boomeranging = false;
	
	
	Controller(Model m)
	{
			model = m;
	}

	void setView(View v)
	{
		view = v;
	}


	public void actionPerformed(ActionEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		if (editing) {getDestination(e.getX()+view.room_x, e.getY()+view.room_y);
		}
	}
	
	//This is necessary to my codes editing 
	public void getDestination(int x, int y) {

		//System.out.println(x + " " + y);
		if (x < 0) {
			x -= 50; // These essentially check if negative and slightly alter the algo for snap to
						// grid
		}
		if (y < 0) {
			y -= 50;
		}

		x = x / 50 * 50; // Converting x and y to multiples of 50
		y = y / 50 * 50;

		// Checking if a Brick exists or not and adding if not
		if (!model.isSpriteThere(x, y)) {
			if (!placingPots) {
				System.out.println("placed brick");
				Brick b = new Brick(x, y);
				model.sprites.add(b);
			}
			if (placingPots) {
				System.out.println("placed pot");
				Pot p = new Pot(x,y);
				model.sprites.add(p);
			}
			
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {
		if(e.getY() < 100)
		{
			//System.out.println("break here");
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_D: keyRight = true; break;
			case KeyEvent.VK_A: keyLeft = true; break;
			case KeyEvent.VK_W: keyUp = true; break;
			case KeyEvent.VK_X: keyDown = true; break;
			case KeyEvent.VK_Q: quitting = true; break;
			case KeyEvent.VK_ESCAPE: quitting = true; break;
			case KeyEvent.VK_S: saving = true; break;
			case KeyEvent.VK_L: load = true; break;
			case KeyEvent.VK_CONTROL: boomeranging = false; break;
			case KeyEvent.VK_P: 
				if( placingPots) {placingPots = false; System.out.println("Placing bricks"); break; }
				else { placingPots = true; System.out.println("Placing pots"); break; }
			case KeyEvent.VK_E: {
				if (editing) {editing = false; System.out.println("Playing"); break;}
				else {editing = true; System.out.println("Editing"); break;} 
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_D: keyRight = false; break;
			case KeyEvent.VK_A: keyLeft = false; break;
			case KeyEvent.VK_W: keyUp = false; break;
			case KeyEvent.VK_X: keyDown = false; break;
			case KeyEvent.VK_S: saving = false; break;
			case KeyEvent.VK_L: load = false; break;
			case KeyEvent.VK_CONTROL: boomeranging = true; break;
			
		}
	}

	public void keyTyped(KeyEvent e)	{
		
	}

	//global link animation variables
	int moving = 0;
	

	void update()
	{
		model.link.getPrevious();
		
		if(boomeranging) {
			System.out.println("throwing 'rang");
			model.sprites.add(new Boomerang(model.link.x+model.link.w/2,model.link.y+model.link.h/2,model.link.dir));
			boomeranging = false; //(x+w/4, y+h/4)
		}
		
		if(keyRight) {
			if(moving >= 10) moving = 0;
			
				model.link.x += model.link.speed;
				keyUp = false;
				keyDown = false;
				model.link.currImage = moving + 29;
				moving+=1;
	
				if (model.link.x >= -10 && model.link.x <= 10) view.room_x =0;
				if (model.link.x >= 490 && model.link.x <= 510) view.room_x = 500;
				if (model.link.x >= 990 && model.link.x <= 1010) view.room_x = 1000;
				model.link.dir = 1;
		}
		
		if(keyLeft) {
			if(moving >= 10) moving = 0;
			
				model.link.x -=model.link.speed;
				keyUp = false;
				keyDown = false;
				model.link.currImage = moving + 13;
				moving+=1;
				
				if (model.link.x >= -10 && model.link.x <= 10) view.room_x =-500;
				if (model.link.x >= 490 && model.link.x <= 510) view.room_x=0;
				if(model.link.x >= 990 && model.link.x <= 1010) view.room_x=500;
				model.link.dir = 3;
		}
		
		if(keyUp) {
			if(moving >= 11) moving = 0;
			
				model.link.y-=model.link.speed;
				keyLeft = false;
				keyRight = false;
				model.link.currImage = moving + 39;
				moving+=1;
				
				if (model.link.y >= -10 && model.link.y <= 10) view.room_y =-500;
				if (model.link.y >= 490 && model.link.y <= 510) view.room_y=0;
				model.link.dir = 0;
		}
		
		if(keyDown) {
			if(moving >= 10) moving = 0;
				model.link.y+=model.link.speed;
				keyLeft = false;
				keyRight = false;
				model.link.currImage = moving + 3;
				moving+=1;
				model.link.dir = 2;
				if (model.link.y >= -10 && model.link.y <= 10) view.room_y =0;
				if (model.link.y >= 490 && model.link.y <= 510) view.room_y = 500;
			
		}
		
		
		if(quitting) System.exit(0); //will quit your program's execution.
		if(saving && editing) model.marshal().save("map.json");
		if(load) {
			Json j = Json.load("map.json");
			j.save("temp.json");
			model.unmarshal(j);
		}
		
		//load in map on startup
		if (startup) {
			Json j = Json.load("map.json");
			j.save("temp.json");
			model.unmarshal(j);
			startup = false;
			System.out.println("File loaded");
		}
		
	}
	

	
	public static void pause(long timeInMilliSeconds) {

	    long timestamp = System.currentTimeMillis();
	    while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);
	}
	
	
		
}
