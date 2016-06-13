//projectile class
//projectile size: 10x10

import java.util.ArrayList;

public class Projectile extends AbstractAnimatedMapObject {


	//=======field========
	private int damage;
	MapObject origin;
	private ProjectileEffect p_effect;
	
	//construct projectile based on (x,y) and velocity
	public Projectile(double x, double y, double s, double a, ProjectileEffect effect, MapObject origin) {
		moveTo(x,y);
		collidable = true;
		type = MapObjectType.PROJECTILE;
		p_effect = effect;
		sizeX = 10;
		sizeY = 10;
		velocity = new Velocity(s, a);
		disabled = false;
		loadImage();
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
		//add animation frames to list
		a.add(url);
		setAnimation(new Animation(a, AnimationType.WALK));
    }

    public void loadKillAnimation(){
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		a.add(url);
		disableMovement();
		setAnimation(new Animation(a, AnimationType.DIE));
    }

    public void onAnimationEnd(AnimationType t){
	switch (t){
	    case WALK:
			loadWalkingAnimation();
			break;
		case DIE:
			GMap map = GMap.getInstance();
			map.removeObject(this);
			//bullet removed
			break;
		}
    }

	public String getImage() {
		return( "bullet (7).png" );
	}
	
	    public void handleTouch(LinkedList<MapObject> touching)
    {
	for (MapObject mo : touching)
	    {
		switch(mo.getMapObjectType())
		    {
		    case PLAYER:
			break;
		    case WALL:
			break;

		    case CHARACTER:
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
	
}