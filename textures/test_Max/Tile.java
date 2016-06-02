


public class Tile implements MapObject
{
    protected int matrixX;
    protected int matrixY;
    protected double x;
    protected double y;
    protected boolean collidable;
    protected String frame;
    protected MapObjectType type;
    protected double sizeX;
    protected double sizeY;

    public int getMatrixX()
    {
	return matrixX;
    }

    public int getMatrixY()
    {
	return matrixY;
    }

    public void setMatrixX(int k)
    {
	matrixX = k;
    }

    public void setMatrixY(int k)
    {
	matrixY = k;
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
