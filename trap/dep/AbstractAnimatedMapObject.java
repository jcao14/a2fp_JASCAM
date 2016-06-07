
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.lang.Math;

public abstract class AbstractAnimatedMapObject implements AnimatedMapObject
{
    protected double x;
    protected double y;
    protected boolean collidable;
    protected Animation frames;
    protected MapObjectType type;
    protected double sizeX;
    protected double sizeY;
    protected Set<ObjectOverlapType> colliding;
    protected Velocity velocity;
    protected Tile tile;
    protected boolean disabled;

    public double getX()
    {
	return x;
    }

    public double getY()
    {
	return y;
    }

    public void moveTo(double X,double Y)
    {
	this.x = X;
	this.y = Y;
    }

    public void setCollidable(Boolean c)
    {
	this.collidable = c;
    }

    public boolean isCollidable()
    {
	return collidable;
    }

    public MapObjectType getMapObjectType()
    {
	return type;
    }

    public String getImage()
    {
	String URL = frames.getFrame();
        if (frames.next())
	    {
		onAnimationEnd(frames.getType());
	    }
	return URL;
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

    public Tile getTile()
    {
	return tile;
    }

    public double getDistance(MapObject other)
    {
	double dx = other.getX() - this.x;
        double dy = other.getY() - this.y;
	return Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
    }
    
    public Velocity getVelocity()
    {
	return velocity;
    }

    public void setSpeed(double s)
    {
	getVelocity().updateSpeed(s);
    }

    public void setDirection (double angle)
    {
	getVelocity().updateAngle(angle);
    }

    public double getDirection()
    {
	return getVelocity().getAngle();
    }

    public void disableMovement()
    {
	disabled = true;
    }

    public void enableMovement()
    {
	disabled = false;
    }

    public abstract void move();
    public abstract void handleTouch(LinkedList<MapObject> touching);
    public abstract void onAnimationEnd(AnimationType t);

    
}

