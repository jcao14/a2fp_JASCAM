
import java.util.*;
public class Tester
{
    public static void main(String[] args)
    {
	GMap map = GMap.getInstance();
	LinkedList<MapObject> l = map.getAllObjects();
	Pathfinder p = new Pathfinder(22);
	Tile[][] tiles = map.getGrid();
	Stack<Tile> s = p.getPath(tiles[7][2], tiles[13][6]);
	Pathfinder.printStack(s);
	map.spawnMonster();
	/*while(true)
	    {
		for (MapObject mo : map.getAllObjects())
		    {
			if (mo instanceof Monster)
			    {
				Monster mn = (Monster)mo;

				try
			        {
				    Tile tle=mn.getCurrentTile();
				    System.out.println(tle.getMatrixX() + ", " + tle.getMatrixY());
				    //Thread.sleep(600);
				} catch (Exception e){}
				String st = mn.getImage();
				mn.move();
			    }
		    }
		    }*/
	String[][] m = MapGenerator.getMap();
	for (int i = 0; i < m.length; i++)
	    {
		System.out.println();
		for(int j = 0; j < m[0].length; j++)
		    {
			System.out.print(m[i][j] + " ");
		    }
	    }
    }
}
