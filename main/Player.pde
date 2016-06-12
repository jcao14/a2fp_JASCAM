/* Player
 * This class is one of the few subtypes of AnimatedMapObject.
 * Draws and animates the player. Also keeps track of player coordinates and other stats.
 */

public class Player extends AnimatedMapObject {

  String name;

  PImage player;
  PImage front;
  PImage back;
  PImage left;
  PImage right;


  //=======FIELD================



  //=======METHS================
  public Player(String s, double xcor, double ycor ) {
    super(xcor, ycor, 50, 50, MapObjectType.values()[1]); //sets type to PLAYER based on enum
    name = s;
    /*
    player = Loader.get("skull_front.png");
    front = Loader.get("skull_front.png");
    back = Loader.get("skull_back.png");
    left = Loader.get("skull_left.png");
    right = Loader.get("skull_right.png");
    */ //--I'll fix this later
     //you can resize player size if needed
     
    //Setting the initial velocity of player
    double[] v = new double[2];
    v[0] = 10;
    v[1] = 10;
    //GOTTA GO FAST
    this.setVelocity (v);
  }
  //draws player based on Pimage player (which is changed by inputs) and the coordiates)
  public void animate() {
    image(player, (float)x, (float)y);
  }

  public void handleCollision(MapObject mo) {//tBD
  };
}