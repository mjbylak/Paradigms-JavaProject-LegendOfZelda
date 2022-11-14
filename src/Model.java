//Mitchell Bylak 2/5/2022 Homework 3

import java.util.*;

class Model {
	ArrayList<Brick> bricks;
	Link link;
	Controller controller;

	Model() {
		bricks = new ArrayList<Brick>();
		link = new Link();

	}

	public boolean checkCollision(Brick b) {
		if ((link.x + link.width < b.x || link.x > b.x + link.width) || (link.y < b.y - link.height || link.y - link.height > b.y)) { //
			return false;
		}
		/*if (link.x > b.x + 50) {
			
			return false;
		}
		if (link.y < b.y - 50) {// assumes bigger is downward
			return false;
		}
		if (link.y + link.height > b.y) { // assumes bigger is downward
			return false;

		}*/
		System.out.println("colliding");
		return true;
	}

	/*
	 * public boolean checkCollisionUp() { System.out.println("Link at " + link.y);
	 * for (int i = 0; i < bricks.size(); i++) { if(link.y-50 <= bricks.get(i).y &&
	 * link.y >= bricks.get(i).y && link.x-50 <= bricks.get(i).x && link.x+50 >=
	 * bricks.get(i).x) { link.currImage = 40; System.out.println("up"); return
	 * false; } } return true; }
	 * 
	 * public boolean checkCollisionDown() {
	 * 
	 * for (int i = 0; i < bricks.size(); i++) { if(link.y <= bricks.get(i).y &&
	 * link.y+50 >= bricks.get(i).y && link.x-50 <= bricks.get(i).x && link.x+50 >=
	 * bricks.get(i).x) { System.out.println("down"); link.currImage = 0; return
	 * false; } } return true; }
	 * 
	 * public boolean checkCollisionLeft() {
	 * 
	 * for (int i = 0; i < bricks.size(); i++) { if(link.y-50 <= bricks.get(i).y &&
	 * link.y+50 >= bricks.get(i).y && link.x-50 <= bricks.get(i).x && link.x >=
	 * bricks.get(i).x) { System.out.println("left"); link.currImage = 13; return
	 * false; } } return true; }
	 * 
	 * public boolean checkCollisionRight() {
	 * 
	 * for (int i = 0; i < bricks.size(); i++) { if(link.y-50 <= bricks.get(i).y &&
	 * link.y+50 >= bricks.get(i).y && link.x <= bricks.get(i).x && link.x+50 >=
	 * bricks.get(i).x) { System.out.println("right"); link.currImage = 26; return
	 * false; } } return true; }
	 */

	public void update() {
		boolean collision = false;
		for (int i = 0; i < bricks.size(); i++) {
			collision = checkCollision(bricks.get(i));
			if (collision)
				link.repelLink(bricks.get(i));
		}

	}

	public void setDestination(int x, int y) {

		System.out.println(x + " " + y);
		if (x < 0) {
			x -= 50; // These essentially check if negative and slightly alter the algo for snap to
						// grid
		}
		if (y < 0) {
			y -= 50;
		}

		x = x / 50 * 50; // Converting x and y to multiples of 50
		y = y / 50 * 50;

		if (!isBrick(x, y)) {
			Brick b = new Brick(x, y);
			bricks.add(b);
		}
		// Checking if a Brick exists or not and adding if not
		System.out.println(x + " " + y);
	}

	public boolean isBrick(int x, int y) {
		for (int i = 0; i < bricks.size(); i++) {
			if (x == bricks.get(i).x && y == bricks.get(i).y) {
				System.out.println("There is already a brick at " + bricks.get(i).x + ", " + bricks.get(i).y);
				bricks.remove(i);
				return true;
			}
		} // Checking if a brick exists at the location and removing it if so
		return false;
	}

	// Marshals this object into a JSON DOM
	Json marshal() {
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("bricks", tmpList);
		for (int i = 0; i < bricks.size(); i++)
			tmpList.add(bricks.get(i).marshal());
		return ob;
	}

	// Unmarshalling constructor
	void unmarshal(Json ob) {
		bricks = new ArrayList<Brick>();
		Json tmpList = ob.get("bricks");
		for (int i = 0; i < tmpList.size(); i++)
			bricks.add(new Brick(tmpList.get(i)));

	}

}