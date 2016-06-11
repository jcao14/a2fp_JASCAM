import java.util.LinkedList;
public abstract class Enemy extends Character
{
    private int exp;
    private LinkedList<Tile> patrol;
    private Tile target;
    private boolean aggro;
    private int aggroRange;
    private Pathfinder pather;
    
    public abstract void loadAttackAnimation();
    public abstract void loadWalkingAnimation();
    public abstract void loadSpawnAnimation();
    public abstract void loadKillAnimation();
    public abstract void onAnimationEnd(AnimationType t);
    public abstract void move();
    public abstract void handleTouch(LinkedList<MapObject> touching);
    public abstract void kill();
    public abstract void handleItemDrop();
}
