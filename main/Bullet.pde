

public class Bullet extends AnimatedMapObject {

  public static final int BULLET_TIME_LIMIT = 1000;

  public String bullet_type;
  PImage bullet;
  int int_counter;
  int damage;

  public Bullet (double xcor, double ycor, double mouse_xcor, double mouse_ycor) {
    super (xcor, ycor, 10, 10, MapObjectType.values()[5]);

    //preloads tile images
    bullet = loadImage("bullet.png");
    damage = 1;
    double[] v = new double[2];
    v[0] = 3;
    v[1] = 3;
    //GOTTA GO FAST
    this.setVelocity (v);
    double slope = (mouse_ycor - ycor) / (mouse_xcor - xcor);
    double angle = Math.atan(slope);
    if ( (mouse_xcor < xcor) && (mouse_ycor < ycor))  {
      angle = Math.PI - angle;
    } 
    else if ((mouse_xcor < xcor) && (mouse_ycor > ycor)){
      angle = Math.PI + angle;
    }
    this.setDirection (angle);
  }

  public void animate() {
    image(bullet, (float)x, (float)y);
  }

  //We're gonna need to for collisions.
  public String getType() {
    return bullet_type;
  }

  public int getCounter() {
    return int_counter;
  }

  public void resetCounter() {
    int_counter =0;
  }

  public void increase() {
    int_counter ++;
  }

  public void handleCollision(MapObject mo) {//IDK
    ; //handled in main :(
  }
}