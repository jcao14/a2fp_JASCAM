public class Tile extends AnimatedMapObject {

  int tile_type;
  
  PImage type;
  PImage wall;
  PImage floor;


  public Tile (int type) {
    super (0, 0, 50, 50, MapObjectType.values()[0]);
    tile_type = type; // the first four parameters are arbitary. Coordinaes are modded in MapMaker. Size is scaled.
    wall = loadImage("wall.png");
    floor = loadImage("floor.png");
  }
  
  public void animate() {
    image(type, (float)x, (float)y);
  }
  
  public int getType(){
    return tile_type;
  }
}