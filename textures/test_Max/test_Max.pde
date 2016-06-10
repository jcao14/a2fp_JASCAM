import java.util.*;
LinkedList<MapObject> l;
PImage tile;
GMap map;
void setup()
{
  size(1200, 1200);
  map = GMap.getInstance();
  l = map.getAllObjects();
  tile = loadImage("wall.png");
}

void draw()
{
  background(255);
  for (MapObject mo : l)
  {
    PImage i = loadImage(mo.getImage());
    if(mo instanceof Player) {Player p= (Player)mo; p.loadWalkingAnimation(); p.move(); p.setDirection(2);}
    image(i, (int)(mo.getX()), (int)(mo.getY()));
  }
}