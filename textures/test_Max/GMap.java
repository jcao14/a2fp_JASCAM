import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
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

  private final String _WALL = "0";
  private final String _FLOOR = "1";
  private final String _PLAYER = "2";


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
    MapItem it = new MapItem(0, 50, 50);
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
  public void moveAll(double x, double y)
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
    a.moveTo(x, y);
    if (a instanceof Player)
    {
      pl = (Player)a;
      collidables.add(a);
      allObjects.addLast(a);
    } else if (a.isCollidable())
    {
      collidables.add(a);
      allObjects.add(allObjects.size() - 1, a);
    } else
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
    } else
    {
      noncollidables.remove(r);
    }
  }

  public Tile[][] generate()
  {
    Player p = new Player();

    Tile[][] tiles = new Tile[26][26];
    String[] readMap = new String[26];
    String[][] splitMap = new String[26][26];
    int counter = 0;
    //=============READS MAP============
    //try {
      readMap[0] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
      readMap[1] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
      readMap[2] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
      readMap[3] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
      readMap[4] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0";
      readMap[5] = "0 0 0 0 0 1 1 1 1 1 1 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0";
      readMap[6] = "0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0";
      readMap[7] = "0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0";
      readMap[8] = "0 0 0 0 0 1 1 2 1 1 1 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0";
      readMap[9] = "0 0 0 0 0 1 1 1 1 1 1 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0";
     readMap[10] = "0 0 0 0 0 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
     readMap[11] = "0 0 0 0 0 0 0 1 1 0 0 0 1 1 1 0 0 0 0 1 1 1 0 0 0 0";
     readMap[12] = "0 0 0 0 0 0 0 1 1 0 0 1 1 1 1 0 0 0 0 1 1 1 0 0 0 0";
     readMap[13] = "0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 1 1 1 0 0 0 0";
     readMap[14] = "0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 1 1 1 0 0 0 0";
     readMap[15] = "0 0 0 0 0 0 0 1 1 0 0 1 1 1 1 0 0 0 0 1 1 1 0 0 0 0";
     readMap[16] = "0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 1 1 1 1 1 1 1 0 0 0 0";
     readMap[17] = "0 0 0 0 0 1 1 1 1 1 0 0 0 0 0 1 1 1 1 1 1 1 0 0 0 0";
     readMap[18] = "0 0 0 0 0 1 1 1 1 1 0 0 0 0 0 1 1 0 0 1 1 1 0 0 0 0";
     readMap[19] = "0 0 0 0 0 1 1 1 1 1 0 0 0 0 0 1 1 0 0 1 1 1 0 0 0 0";
     readMap[20] = "0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 0 0 0 0";
     readMap[21] = "0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 0 0 0 0";
     readMap[22] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
     readMap[23] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
     readMap[24] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
     readMap[25] = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
      /*File f = new File ("C:\\Users\\James\\Documents\\Processing\\JASC\\a2\\a2fp_JASCAM\\textures\\test_Max\\world.txt");
      System.out.println(f.exists());
      Scanner reader = new Scanner (f);
      while (reader.hasNext()) {
        readMap[counter]= reader.nextLine();
        counter++;
      }*/
  //  }
  //  catch (FileNotFoundException ex) {
 //     System.out.println("This should not happen. Stop goofing around.");
  //  }

    for (int i =0; i<26; i++) {
      String[] line = readMap[i].split(" ");
      for (int j=0; j<26; j++) {
        splitMap[i][j] = line[j];
      }
    }

    //=====================================

    for (int i =0; i<26; i++) {
      int ycor = i * 50 +25;

      for (int j=0; j<26; j++) {
        int xcor = j * 50 +25;

        switch (splitMap[i][j]) {

        case _WALL:
          tiles[i][j] =  new Wall("wall.png", 50.0d, 50.0d);
          if (allObjects.size() != 0) {
            allObjects.add(allObjects.size()-1, tiles[i][j]);
          } else {
            allObjects.add(0, tiles[i][j]);
          }
          break;

        case _FLOOR:
          tiles[i][j] =  new Floor("floor.png", 50.0d, 50.0d);
          noncollidables.add(tiles[i][j]);
          allObjects.add(0, tiles[i][j]);
          break;

        case _PLAYER:

          tiles[i][j] =  new Floor("floor.png", 50.0d, 50.0d);
          noncollidables.add(tiles[i][j]);
          allObjects.add(0, tiles[i][j]);
          p.moveTo(xcor, ycor);
          break;
        }
      }
    }
    return tiles;
  }
  /*	Player p = new Player();
   	addObject(p, 801, 600);
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
   
   
   	return tiles;*/

}