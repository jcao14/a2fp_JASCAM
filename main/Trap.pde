//trap
//will be compatable with loader class

public class Trap extends Tile {
  //Staircase should also be an instance of trap. It's player triggered. It just don't kill. If anything stairs are like traphole.
  TrapType type;

  PImage trap;
  PImage pre_trap;

  public Trap(TrapType type, double x, double y) {
    super (2,loadImage("floor.png"));
    setXY(x, y);
    setSize(100, 100);
    setMapObjectType(MapObjectType.values()[4]);
    this.type = type;

    switch(type) {
    case TRAPHOLE:
      pre_trap = loadImage("floor.png");
      trap = loadImage("hole.png"); 
      //Loader.myLoadImage("floor.png");
      //resize images separately
      // Loader.myLoadImage("trap (2).png");
      break;
    case CHEST:
      pre_trap = loadImage("floor.png");
      trap = loadImage("treasure_closed.png"); 
      break;
    case WONDERTILE:
      pre_trap = loadImage("floor.png");
      trap = loadImage("wonder.png"); 
      break;
    }
  }
  public void handleCollision(MapObject mo) {//tBD
  }

  public void animate() {//TBD
    image (trap, (float)x, (float)y);
  }
}