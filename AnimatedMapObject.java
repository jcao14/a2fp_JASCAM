public interface AnimatedMapObject extends MapObject
{
    public void move(int deltaX, int deltaY);
    public boolean checkTouching();
    public List<MapObject> getTouching();
    public void setSpeed();
    public double getSpeed();
    public void setDirection(double angle);
    public double getDirection();
    public Velocity getVelocity();
    public void setAnimation (Animation animation);
    public void onAnimationEnd(AnimationType type);
    public void handleTouch(List<MapObject>);
}
