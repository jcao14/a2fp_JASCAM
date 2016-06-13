//projectile class
//projectile size: 10x10

import java.util.ArrayList;
import java.util.LinkedList;

public class Projectile extends AbstractAnimatedMapObject {


  //=======field========
  private int damage;
  MapObject origin;      //object that fired bullet, keeps track so u dont hit yourself
  private ProjectileEffect p_effect;
  private int timeRemaining;

  //construct projectile based on (x,y) and velocity
  public Projectile(double x, double y, double s, double a, ProjectileEffect effect, MapObject shooter) {
    moveTo(x, y);
    collidable = true;
    type = MapObjectType.PROJECTILE;
    p_effect = effect;
    sizeX = 10;
    sizeY = 10;
    velocity = new Velocity(s, a);
    disabled = false;
    loadWalkingAnimation();
    origin = shooter;
    timeRemaining = 100;
  }

  //USE THIS CONSTRUCTOR!!!!
  public Projectile(MapObject shooter, double targetX, double targetY, ProjectileEffect effect) {
    this(shooter.getX(), shooter.getY(), 3, Math.atan2(targetY-shooter.getY(), targetX-shooter.getX()), effect, shooter);
    //System.out.println("projectile debug: " + (Math.atan2(targetY, targetX)*180/Math.PI));
  }

  //no spawn animation, starts in walk animation
  //bullet "walk" (travel) animation
  public void loadWalkingAnimation() {

    String url;

    //effect determines bullet image
    switch(p_effect) {
    case BOUNCE:
      url = "bullet (3).png";
    case EXPLODE:
      url = "bullet (1).png";
    case PENETRATE:
      url = "bullet (4).png";
    case NORMAL:
    default:
      url = "bullet (7).png";
      break;
    }

    ArrayList<String> a = new ArrayList<String>();
    a.add(url);

    setAnimation(new Animation(a, AnimationType.WALK));
  }

  //whenn bullet collides!
  public void loadAttackingAnimation() {

    String url;
    ArrayList<String> a = new ArrayList<String>();

    //effect determines bullet image
    switch(p_effect) {
    case BOUNCE:
      url = "bullet (3).png";
      a.add(url);
    case EXPLODE:
      a.add("explosion0.png");
      a.add("explosion1.png");
      a.add("explosion2.png");
      a.add("explosion3.png");
      a.add("explosion4.png");
      a.add("explosion5.png");
    case PENETRATE:
      url = "bullet (4).png";
      a.add(url);
    case NORMAL:
    default:
      url = "bullet (7).png";      
      a.add(url);
      break;
    }

    setAnimation(new Animation(a, AnimationType.ATTACK));
  }

  public void onAnimationEnd(AnimationType t) {
    switch (t) {
    case WALK:
      loadWalkingAnimation();
      break;
    case ATTACK:
      loadAttackingAnimation();
      break;
    case DIE:
      GMap map = GMap.getInstance();
      map.removeObject(this);
      //bullet removed
      break;
    }
  }

  public void destroy() {
    onAnimationEnd( AnimationType.DIE );
  }

  public String getImage() {
    return frames.getFrame();
  }

  //implement abstract classes=============================================
  public void handleTouch(LinkedList<MapObject> touching) {
    for (int i=0; i<touching.size(); i++) {
      MapObject mo = touching.get(i);
      switch(mo.getMapObjectType()) {
      case CHARACTER:
      case PLAYER:
        Character gch = (Character)mo;
        if( mo==origin ) {
          ;
        } else if ( p_effect == ProjectileEffect.PENETRATE ) {
          ((Character)mo).takeDamage( damage );
        } else if ( p_effect == ProjectileEffect.EXPLODE ) {
          ((Character)mo).takeDamage( damage );
          loadAttackingAnimation();
          destroy();
        } else {
          ((Character)mo).takeDamage( damage );
          destroy();
        }
        break;
      case WALL:
        if ( p_effect == ProjectileEffect.BOUNCE ) {
          if ( colliding.contains( ObjectOverlapType.RIGHT ) || colliding.contains( ObjectOverlapType.LEFT ) ) {  
            //BUG!!! Projectile my collide to 
            //wall from UP, but collide bullet RIGHT
            //and thus call this code...
            velocity.reflectX();
          } else {
            velocity.reflectY();
          }
        } else if ( p_effect == ProjectileEffect.PENETRATE ) {
          ;
        } else {  //no explosions on walls... sorry
          destroy();
        }
        break;
      case FLOOR:
      case HAZARD:
      case PROJECTILE:
      case SPECIAL:
      case ITEM:
        break;
      }
    }
  }

  //moves bullet, also adjust bullet life timer and destroys it if necessary
  public void move() {
    moveTo( getX()+velocity.getXVelocity(), getY()+velocity.getYVelocity() );
    timeRemaining--;
    if ( timeRemaining<1 ) {
      destroy();
    }
  }

}