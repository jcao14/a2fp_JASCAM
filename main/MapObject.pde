
/* MapObject.java
 *
 * This class will be the superclass of all classes to be drawn
 *
 * Purpose of this class is to standardize positioning, velocity, and collision handling
 */

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;



public abstract class MapObject {

  /*=======================================STATIC FIELD=====================================*/
  public static final int STEP = 1;

  /*============================================FIELD=====================================*/
  protected double x;
  protected double y;

  protected double sizeX;
  protected double sizeY;

  protected double[] velocity;

  protected MapObjectType mapObjectType;

  //protected boolean collidable;  --moved to subobject, layering aspects
  //protected Set<ObjectOverlapType> collidingDirection;  --moved ^^
  //protected Animation frames;    --awesome idea; just moved to AbstractAnimatedMapObject
  //protected MapObjectType type;  --nested; easier to read 
  //protected Velocity velocity;   --removed; "excessive class"
  //protected Tile tile;           --removed; xcor ycor with Tile[][] global keeps track
  //protected boolean disable      --removed; if something cant move, set velocity to NULL

  /*=========================================CONSTRUCTER==================================*/
  public MapObject( double x_arg, double y_arg, 
    double size_x, double size_y, 
    MapObjectType type
    ) { //to be used by super only!
    setXY(x_arg, y_arg);
    setSize(size_x, size_y);
    setMapObjectType(type);
    this.velocity = new double[] {0.0, 0.0};
  }

  /*=======================================SETTERS & GETTERS=====================================*/
  public double getX() { 
    return x;
  }

  public double getY() { 
    return y;
  }

  public void setXY(double X, double Y) {
    this.x = X;
    this.y = Y;
  }

  public double[] getSize() {
    return( new double[] {this.sizeX, this.sizeY} );
  }

  protected void setSize(double size_x, double size_y) {  //to be used by constructor, size is static
    this.sizeX = size_x; 
    this.sizeY = size_y;
  }

  public double[] getVelocity() { 
    return velocity;
  }

  public void setVelocity(double[] new_velocity) { 
    this.velocity = new_velocity;
  }

  public MapObjectType getMapObjectType() { 
    return mapObjectType;
  }

  public void setMapObjectType( MapObjectType t) { 
    this.mapObjectType = t;
  }

  /*=======================================SPEED & DIRECTION=====================================*/
  public void setSpeed(double s)
  {
    double oldspd = getSpeed();
    velocity[0] = (s/oldspd);
    velocity[1] = (s/oldspd);
  }

  public double getSpeed() {
    double sum = (velocity[0])*(velocity[0]) + (velocity[1])*(velocity[1]);
    return( Math.sqrt(sum) );
  }

  //dont use this plz:
  public void setDirection (double angle)
  {
    double old = getDirection();
    double rot = angle - old;

    velocity[0] = velocity[0]*Math.cos(rot) - velocity[1]*Math.sin(rot);
    velocity[1] = velocity[0]*Math.sin(rot) + velocity[1]*Math.cos(rot);
  }

  //dont use this plz:
  public double getDirection()
  {
    return Math.atan( velocity[1]/velocity[0] );
  }
  
  

  /*=======================================Move=====================================*/
  public void move() {
    if ( velocity!=null ) {
      setXY( getX()+velocity[0]*STEP, getY()+velocity[1]*STEP );
    }
  } //I think we don't need this anymore.
  
   //for non player movements to sim camera
  public void moveTo(double X, double Y)
  {
    this.x = X;
    this.y = Y;
  }
}