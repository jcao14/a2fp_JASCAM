import java.util.*;

LinkedList<MapObject> l;
PImage tile;
HashMap<String, PImage> images;
GMap map;
Stack<Tile> st;
Tile old = null;
Set<String> mPressed;
Player gplayer;
int player_fire_cooldown;
ItemGUI itembox;

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

  for(int i=0; i<l.size(); i++) {
    if( l.get(i) instanceof Player ) {
      gplayer = (Player)l.get(i);
      break;
    }
  }
  player_fire_cooldown = 0; //when player fires subtract 10, only fire when positive, increment if neg

  itembox = new ItemGUI(800, 500, 150, 300, gplayer.getInventory());
  
}

void draw()
{
  background(0);
  
  //for player fire:
  if( player_fire_cooldown<0 ) { player_fire_cooldown++; }
  if( mousePressed ) {
    if( player_fire_cooldown>=0 ) {
      map.addObject( new Projectile( gplayer, mouseX, mouseY, ProjectileEffect.NORMAL, gplayer.getAtk() ), (int)gplayer.getX(), (int)gplayer.getY() ); 
      player_fire_cooldown = -5;
    }
  }
  
  for(int j=0; j<l.size(); j++)
  {
    MapObject mo = l.get(j);
    PImage i = null;
    LinkedList touching;
    
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
    
    else if( mo.getMapObjectType() == MapObjectType.PROJECTILE) {
      Projectile proj = (Projectile)mo;
      proj.move();
    }
    
    if( mo instanceof AbstractAnimatedMapObject ) {
      AbstractAnimatedMapObject aamo = (AbstractAnimatedMapObject)mo;
      touching = aamo.getTouching();
      if( touching!=null ) aamo.handleTouch( touching);
    }
    
    //draw all images
    String s = mo.getImage();
    if (!images.containsKey(mo.getImage()))
    {
      PImage imge = loadImage(s);
      images.put(s, imge);
    }
    
    image(images.get(s), (int)(mo.getX()), (int)(mo.getY()));

  }
  
  //itembox - draw at end to override
  //itembox.display();
  //itembox.update( gplayer.getInventory );
  
  
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

//player fire code!!
//will also add inventory gui interaction...
void mousePressed() {

}