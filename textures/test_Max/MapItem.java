public class MapItem implements MapObject
{
    private double x;
    private double y;
    private boolean collidable;
    private MapObjectType type;
    private double sizeX;
    private double sizeY;
    private int id;
    ItemDataProvider idp;
    
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
idp = ItemDataProvider.getInstance();
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
      
	return idp.getData(id).getImage();
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