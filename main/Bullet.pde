public class Bullet extends AnimatedMapObject {

  String bullet_type;

  PImage bullet;



  public Bullet () {
    super (0, 0, 10, 10, MapObjectType.values()[5]);

    //preloads tile images
    bullet = loadImage("bullet.png");
    double[] v = new double[2];
    v[0] = 1;
    v[1] = 1;
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


  public void handleCollision(MapObject mo) {//IDK MAN
  }
}