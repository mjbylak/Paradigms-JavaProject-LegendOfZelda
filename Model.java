//Mitchell Bylak 3/20/2022 Homework 5

import java.util.*;

class Model {
	ArrayList<Sprite> sprites;
	Link link;
	Controller controller;

	Model() {
		sprites = new ArrayList<Sprite>();
		link = new Link(100, 100);
		sprites.add(link);

	}
	
	public void update() {
		for (int i = 0; i < sprites.size(); i++) {
			for(int j = 0; j < sprites.size(); j++) {
				if(sprites.get(i).isPot() 
				&& (sprites.get(j).isBrick() || sprites.get(j).isBoomerang())
				&& checkCollision(sprites.get(i),sprites.get(j))) {
					((Pot)sprites.get(i)).potBreak();
				}
				//code for boomerang collision
				if(sprites.get(i).isBoomerang()){
					if(i != j)
						if (checkCollision(sprites.get(i),sprites.get(j)) && !sprites.get(j).isLink()) {
							if(sprites.get(j).isPot())
								((Pot)sprites.get(j)).potBreak();
							sprites.get(i).isActive = false;
						}
					
				}
			}
			if(!sprites.get(i).update())sprites.get(i).isActive = false;
			if(!sprites.get(i).isActive) {
				sprites.remove(i); break;
			}
			if(!sprites.get(i).isLink()) {
				if (checkCollision(sprites.get(i),link)) {
					link.repelLink(sprites.get(i));
					if(sprites.get(i).isPot()) 
						((Pot)sprites.get(i)).potMovement(link);
					}
			}
			sprites.get(i).update();
		}

	}
//	public boolean checkCollision(Sprite sprite) {
//		//System.out.println("sprite2s position is " + sprite2.x + " " + sprite2.y);
//		if ((link.x + link.w < sprite.x 
//			|| link.x > sprite.x + sprite.w) 
//			|| (link.y < sprite.y - link.h 
//			|| link.y - link.h > sprite.y)) {
//			return false;
//		}
//		//System.out.println("colliding with brick at " + sprite.x + ", " + sprite.y);
//		return true;
//	}

	public boolean checkCollision(Sprite sprite, Sprite sprite2) { //boomerang, brick
//		System.out.println("sprite2s position is " + sprite2.x + " " + sprite2.y);
		if ((sprite2.x + sprite2.w <= sprite.x 
			|| sprite2.x >= sprite.x + sprite.w) 
			|| (sprite2.y + sprite2.h <= sprite.y
			|| sprite2.y >= sprite.y + sprite.h)) {
			return false;
		}
		
//		//FUN TROUBLESHOOTING THING YAYYYY
//		System.out.print("colliding with ");
//		if(sprite2.isPot())System.out.print("pot ");
//		else if (sprite2.isBrick())System.out.print("brick");
//		else if(sprite2.isBoomerang())System.out.print("boomerage");
//		System.out.println(" at " + sprite.x + ", " + sprite.y);
		return true;
	}

	
	// Checking if a brick exists at the location and removing it if so
	public boolean isSpriteThere(int x, int y) {
		for (int i = 0; i < sprites.size(); i++) {
			if (x == sprites.get(i).x && y == sprites.get(i).y) {
				//System.out.println("There is already a brick at " + sprites.get(i).x + ", " + sprites.get(i).y);
				if(sprites.get(i).isBrick() || sprites.get(i).isPot())
					sprites.remove(i);
				return true;
			}
		}
		return false;
	}

	// Marshals this object into a JSON DOM
	Json marshal() {
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("bricks", tmpList);
		Json tmpListLink = Json.newList();
		ob.add("link", tmpListLink);
		Json tmpListPot = Json.newList();
		ob.add("pots", tmpListPot);
		
		for (int i = 0; i < sprites.size(); i++) {
			if(sprites.get(i).isBrick()) {
//				System.out.println("brick works");
				tmpList.add(sprites.get(i).marshal());
			}
			if(sprites.get(i).isPot()) {
//				System.out.println("pot works");
				tmpListPot.add(sprites.get(i).marshal());
			}
			if(sprites.get(i).isLink()) {
				tmpListLink.add(sprites.get(i).marshal());
			}
		}
		return ob;
	}

	// Unmarshalling constructor
	void unmarshal(Json ob) {
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
		Json tmpList = ob.get("bricks");
		for (int i = 0; i < tmpList.size(); i++)
			sprites.add(new Brick(tmpList.get(i)));
		Json tmpListPot = ob.get("pots");
		for (int i = 0; i < tmpListPot.size(); i ++)
			sprites.add(new Pot(tmpListPot.get(i)));

	}

}