//trap
//will be compatable with loader class

public class Trap extends AnimatedMapObject {
//Staircase should also be an instance of trap. It's player triggered. It just don't kill. If anything stairs are like traphole.
  TrapType type;
  
  PImage pre_traphole;
  PImage traphole;
  public Trap(TrapType type, double x, double y) {
    super(x, y, 100, 100, MapObjectType.values()[4]);
    this.type = type;

    switch(type) {
    case TRAPHOLE:
      //Loader.myLoadImage("floor.png");
      //resize images separately
     // Loader.myLoadImage("trap (2).png");
      break;
    case CHEST:
      break;
    case WONDERTILE:
      break;
    default:
      break;
    }
  }
  public void handleCollision(MapObject mo) {//tBD
  }
  
  public void animate(){//TBD
  }
}