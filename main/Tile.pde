//Tile class
//Tile extends MapObject - see, integration

public class Tile extends AnimatedMapObject {

  private int tile_type;

  //public static final int TILE_FLOOR = 1;
  //public static final int TILE_WALL = 0;

  public PImage tile;
  
  /* Processing fails at statics, 
  //preload 1 static image here, all other instances will have references
  public static final PImage IMG_WALL = loadImage("wall.png");
  public static final PImage IMG_FLOOR = loadImage("floor.png");
  */

  //giving constructor an image param so each instance carries a reference
  //NOT a sperately loaded image
  public Tile (int type, PImage img) {
    super (0, 0, 100, 100, MapObjectType.values()[0]);
    tile = img;
    //makes it Type TILE based on the enum crap
    // the first four parameters are arbitary. Coordinaes are modded in MapMaker. Size is scaled. But super needs to be called.
    tile_type = type;
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
  public void setType(PImage image, int type) {
    tile_type = type;
    tile = image;
  }



  public void handleCollision(MapObject mo) {
    ; 
    //nothing needs to be done; tiles do not react to collisions
    //players and mobs will react to wall collisions in their method
    //walls being destroyed by bullets is not featured in beta version
  }
}