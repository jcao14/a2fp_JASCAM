
public class TouchRegion
{
    private double xLoc;
    private double yLoc;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public TouchRegion(double x,double y,double xSize,double ySize)
    {
	xLoc = x + xSize/2;
	yLoc = y + ySize/2;
	xMin = xLoc - xSize/2;
	xMax = xLoc + xSize/2;
	yMin = yLoc - ySize/2;
	yMax = yLoc + ySize/2;	
    }

    public double getXMin() {return xMin;}
    public double getYMin() {return yMin;}
    public double getXMax() {return xMax;}
    public double getYMax() {return yMax;}
    public double getXLoc() {return xLoc;}
    public double getYLoc() {return yLoc;}

    public ObjectOverlapType isTouching(TouchRegion other)
    {
	if (this.xMax > other.getXMin() && this.yMin < other.getYMax() && this.yMax > other.getYMin() && this.xMin < other.getXMax())
	    {
          double dx = this.xLoc - other.getXLoc();
          double dy = this.yLoc - other.getYLoc();
          double ax = Math.abs(dx);
          double ay = Math.abs(dy);
          
          if (ay > ax)
          {
             if (dy > 0)
             {
               return ObjectOverlapType.DOWN;
             }
             if (dy < 0)
             {
               return ObjectOverlapType.UP;
             }
          }
          else if (ay < ax)
          {
             if (dx < 0)
             {
              return ObjectOverlapType.RIGHT; 
             }
             if (dx > 0)
             {
               return ObjectOverlapType.LEFT;
             }
          }
          //return ObjectOverlapType.LEFT;
	    }
		return ObjectOverlapType.NONE;
    }
    
}