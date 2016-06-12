import java.util.*;

LinkedList<MapObject> l;
PImage tile;
HashMap<String, PImage> images;
GMap map;
Stack<Tile> st;
Tile old = null;
Set<String> mPressed;


void setup()
{
  fullScreen();
  background(0);
  map = GMap.getInstance();
  l = map.getAllObjects();
  tile = loadImage("wall.png");
  images = new HashMap<String, PImage>();
  st = new Stack<Tile>();
  map.spawnMonster();
  mPressed = new HashSet<String>();
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
      handleControl(p);
      p.move();

    }
    
    else if (mo.getMapObjectType() == MapObjectType.CHARACTER)
    {
     
     Character mn= (Character)(mo);
     mn.loadWalkingAnimation();
     mn.move(); 
    }
    
    String s = mo.getImage();
    if (!images.containsKey(mo.getImage()))
    {
      images.put(s, loadImage(s));
    }
    
    image(images.get(s), (int)(mo.getX()), (int)(mo.getY()));

  }
}

public void keyPressed()
{
   switch(key)
   {
     case 'w':
     mPressed.add("w");
     break;
     case 'a':
     mPressed.add("a");
     break;
     case 's':
     mPressed.add("s");
     break;
     case 'd':
     mPressed.add("d");
     break;
     default:
     break;
   }

         
}
public void keyReleased()
{
       switch(key)
   {
     case 'w':
     mPressed.remove("w");
     break;
     case 'a':
     mPressed.remove("a");
     break;
     case 's':
     mPressed.remove("s");
     break;
     case 'd':
     mPressed.remove("d");
     break;
     default:
     break;
   }
}

public void handleControl(Player p)
{
  double angle = 0;
  boolean left = false;
  boolean right = false;
  boolean up = false;
  boolean down = false;
  if (mPressed.contains("w")) {up =  true;}
  if (mPressed.contains("a")) {left =  true;}
  if (mPressed.contains("s")) {down =  true;}
  if (mPressed.contains("d")) {right =  true;}
  if (left == true && right == true)
  {
   left = false;
   right = false;
  }
  if (up == true && down == true)
  {
    up = false;
    down = false;
  }
  if (up == false && down == false && left == false && right == false)
  {
    p.loadStandAnimation(); 
    p.stopMoving();
    return;
  }
  p.startMoving();
  if (down == true && left == true) {angle = 135;}
  else if (down == true && right == true) {angle = 45;}
  else if (up == true && left == true) {angle = 225;}
  else if (up == true && right == true) {angle = 315;}
  else if (down == true) {angle = 90;}
  else if (up == true) {angle = 270;}
  else if (left == true) {angle = 180;}
  else if (right == true) {angle = 0;}
  double rads = Math.toRadians(angle);
  p.setDirection(rads);
}