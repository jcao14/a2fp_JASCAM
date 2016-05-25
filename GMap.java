import java.util.LinkedList;
public class GMap
{
    private static GMap instance = null;
    public static GMap getInstance()
    {
	if (instance == null)
	    {
		//generate map here
	    }
	return instance;
    }

    private Player player = null;
    private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
    // [first element is player] [collidables added in front] [noncollidables added in back]
    private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
    private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();

    //shift all rendered map items on screen when player moves, instead of moving player
    public void moveAll(int x, int y)
    {
	for (MapObject mo : allObjects)
	    {
		mo.move(x, y);
	    }
    }

    public void addObject(MapObject a, int x, int y)
    {
	a.moveTo(x,y);
	if (a.isCollidable())
	    {
		collidables.add(a);
		allObjects.add(1, a);
	    }
	else
	    {
		noncollidables.add(a);
		allObjects.addLast(a);
	    }
	
    }

    public void removeObject(MapObject r)
    {
	allObjects.remove(r);
	if (r.isCollidable())
	    {
		collidables.remove(r);
	    }
	else
	    {
		noncollidables.remove(r);
	    }
    }
}
