package map;
public interface MapObject
{
    public double getX();
    public double getY();
    public void moveTo(double x,double y);
    public void setCollidable(Boolean b);
    public boolean isCollidable(); //Objects can touch without colliding. Collision is only when objects can't move through each other
    public MapObjectType getMapObjectType(); //More robust than using instanceof
    public TouchRegion getTouchRegion();
    public String getImage();
}
