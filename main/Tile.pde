public class Tile extends AnimatedMapObject {

  int tile_type;

  PImage tile;
  PImage wall;
  PImage floor;


  public Tile (int type) {
    super (0, 0, 50, 50, MapObjectType.values()[0]);
    tile_type = type; // the first four parameters are arbitary. Coordinaes are modded in MapMaker. Size is scaled.
    wall = loadImage("wall.png");
    floor = loadImage("floor.png");
  }

  public void animate() {
    image(tile, (float)x, (float)y);
  }

  public int getType() {
    return tile_type;
  }

  public void setWall () {
    tile = wall;
  }

  public void setFloor() {
    tile = floor;
  }

  public void handleCollision(MapObject mo) {
  }
}