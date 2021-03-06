import java.util.LinkedList;
public interface AnimatedMapObject extends MapObject
{
    public void move();
    public LinkedList<MapObject> getTouching();
    public void setSpeed(double s);
    public void setDirection(double angle);
    public double getDirection();
    public Velocity getVelocity();
    public void setAnimation (Animation animation);
    public void onAnimationEnd(AnimationType type);
    public void handleTouch(LinkedList<MapObject> touching);
    public LinkedList<MapObject> getNearby(double range);
    public Tile getCurrentTile();
    public double getDistance(MapObject other);
    public void enableMovement();
    public void disableMovement();
}
