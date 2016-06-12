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

  public boolean isNearby(MapObject other, double r) {
    double dx = other.getX() + other.getSizeX()/2 - this.x + this.sizeX/2;
    double dy = other.getY() + other.getSizeY()/2 - this.y + this.sizeY/2;
    return(Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2))) < r);
  }

  public List<MapObject> getAllColliding(List<MapObject> all) {
    double colliding_radius = 1.5 * Math.sqrt(this.sizeX*this.sizeX+this.sizeY*this.sizeY);
    List ret = new ArrayList<MapObject>();
    for(int i=0; i<all.size(); i++) {
      MapObject mo = all.get(i);
      if( isNearby( mo, colliding_radius ) && this!=mo ) {
        ret.add( mo );
      }
    }
    return(ret);
  }

  public abstract void handleCollision(MapObject mo);
  //TBD ;)
}