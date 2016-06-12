import java.util.LinkedList;
import java.util.Stack;
public abstract class Enemy extends Character
{
    protected int exp;
    protected LinkedList<Tile> patrol;
    protected boolean aggro;
    protected int aggroRange;
    protected Pathfinder pather;
    protected Stack<Tile> path;
    protected Tile old = null;
    protected Pathfinder pPather;
    
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
