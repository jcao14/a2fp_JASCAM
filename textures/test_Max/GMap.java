import java.io.*;
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
    private int Cindex = 0;
    private Tile[][] grid;
    private Player pl;

    public GMap()
    {
	grid = generate();
    }

    public void spawnMonster()
    {
	Tile pt1 = getGrid()[10][10];
	Tile pt2 = getGrid()[10][12];
	Tile pt3 = getGrid()[13][10];
	Tile pt4 = getGrid()[14][16];
	LinkedList<Tile> pats = new LinkedList<Tile>();
	pats.addLast(pt1);
	pats.addLast(pt2);
	pats.addLast(pt3);
	pats.addLast(pt4);
	Monster m = new Monster(pats);
	addObject(m, (int)pt1.getX(), (int)pt1.getY());
	MapItem it = new MapItem(0,50,50);
	addObject(it, 700, 760);
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
		if (!(mo.getMapObjectType() == MapObjectType.PLAYER))
		    mo.moveTo(x + mo.getX(), y + mo.getY());
	    }
    }

    public Player getPlayer()
    {
	return pl;
    }

    public synchronized void addObject(MapObject a, int x, int y)
    {
	a.moveTo(x,y);
	if (a instanceof Player)
	    {
		pl = (Player)a;
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
		allObjects.add(Cindex, a);
		Cindex += 1;
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
	addObject(p, 650, 650);

	String[][] mapString = MapGenerator.getMap();
	Tile[][] tiles = new Tile[mapString.length][mapString[0].length];
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
			switch(mapString[i][j])
			    {
			    case "0":
				t = new Wall("wall.png", 50, 50);
				break;
			    case "1":
				t = new Floor("floor.png", 50, 50);
				break;
			    default:
				t = new Floor("floor.png", 50, 50);
				break;
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