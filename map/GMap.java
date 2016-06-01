package map;
import java.util.LinkedList;
import tile.*;
public class GMap
{
    private static GMap instance = null;

    public static GMap getInstance()
    {
	//just to make sure things compile:
	//Hazard h = new Hazard(1,1,HazardType.SPIKE, true);
	//Wall w = new Wall("a", 1,2);
	//Floor f = new Floor("b", 1, 2);
	//
	if (instance == null)
	    {
		//generate map here
	    }
	return instance;
    }

    //  private Player player = null;
    private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
    // [first element is player] [collidables added in front] [noncollidables added in back]
    private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
    private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();

    public LinkedList<MapObject> getAllObjects()
    {
	return allObjects;
    }

    public LinkedList<MapObject> getCollidables()
    {
	return collidables;
    }

    public LinkedList<MapObject> getNoncollidables()
    {
	return noncollidables;
    }
    
    //shift all rendered map items on screen when player moves, instead of moving player
    public void moveAll(double x,double y)
    {
	for (MapObject mo : allObjects)
	    {
		mo.moveTo(x + mo.getX(), y + mo.getY());
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
