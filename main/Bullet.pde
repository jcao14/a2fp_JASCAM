public class Bullet extends AnimatedMapObject {

  String bullet_type;

  PImage bullet;

  int int_counter;

  public Bullet (double xcor, double ycor) {
    super (xcor, ycor, 10, 10, MapObjectType.values()[5]);

    //preloads tile images
    bullet = loadImage("bullet.png");
    double[] v = new double[2];
    v[0] = 3;
    v[1] = 3;
    //GOTTA GO FAST
    this.setVelocity (v);
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


  public void handleCollision(MapObject mo) {//IDK MAN
  }
}