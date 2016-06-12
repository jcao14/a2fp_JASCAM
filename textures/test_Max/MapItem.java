public class MapItem implements MapObject
{
    private double x;
    private double y;
    private boolean collidable;
    private MapObjectType type;
    private double sizeX;
    private double sizeY;
    private int id;
    public double getSizeX()
    {
       return sizeX; 
    }
    
    public double getSizeY()
    {
       return sizeY; 
    }
   
    public MapItem(int i,double sx, double sy)
    {
	x=y=0;
	id = i;
	sizeX = sx;
	sizeY = sy;
	type = MapObjectType.ITEM;
    }
    
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
    }

    public boolean isCollidable()
    {
	return false;
    }

    public MapObjectType getMapObjectType()
    {
	return type;
    }

    public String getImage()
    {
      System.out.println("loaded item image");
	return "0.png";
    }

    public TouchRegion getTouchRegion()
    {
	TouchRegion T = new TouchRegion(x, y, sizeX+3, sizeY+3);
	return T;
    }

    public int getID()
    {
	return id;
    }
}