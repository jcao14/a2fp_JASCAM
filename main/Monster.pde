/* Player
 * This class is one of the few subtypes of AnimatedMapObject.
 * Draws and animates the player. Also keeps track of player coordinates and other stats.
 */

public class Monster extends AnimatedMapObject {

  PImage reg_sprite;
  PImage front;
  PImage back;
  PImage left;
  PImage right;

  int HP;

  //=======FIELD================



  //=======METHS================
  public Monster(double xcor, double ycor ) {
    super(xcor, ycor, 50, 50, MapObjectType.values()[2]); //sets type to PLAYER based on enum

    reg_sprite = loadImage ("mob_1_down.png");
    front = loadImage("mob_1_down.png");
    back = loadImage("mob_1_up.png");
    left = loadImage("mob_1_left.png");
    right = loadImage("mob_1_right.png");
    reg_sprite.resize(40,40);
    front.resize(40,40);
    back.resize(40,40);
    left.resize(40,40);
    right.resize(40,40);
  
    //Setting the initial velocity of player
    double[] v = new double[2];
    v[0] = 2;
    v[1] = 2;
    //GOTTA GO FAST
    this.setVelocity (v);
    
    HP = 100;
  }
  //draws player based on Pimage player (which is changed by inputs) and the coordiates)
  public void animate() {
    image(reg_sprite, (float)x, (float)y);
  }

  public void handleCollision(MapObject mo) {//tBD
  };
}