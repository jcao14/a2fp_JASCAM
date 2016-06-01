package tile;

import map.TouchRegion;
import map.MapObject;
import map.MapObjectType;


public class Tile implements MapObject
{
    protected double x;
    protected double y;
    protected boolean collidable;
    protected String frame;
    protected MapObjectType type;
    protected double sizeX;
    protected double sizeY;

    public double getX()
    {
	return x;
    }

    public double getY()
    {
	return y;
    }

    public void moveTo(double X, double Y)
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
	return frame;
    }

    public TouchRegion getTouchRegion()
    {
	TouchRegion T = new TouchRegion(x, y, sizeX, sizeY);
	return T;
    }
}
