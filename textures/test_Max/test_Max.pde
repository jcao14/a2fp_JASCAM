import java.util.*;
LinkedList<MapObject> l;
PImage tile;
GMap map;
void setup()
{
  size(1200,1200);
  map = GMap.getInstance();
  l = map.getAllObjects();
  tile = loadImage("wall.png");
}

void draw()
{
  background(255);
  map.moveAll(2,5);
  for (MapObject mo : l)
      {
          image(tile, (int)(mo.getX()), (int)(mo.getY()));
      }
}