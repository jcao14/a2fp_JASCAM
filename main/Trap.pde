//trap
//will be compatable with loader class

public class Trap extends Tile {
  //Staircase should also be an instance of trap. It's player triggered. It just don't kill. If anything stairs are like traphole.
  TrapType type;

  PImage trap;

  public Trap(TrapType type, double x, double y) {
    super (2,loadImage("floor.png"));
    setXY(x, y);
    setMapObjectType(MapObjectType.values()[4]);
    this.type = type;

//===========SWITCH TILE TO FLOOR LATER ON FOR TRAP. FOR TESTING PURPOSES, THE EXPOSED TRAP FORM HAS BEEN USED=====
    switch(type) {
    case TRAPHOLE:
      //tile = loadImage("floor.png");
      tile = loadImage("hole.png");  
      //Loader.myLoadImage("floor.png");
      //resize images separately
      // Loader.myLoadImage("trap (2).png");
      break;
    case CHEST:

      tile = loadImage("treasure_closed.png"); 
      trap= loadImage("treasure_open.png");
      break;
    case WONDERTILE:
      //tile = loadImage("floor.png");
      tile = loadImage("wonder.png"); 
      break;
    }
  }
  public void handleCollision(MapObject mo) {//tBD
  }

  public void animate() {//TBD
    image (tile, (float)x, (float)y);
  }
  
}