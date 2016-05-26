import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.lang.Math;
public abstract class AbstractAnimatedMapObject implements AnimatedMapObject
{
    private double x;
    private double y;
    private boolean collidable;
    private Animation frames;
    private MapObjectType type;
    private double sizeX;
    private double sizeY;
    private Set<ObjectOverlapType> colliding;
    private Velocity velocity;

    public double getX()
    {
	return x;
    }

    public double getY()
    {
	return y;
    }

    public void moveTo(int X, int Y)
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
	return touching;
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

    public abstract void move();
    public abstract void handleTouch(LinkedList<MapObject> touching);
    public abstract void onAnimationEnd();

    
}
