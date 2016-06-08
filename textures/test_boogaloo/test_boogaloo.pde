
import java.util.*;
GMap map;
LinkedList<MapObject> l;



public static final String TRAP_1 = "Z";
public static final String TRAP_2 = "X";
public static final String TRAP_3 = "C";
public static final String TRAP_4 = "V";

public static final String HERO = "H";
public static final String enemy_1 = "1";
public static final String enemy_2 = "2";

PImage wall;
PImage floor;

void setup() {
  size (550, 550);
  map = GMap.getInstance("map.txt");
  l = map.getAllObjects();
  getTextures();
}



public void getTextures()
{
  wall = loadImage("wall.png");
  floor = loadImage("floor.png");
}