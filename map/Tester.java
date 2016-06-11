
import java.util.*;
public class Tester
{
    public static void main(String[] args)
    {
	GMap map = GMap.getInstance();
	LinkedList<MapObject> l = map.getAllObjects();
	Pathfinder p = new Pathfinder(19);
	Tile[][] tiles = map.getGrid();
	Stack<Tile> s = p.getPath(tiles[7][2], tiles[13][6]);
	Pathfinder.printStack(s);
    }
}
