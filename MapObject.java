public interface MapObject
{
    public double getX();
    public double getY();
    public void moveTo(int x, int y);
    public boolean setCollidable();
    public boolen isCollidable(); //Objects can touch without colliding. Collision is only when objects can't move through each other
    public MapObjectType getMapObjectType(); //More robust than using instanceof
    public TouchRegion getTouchRegion();
    public String getFrame();
}
