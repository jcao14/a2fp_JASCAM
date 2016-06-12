/* CollidableMapObject
 *
 * This class is the direct class of MapObject and supers every other class; implements a different collision algorithm (sorry)
 *
 * 
 *
 */

public abstract class CollidableMapObject extends MapObject {

  public CollidableMapObject( double x_arg, double y_arg, 
    double size_x, double size_y, 
    MapObjectType type ) 
  { //to be used by super only!
    super(x_arg, y_arg, size_x, size_y, type);
  }

  public abstract void handleCollision(MapObject mo);
  //TBD ;)
}