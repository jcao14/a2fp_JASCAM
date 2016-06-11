
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
	
	
	//added
	public double getXLoc() {return xLoc;}
	public double getYLoc() {return yLoc;}
	
    public ObjectOverlapType isTouching(TouchRegion other)
    {
	if (this.xMax > other.getXMin() && this.yMin < other.getYMax() && this.yMax > other.getYMin() && this.xMin < other.getXMax())
	    {
          //zeroth: get other center point & calculate slope
		  double ox = other.getXLoc();
		  double oy = other.getYLoc();
		  double m;
		  
		  //first: take care of oddities
		  if( xLoc == ox ) {
			if( yLoc > oy ) {
				return( ObjectOverlapType.DOWN );
			}
			else {
				return( ObjectOverlapType.UP );
			}
		  }
		  else if( yLoc==oy ) {
			if( xLoc > ox ) {
				return( ObjectOverlapType.LEFT );
			}
			else {
				return( ObjectOverlapType.RIGHT );
			}		  
		  }
		  
		  //compute slope
		  m = (yLoc - oy)/(xLoc - ox);
		  
		  //then: determine if its relatively right or left
		  if( ox > xLoc ) { //right
			if( m > 0 ) { //positive slope, octants I or II
				if( m > 1.0 ) { //octant II
					return( ObjectOverlapType.UP );
				}
				else { //octant I
					return( ObjectOverlapType.RIGHT );
				}
			}
			else {  //negative slope, octants VII or VIII
				if( m > -1 ) {  //octant VIII
					return( ObjectOverlapType.RIGHT );
				}
				else {  //octant VII
					return( ObjectOverlapType.DOWN );
				}
			}
		  }
		  else {  //left
			if( m > 0 ) { //positive slope, octants V or VI
				if( m > 1.0 ) { //octant VI
					return( ObjectOverlapType.DOWN );
				}
				else { //octant V
					return( ObjectOverlapType.LEFT );
				}
			}
			else {  //negative slope, octants III or IV
				if( m > -1 ) {  //octant III
					return( ObjectOverlapType.UP );
				}
				else {  //octant IV
					return( ObjectOverlapType.LEFT );
				}
			}
		  }
	    }
	return ObjectOverlapType.NONE;
    }
    
}