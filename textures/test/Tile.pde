//Tile class
//implements MAxs collision stuff
//Map

public class Tile extends MapObject {

  public static final int SIZE = 20;//NOTE NOT USED, LOOK THRU CODE :(

  public static final int WALL = 0;
  public static final int FLOOR = 1;

  int tileType;

  public Tile(int num){
    super(
	0, 0,   //coordinates dont matter, wud be set later
	20, 20,
	MapObject.MapObjectType.TILE,
	true, false   //will be set later
    )
    tiletype = num;
    

  }
  public int tileType(){
    return tiletype;
  }
  
  public void makeSolid(){
    collidable = true;
  }
  
  public String getCoor(){
    String ret = "";
    ret += xcor;
    ret += " ";
    ret += ycor;
    return ret;
  }

}