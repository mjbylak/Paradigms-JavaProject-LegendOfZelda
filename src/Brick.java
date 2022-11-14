//Mitchell Bylak 2/15/2022 Homework 3

 class Brick {

	int x;
	int y;
	
	Brick (int x_pos, int y_pos) {
		x = x_pos;
		y = y_pos;
	}
	
	@Override 
	public String toString()
	{
		return "Brick (x,y) = (" + x + ", " + y + ")";
	}
	
	public void main () {
		
	}
	
	     // Marshals this object into a JSON DOM
  Json marshal()
  {
      Json ob = Json.newObject();
      ob.add("x_pos", x);
      ob.add("y_pos", y);
      return ob;
  }

  //Unmarshaling constructor
Brick(Json ob)
{
    x = (int)ob.getDouble("x_pos");
    y = (int)ob.getDouble("y_pos");
    }
	


}
