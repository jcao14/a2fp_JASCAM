
import java.util.*;
public class Tester
{
    public static void main(String[] args)
    {
	GMap map = GMap.getInstance();
	LinkedList<MapObject> l = map.getAllObjects();
	for (MapObject mo : l)
	    {
		System.out.println("Floor: ");
		System.out.println("x: " + mo.getX());
		System.out.println("y: " + mo.getY());
	    }
    }
}
