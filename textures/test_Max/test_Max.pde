import java.util.*;

LinkedList<MapObject> l;
PImage tile;
HashMap<String, PImage> images;
GMap map;
Stack<Tile> st;
Tile old = null;
void setup()
{
  size(1200, 1200);
  background(0);
  map = GMap.getInstance();
  l = map.getAllObjects();
  tile = loadImage("wall.png");
  images = new HashMap<String, PImage>();
  st = new Stack<Tile>();
}

void draw()
{
  background(0);
  for (MapObject mo : l)
  {
    PImage i = null;
    if (mo instanceof Player) 
    {
      Player p= (Player)mo; 
      p.loadWalkingAnimation(); 
      p.move(); 
      //p.setDirectionTowards( map.getGrid()[0][0] );
      try
      {
      Pathfinder pa = new Pathfinder(22);
      if (st.empty())
      {
         st = pa.getPath(p.getCurrentTile(), map.getGrid()[8][2]);
         Pathfinder.printStack(st); 
          st = pa.getPath(p.getCurrentTile(), map.getGrid()[8][2]);
         st.pop();
      }

      Tile ti = p.getCurrentTile();
      //System.out.println(ti.getMatrixX() + ", " + ti.getMatrixY());
      if (old == null || ti.getMatrixX() != old.getMatrixX() || ti.getMatrixY() != old.getMatrixY() )
      {
        Tile ti1 = st.pop();
        p.setDirectionTowards(ti1);
        old = ti;
        System.out.println("popped");
        System.out.println(ti1.getMatrixX() + ", " + ti1.getMatrixY());
      }



      } catch (Exception e) {}

    }
    String s = mo.getImage();
    if (!images.containsKey(mo.getImage()))
    {
      images.put(s, loadImage(s));
    }

    image(    images.get(s), (int)(mo.getX()), (int)(mo.getY()));

  }
}