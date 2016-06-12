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
    super(xcor, ycor, 50, 50, MapObjectType.values()[1]);
    name = s;
    player = loadImage("skull_front.png");
    front = loadImage("skull_front.png");
    back = loadImage("skull_back.png");
    left =loadImage("skull_left.png");
    right =loadImage("skull_right.png");
     
    double[] v = new double[2];
    v[0] = 5;
    v[0] = 5;
    this.setVelocity (v);
  }

  public void animate() {
    image(player, (float)x, (float)y);
  }

  public void handleCollision(MapObject mo) {
  };
}