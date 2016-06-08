import java.io.*;
import java.util.LinkedList;


public class GMap
{
  public static final String WALL = "W";
  public static final String FLOOR = "F";
  public Tile t;

  private static GMap instance = null;



  public static GMap getInstance(String map)
  {
    //just to make sure things compile:
    //Hazard h = new Hazard(1,1,HazardType.SPIKE, true);
    //Wall w = new Wall("a", 1,2);
    //Floor f = new Floor("b", 1, 2);
    //
    if (instance == null)
    {
      instance = new GMap(map);
    }
    return instance;
  }

  //  private Player player = null;
  private LinkedList<MapObject> allObjects = new LinkedList<MapObject>();
  // [first element is player] [collidables added in front] [noncollidables added in back]
  private LinkedList<MapObject> collidables = new LinkedList<MapObject>();
  private LinkedList<MapObject> noncollidables = new LinkedList<MapObject>();
  private Tile[][] grid;

  public GMap(String map)
  {
    grid = generate(map);
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
  public void moveAll(double x, double y)
  {
    for (MapObject mo : allObjects)
    {
      mo.moveTo(x + mo.getX(), y + mo.getY());
    }
  }

  public void addObject(MapObject a, int x, int y)
  {
    a.moveTo(x, y);
    if (a.isCollidable())
    {
      collidables.add(a);
      allObjects.add(1, a);
    } else
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
    } else
    {
      noncollidables.remove(r);
    }
  }

  public Tile[][] generate(String map)
  {
    Tile[][] world = new Tile[20][20];

    int hOffset = 6;
    int vOffset = 6;
    int offset2 = vOffset;
    int offset1 = hOffset;

    
    BufferedReader br = new BufferedReader(new FileReader(new File(map)));
    String row;
    String[] readMap = new String[10];
    while ((row = br.readLine()) != null) {
      for (int i =0; i < 20; i++) {
        readMap[i] = br.readLine();
      }
    }


    //readMap
    String[][] splitMap = new String[20][20];

    for (int i =0; i< world.length; i++) {
      String[]line = readMap[i].split(" ");
      for (int j=0; j< world[0].length; j++) {
        splitMap[i][j] = line[j];
      }
    }

    for (int i =0; i< world.length; i++) {
      offset1 = hOffset;
      for (int j=0; j< world[0].length; j++) {
        switch(splitMap[i][j]) {
          
        case WALL:
          t = new Wall ("wall.png", 20, 20);
          break;
        case FLOOR:
          t = new Floor ("floor.png", 20, 20);
          break;
        }
        world[i][j] = t;
        t.setMatrixX(j);
        t.setMatrixY(i);
        addObject(t, offset1, offset2);
        offset1 += 2 * hOffset;
      }
      offset2 += 2*vOffset;
    }
    return world;
  }
}