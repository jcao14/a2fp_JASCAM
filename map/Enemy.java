import java.util.LinkedList;
public interface Enemy extends Character
{
    public void getNextPatrol();
    public boolean nearbyPlayer();
    public void invokeAI();
    public void setDirectionTo(Tile t);
    //public TilePath getPath();
    public LinkedList<Enemy> getGroup();
}
