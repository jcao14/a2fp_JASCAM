public class Tile extends AnimatedMapObject {

  int tile_type;

  PImage tile;
  PImage wall;
  PImage floor;


  public Tile (int type) {
    super (0, 0, 100, 100, MapObjectType.values()[0]);//makes it Type TILE based on the enum crap
    tile_type = type; // the first four parameters are arbitary. Coordinaes are modded in MapMaker. Size is scaled. But super needs to be called.
    
    //preloads tile images
    wall = loadImage("wall.png");
    floor = loadImage("floor.png");

  }

//draws tiles based on Pimage tile (which changes due to MapMaker) and coordinates
  public void animate() {
    image(tile, (float)x, (float)y);
  }
//We're gonna need to for collisions.
  public int getType() {
    return tile_type;
  }
  
//Setters for tile iamge
  public void setWall () {
    tile = wall;
  }

  public void setFloor() {
    tile = floor;
  }

  public void handleCollision(MapObject mo) {//IDK MAN
  }
}