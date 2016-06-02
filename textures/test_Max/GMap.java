
import java.util.LinkedList;
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
		instance = new GMap();
	    }
	return instance;
    }

    //  private Player player = null;
    private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
    // [first element is player] [collidables added in front] [noncollidables added in back]
    private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
    private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();
    private Tile[][] grid;

    public GMap()
    {
	grid = generate();
    }

    public Tile[][] getGrid()
    {
	return grid;
    }

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

    public Tile[][] generate()
    {
	Tile[][] tiles = new Tile[30][40];
	int hOffset = 25;
	int vOffset = 25;
	int offset2 = vOffset;
	int offset1 = hOffset;
	for (int i = 0; i < tiles.length; i++)
	    {
		offset1 = hOffset;
		for (int j = 0; j < tiles[0].length; j++)
		    {
      String s = "";
      if (j%2 > 0) {s = "floor.png";} else {s = "wall.png";}
			Tile t = new Floor(s, 11, 11);
			tiles[i][j] = t;
			addObject(t, offset1, offset2);
			offset1 += 2*hOffset;
		    }
		offset2 += 2*vOffset;
	    }
	return tiles;
    }
}