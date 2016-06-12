import java.util.Stack;
public class Pathfinder
{
    private GMap map;
    private Stack<Tile> path;
    private int dist;
    
    public Pathfinder(int d)
    {
	dist = d;
	path = new Stack<Tile>();
	resetMap();
    }

    public void resetMap()
    {
	map = GMap.getInstance();
    }
    
    public Stack<Tile> getPath (Tile start, Tile dest)
    {
	path = new Stack<Tile>();
        int count = 0;
        getPath(start.getMatrixX(), start.getMatrixY(),start.getMatrixX(), start.getMatrixY(), dest.getMatrixX(), dest.getMatrixY(), count);

	return path;
    }

    private boolean getPath (int x0, int y0,int x1, int y1,int x2,int y2, int count)
    {
	//System.out.println("< " + x1 + ", " + y1 + ">");
	if (count > dist)
	    {
		//System.out.println(1);
		return false;
	    }
	if (x1 < 0 || y1 < 0 || x1 >= map.getGrid().length || y1 >= map.getGrid().length || map.getGrid()[x1][y1].isCollidable())
	    {
		//System.out.println(2);
		return false;
	    }
	if (x1 == x2 && y1 == y2)
	    {
		path.push(map.getGrid()[x1][y1]);
		return true;
	    }
	if (x1 - 1 != x0)
	    {
		if (getPath(x1, y1, x1-1, y1, x2, y2, count + 1))
		    {
			path.push(map.getGrid()[x1][y1]);
			return true;
		    }
	    }
	if (x1 + 1 != x0)
	    {
		if (getPath(x1, y1, x1+1, y1, x2, y2, count + 1))
		    {
			path.push(map.getGrid()[x1][y1]);
			return true;
		    }
	    }
	if (y1 - 1 != y0)
	    {
		if (getPath(x1, y1, x1, y1-1, x2, y2, count + 1))
		    {
			path.push(map.getGrid()[x1][y1]);
			return true;
		    }
	    }
	if (y1 + 1 != y0)
	    {
		if (getPath(x1, y1, x1, y1+1, x2, y2, count + 1))
		    {
			path.push(map.getGrid()[x1][y1]);
			return true;
		    }
	    }
	//System.out.println(3);
	return false;
    }

    //for testing
    public static void printStack(Stack<Tile> p)
    {
	Tile[][] game = GMap.getInstance().getGrid();
	String[][] board = new String[game.length][game[0].length];

	for (int i = 0; i < game.length; i++)
	    {
		for (int j = 0; j < game[0].length; j++)
		    {
			switch (game[i][j].getMapObjectType())
			    {
			    case WALL:
				board[i][j] = "W";
				break;
			    case FLOOR:
				board[i][j] = " ";
				break;
			    }
		    }
	    }
	
	Tile t = null;
	while (!p.empty())
	    {
		t = p.pop();
		//System.out.println("< " + t.getMatrixX() + ", " + t.getMatrixY() + ">");
		board[t.getMatrixX()][t.getMatrixY()] = ".";
	    }

	for (int i = 0; i < game.length; i++)
	    {
		for (int j = 0; j < game[0].length; j++)
		    {
			System.out.print(board[i][j]);
		    }
		System.out.println();
	    }	
    }
}