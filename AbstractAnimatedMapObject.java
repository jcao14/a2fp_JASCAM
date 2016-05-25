public abstract class AbstractAnimatedMapObject implements AnimatedMapObject
{
    private double x;
    private double y;
    private boolean collidable;
    private Animation frames;
    private MapObjectType type;
    private double sizeX;
    private double sizeY;

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

    public boolean getCollidable()
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

    public abstract void onAnimationEnd();
}
