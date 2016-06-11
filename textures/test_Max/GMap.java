
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
    // [noncollidables added in front] [collidables added in back] [last element is player]
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
  if (!(mo instanceof Player))
  {
     mo.moveTo(x + mo.getX(), y + mo.getY());   
  }

	    }
    }

    public void addObject(MapObject a, int x, int y)
    {
	a.moveTo(x,y);
	if (a instanceof Player)
	    {
		collidables.add(a);
		allObjects.addLast(a);
	    }
	else if (a.isCollidable())
	    {
		collidables.add(a);
		allObjects.add(allObjects.size() - 1, a);
	    }
	else
	    {
		noncollidables.add(a);
		allObjects.add(0, a);
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
  Player p = new Player();
  addObject(p, 400, 650);
	Tile[][] tiles = new Tile[20][20];
	int hOffset = 50;
	int vOffset = 50;
	int offset2 = 0;
	int offset1 = 0;
	for (int i = 0; i < tiles.length; i++)
	    {
		offset1 = 0;
		for (int j = 0; j < tiles[0].length; j++)
		    {
			Tile t = null;
			if (((i > 3) &&( j == 4))|| ((i == 5)&&(j > 2)&&(j < 8)))
			    {
				t = new Wall("wall.png", 50, 50);
			    }
			else
			    {
				t = new Floor("floor.png", 50, 50);
			    }
			tiles[i][j] = t;
			t.setMatrixX(i);
			t.setMatrixY(j);
			addObject(t, offset1, offset2);
			offset1 += hOffset;
		    }
		offset2 += vOffset;
	    }

	return tiles;
    }
}