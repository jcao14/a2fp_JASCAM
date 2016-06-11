import java.util.*;
LinkedList<MapObject> l;
PImage tile;
HashMap<String, PImage> images;
GMap map;
void setup()
{
  size(1200, 1200);
  map = GMap.getInstance();
  l = map.getAllObjects();
  tile = loadImage("wall.png");
  images = new HashMap<String, PImage>();
}

void draw()
{
  background(255);
  for (MapObject mo : l)
  {
    PImage i = null;
    if(mo instanceof Player) 
  {
  Player p= (Player)mo; 
  p.loadWalkingAnimation(); 
  p.move(); 
  p.setDirection(2);
  }
  String s = mo.getImage();
  if (!images.containsKey(mo.getImage()))
  {
      images.put(s, loadImage(s));
  }

    image(    images.get(s), (int)(mo.getX()), (int)(mo.getY()));
  }
}