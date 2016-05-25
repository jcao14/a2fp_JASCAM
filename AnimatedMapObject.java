public interface AnimatedMapObject extends MapObject
{
    public void move(int deltaX, int deltaY);
    public boolean checkTouching(MapObject other);
    public List<MapObject> getTouching();
    public void setSpeed();
    public void setDirection(double angle);
    public Velocity getVelocity();
    public void setAnimation (Animation animation);
    public void onAnimationEnd(AnimationType type);
    public void handleTouch(List<MapObject>);
    public List<MapObject> getNearby();
}
