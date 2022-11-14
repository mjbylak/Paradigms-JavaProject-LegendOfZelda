//Mitchell Bylak 2/15/2022 Homework 3

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
	
	//int [][] bounds = {{0,0,0,0,0,0},{0,0,1,0,0,0},{0,1,1,1,1,0},{0,1,1,1,1,0},{0,0,0,0,0,0}};
	//int [] currLocation = {2,2}; //x & y
	
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
		if (editing) {model.setDestination(e.getX()+view.room_x, e.getY()+view.room_y);
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
			case KeyEvent.VK_E: {if (editing) {editing = false; System.out.println("Playing"); break;}
			else {editing = true; System.out.println("Editing"); break;} }
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
			
		}
	}

	public void keyTyped(KeyEvent e)	{
		
	}

	//global link animation variables
	int moving = 0;
	

	void update()
	{
		model.link.getPrevious();
		
		if(keyRight) {
			if(moving >= 10) moving = 0;
			//if (model.checkCollision()) {
				model.link.x += model.link.speed;
				keyUp = false;
				keyDown = false;
				model.link.currImage = moving + 29;
				moving+=1;
	
				if (model.link.x == 0) view.room_x =0;
				if (model.link.x == 500) view.room_x = 500;
				if (model.link.x == 1000) view.room_x = 1000;
			
		}
		
		if(keyLeft) {
			if(moving >= 10) moving = 0;
			
				model.link.x -=model.link.speed;
				keyUp = false;
				keyDown = false;
				model.link.currImage = moving + 13;
				moving+=1;
				
				if (model.link.x == 0) view.room_x =-500;
				if (model.link.x == 500) view.room_x=0;
				if(model.link.x == 1000) view.room_x=500;
			
		}
		
		if(keyUp) {
			if(moving >= 11) moving = 0;
			
				model.link.y-=model.link.speed;
				keyLeft = false;
				keyRight = false;
				model.link.currImage = moving + 39;
				moving+=1;
				
				if (model.link.y == 0) view.room_y =-500;
				if (model.link.y == 500) view.room_y=0;
			
		}
		
		if(keyDown) {
			if(moving >= 10) moving = 0;
				model.link.y+=model.link.speed;
				keyLeft = false;
				keyRight = false;
				model.link.currImage = moving + 3;
				moving+=1;
				
				if (model.link.y == 0) view.room_y =0;
				if (model.link.y == 500) view.room_y = 500;
			
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
		}
		
	}
	

	
	public static void pause(long timeInMilliSeconds) {

	    long timestamp = System.currentTimeMillis();
	    while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);
	}
	
	
		
}
