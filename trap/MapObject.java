
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


public class MapObject {

    /*============================================FIELD=====================================*/
    protected double x;
    protected double y;

    protected double sizeX;
    protected double sizeY;

    protected double[] velocity;

    public enum MapObjectType { //nested enum
	TILE, //TBD
    }

    protected MapObjectType mapObjectType;

    protected boolean collidable;
    protected Set<ObjectOverlapType> collidingDirection;

	
    //protected Animation frames;    --awesome idea; just moved to AbstractAnimatedMapObject
    //protected MapObjectType type;  --nested; easier to read 
    //protected Velocity velocity;   --removed; "excessive class"
    //protected Tile tile;           --removed; xcor ycor with Tile[][] global keeps track
    //protected boolean disable      --removed; if something cant move, set velocity to NULL
    
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
	
    public MapObjectType getMapObjectType() { return type; }
	
    protected void setType(MapObject.MapObjectType t) { this.mapObjectType = t; }  //for constructor
	
    public void setCollidable(boolean c) { this.collidable = c; }

    public boolean isCollidable() { return collidable; }

    public TouchRegion getTouchRegion(){ //helper function
	TouchRegion T = new TouchRegion(x, y, sizeX, sizeY);
	return T;
    }

    //added map param because a list of all MapObjects will be held in a global list
    //returns of list of aliases of objects nearby caller
    protected LinkedList<MapObject> getNearby(double range, List<MapObject> map){
	LinkedList<MapObject> nearby = new LinkedList<MapObject>();

	for(int i=0; i<map.size(); i++) {
	    MapObject mo = map.get(i); //mo is an alias
	    double dx = mo.getX() - this.x;
	    double dy = mo.getY() - this.y;

	    if (!(dx > range || dy > range)){
		double dist = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
		if(dist <= range) {
		    nearby.add(mo);
		}
	    } 
	}
	return nearby;
    }

    //takes a param map which is a List of all MapObjects
    //returns a List of aliases of all MapObjects "touching" the caller
    //also updates collidingDirection
    public List<MapObject> getTouching(List<MapObject> map)
    {
	double touchingRadius = 1.5*Math.max(sizeX,sizeY);  //!! THE MAGIK NUMBER!!!!
	
	collidingDirection = new LinkedList<ObjectOverlapType>();
	List<MapObject> nearby = this.getNearby(touchingRadius, map);
	TouchRegion region = this.getTouchRegion();
	List<MapObject> touching = new ArrayList<MapObject>();
	for (int i=0; i<nearby.size(); i++)
	    {
		MapObject mo = nearby.get(i);
		TouchRegion otherRegion = mo.getTouchRegion();
		ObjectOverlapType interaction = region.isTouching(otherRegion);
		if (interaction.getIsTouching())
		    {
			touching.add(mo);
			if (mo.isCollidable()) {
				collidingDirection.add(interaction);
			    }
			else {
			    //NONE?
			}
		    }
	    }
	
	return touching;
    }

    /* moved to GU (GameUtil class)
    public double getDistance(MapObject other)
    */
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
   
    /*==========================================METHS=====================================*/
    
    //move touching stuff here
    
    //returns array, 1st tile-x-cor, 2nd is tile-y-cor
    //param floor - matrix of tier
    //USAGAE:
    //    a = getTileCor(floor)
    //    floor[a[0]][a[1]];
    int[] getTileCor(Tile[][] floor) {
	
	TouchRegion treg = this.getTouchRegion();
	int[] ret = new int[2];
	int i, j;
	
	for(i=0; i<floor.length; i++)
	    for( j=0; j<floor[0].length; j++ ) {
		if( (treg.touching( floor[i][j].getTouchRegion() )).getIsTouching() ) {
		    ret[0] = j;
		    ret[1] = i;
		    return(ret);
		}
	    }
	System.out.println("Error getting tile cors, returning NULL");
	return(NULL);

    }

	public double getSpeed() {
		GU.getMagnitude(this.velocity);
	}

	public double getAngle() {
		GU.getAngle(this.velocity);
	}

}

}
