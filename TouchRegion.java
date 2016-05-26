public class TouchRegion
{
    private double xLoc;
    private double yLoc;
    private double sizeX;
    private double sizeY;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public TouchRegion(double x,double y,double xSize,double ySize)
    {
	xLoc = x;
	yLoc = y;
	sizeX = xSize;
	sizeY = ySize;
	xMin = xLoc - xSize/2;
	xMax = xLoc + xSize/2;
	yMin = yLoc - ySize/2;
	yMax = yLoc + ySize/2;	
    }

    public double getXMin() {return xMin;}
    public double getYMin() {return yMin;}
    public double getXMax() {return xMax;}
    public double getYMax() {return yMax;}

    public ObjectOverlapType isTouching(TouchRegion other)
    {
	if (this.xMax > other.getXMin())
	    {
		return ObjectOverlapType.RIGHT;
	    }
	else if (this.xMin < other.getXMax())
	    {
		return ObjectOverlapType.LEFT;
	    }
	else if (this.yMin < other.getYMax())
	    {
		return ObjectOverlapType.UP;
	    }
	else if (this.yMax > other.getYMin())
	    {
		return ObjectOverlapType.DOWN;
	    }
	else
	    {
		return ObjectOverlapType.NONE;
	    }
    }
    
}
