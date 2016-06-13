//projectile class
//projectile size: 10x10

import java.util.ArrayList;

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
	timeRemaining = 20;
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

    //effect determines bullet image
    switch(p_effect) {
    case BOUNCE:
      url = "bullet (3).png";
    case EXPLODE:
      url = //explosion sprite!!
    case PENETRATE:
      url = "bullet (4).png";
    case NORMAL:
    default:
      url = "bullet (7).png";
      break;
    }
	
    ArrayList<String> a = new ArrayList<String>();
    a.add(url);
    
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
    frames.getFrame();
  }

  //implement abstract classes=============================================
  public void handleTouch(LinkedList<MapObject> touching)
  {
    for (int i=0; i<touching.size(); i++)
    {
	  MapObject mo = touching.get(i);
      switch(mo.getMapObjectType())
      {
	  case CHARACTER:
      case PLAYER:
		Character gch = (Character)mo;
		if( p_effect == ProjectileEffect.PENETRATE ) {
			mo.takeDamage( damage );
		}
		else if( P_effect == ProjectileEffect.EXPLODE ) {
			mo.takeDamage( damage );
			loadAttackingAnimation();
			destroy();
		}
		else {
			mo.takeDamage( damage );
			destroy();
		}
        break;
      case WALL:
		if( p_effect == ProjectileEffect.BOUNCE ) {
			if( colliding.contains( ObjectOverlayType.RIGHT ) || colliding.contains( ObjectOverlayType.LEFT ) ) {  
				//BUG!!! Projectile my collide to 
				//wall from UP, but collide bullet RIGHT
				//and thus call this code...
				reflectX();
			}
			else {
				reflectY();
			}
		}
		else if( p_effect == ProjectileEffect.PENETRATE ) {
			;
		}
		else {  //no explosions on walls... sorry
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
	if( timeRemaining<1 ) {
		destroy();
	}
  }
  
}

