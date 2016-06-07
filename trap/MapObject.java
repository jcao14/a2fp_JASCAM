
/* MapObject.java
 *
 * This class will be the superclass of all classes to be drawn
 *
 */

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.lang.Math;


public class MapObject {

    /*============================================FIELD=====================================*/
    protected double x;
    protected double y;

    protected double sizeX;
    protected double sizeY;

    protected boolean collidable;
    protected Set<ObjectOverlapType> colliding;

    protected double[] velocity;
    protected boolean disabled;

    protected Animation frames;

    public enum MapObjectType { //nested enum
	//TBD
    }

    protected MapObjectType type;

    //protected MapObjectType type;  --nested; easier to read 
    //protected Velocity velocity;   --removed; "excessive class"
    //protected Tile tile;           --removed; xcor ycor with Tile[][] global keeps track

    /*=========================================CONSTRUCTER==================================*/
    public MapObject(double x_arg, double y_arg, 
		     double size_x, double size_y, 
		     int tile_x, int tile_y,
		     Animations a ) { //to be used by super
	setXY(x_arg, y_arg);
	setSize(size_x, size_y);
    }

    /*============================================METHS=====================================*/
    public double getX()
    {
	return x;
    }

    public double getY()
    {
	return y;
    }

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

    private void setSize(double size_x, double size_y) {  //to be used by constructor, size is static
	this.sizeX = size_x; 
	this.sizeY = size_y; 
    }

//=============================================

    public MapObjectType getType()
    {
	return type;
    }

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

    public void setAnimation(Animation a)
    {
	frames = a;
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
    
    public double[] getVelocity()
    {
	return velocity;
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
    
}

}
