
/* MapObject.java
 *
 * This class will be the superclass of all classes to be drawn
 *
 */

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.lang.Math;


public abstract class MapObject {

    /*============================================FIELD=====================================*/
    protected double x;
    protected double y;

    protected double sizeX;
    protected double sizeY;

    protected double[] velocity;
    protected boolean disabled;  //should this be changed to movable? I'm keeping it for now

    public enum MapObjectType { //nested enum
	//TBD
    }

    protected MapObjectType type;

	protected boolean collidable;
    protected Set<ObjectOverlapType> colliding;

	
    //protected Animation frames;    --awesome idea; just moved to AbstractAnimatedMapObject
    //protected MapObjectType type;  --nested; easier to read 
    //protected Velocity velocity;   --removed; "excessive class"
    //protected Tile tile;           --removed; xcor ycor with Tile[][] global keeps track

    /*=========================================CONSTRUCTER==================================*/
    public MapObject( double x_arg, double y_arg, 
					  double size_x, double size_y, 
					  MapObject.MapObjectType type,
					  boolean isMovementDisabled, boolean isItCollidable
			 ) { //to be used by super
		setXY(x_arg, y_arg);
		setSize(size_x, size_y);
		setType(type);
		this.velocity = new double[] {0.0, 0.0};
    }

    /*=======================================SETTERS & GETTERS=====================================*/
    public double getX() { return x; }

    public double getY() { return y; }

    /* --removed; misnomer
    public void moveTo(double X,double Y)
    {
	this.x = X;
	this.y = Y;
    }
    */

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
	
    public double[] getVelocity() { return velocity; }
	
	public double[] setVelocity(double[] new_velocity) { this.velocity = new_velocity; }
	
    public MapObjectType getType() { return type; }
	
	protected void setType(MapObject.MapObjectType t) { this.type = t; }  //for constructor
	
//=============================================

    public String getImage()
    {
	String URL = frames.getFrame();
        if(frames.next())
	    {
		onAnimationEnd(frames.getType());
	    }
	return URL;
    }

    public void setCollidable(Boolean c)
    {
	this.collidable = c;
    }

    public boolean isCollidable()
    {
	return collidable;
    }

    public TouchRegion getTouchRegion()
    {
	TouchRegion T = new TouchRegion(x, y, sizeX, sizeY);
	return T;
    }

    public LinkedList<MapObject> getNearby(double range)
    {
	GMap map = GMap.getInstance();
	LinkedList<MapObject> nearby = new LinkedList<MapObject>();
	for (MapObject mo : map.getAllObjects())
	    {
	        double dx = mo.getX() - this.x;
		double dy = mo.getY() - this.y;
		if (!(dx > range || dy > range))
		    {
		        double dist = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
			    if (dist <= range)
				{
				    nearby.add(mo);
				}
		    } 
	    }
	return nearby;
    }

    public LinkedList<MapObject> getTouching()
    {
	colliding.clear();
	LinkedList<MapObject> nearby = getNearby(1.5*Math.max(sizeX, sizeY));
	TouchRegion region = getTouchRegion();
	LinkedList<MapObject> touching = new LinkedList<MapObject>();
	for (MapObject mo : nearby)
	    {
		TouchRegion otherRegion = mo.getTouchRegion();
		ObjectOverlapType interaction = region.isTouching(otherRegion);
		if (interaction.getIsTouching())
		    {
			touching.add(mo);
			if (mo.isCollidable())
			    {
				colliding.add(interaction);
			    }
		    }
	    }
	Tile t = null;
	double dist1 = 1000;
	for (MapObject mo2 : touching)
	    {
		if (mo2.getMapObjectType() == MapObjectType.FLOOR || mo2.getMapObjectType() == MapObjectType.HAZARD || mo2.getMapObjectType() == MapObjectType.SPECIAL)
		    {
			double dist2 = this.getDistance(mo2);
			if (dist2 < dist1)
			    {
				dist1 = dist2;
				t = (Tile)mo2;
			    }
		    }
	    }
	tile = t;
	return touching;
    }

    public double getDistance(MapObject other)
    {
	double dx = other.getX() - this.x;
        double dy = other.getY() - this.y;
	return Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
    }
    
    public void setSpeed(double s)
    {
	//getVelocity().updateSpeed(s);
    }

    public void setDirection (double angle)
    {
	//getVelocity().updateAngle(angle);
    }

    public double getDirection()
    {
	Math.tan( velocity[1]/velocity[0] );
    }

    public void disableMovement()
    {
	disabled = true;
    }

    public void enableMovement()
    {
	disabled = false;
    }
   
    /*==========================================ABSTRCT=====================================*/
	//draws the image
	//will implement Animation for AbstractAnimatedMapObject
	//will be static picture for tile
	public abstract void drawImage();
	
	
}

}
