package tile;

import map.TouchRegion;
import map.MapObject;
import map.MapObjectType;
import map.AbstractAnimatedMapObject;
import map.Animation;
import map.AnimationType;
import map.AnimatedMapObject;

public class Hazard extends AbstractAnimatedMapObjectTile
{
    private HazardType hType;
    private boolean sprung = false;
    private boolean playerTrap;
    
    public Hazard(double sx, double sy, HazardType t, Boolean pt)
    {
	disabled = false;
	x = y = 0;
	loadUnsprung();
	sizeX = sx;
	sizeY = sy;
	collidable = false;
	type = MapObjectType.HAZARD;
	hType = t;
	setSpeed(0);
	PlayerTrap = pt;
    }

    private void loadUnsprung()
    {
	String url = "";
        //set url to image of floor
	ArrayList<String> a = new ArrayList<String>();
	a.add(url);
	setAnimation(new Animation(a, AnimationType.UNSPRUNG));
    }

    private void loadSpring()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//Add animation url frames to list a
	setAnimation(new Animation(a, AnimationType.SPRING));	
    }

    private void loadSprung()
    {
	String url = "";
        //set url to image of sprung trap
	ArrayList<String> a = new ArrayList<String>();
	a.add(url);
	setAnimation(new Animation(a, AnimationType.UNSPRUNG));	
    }
    
    public void move()
    {
	handleTouch();
    }

    public void onAnimationEnd(AnimationType t)
    {
	switch(t)
	    {
	    case UNSPRUNG:
		break;
	    case SPRING:
		for (MapObject mo : getTouching())
		    {
			if (mo instanceof AnimatedMapObject)
			    {
				(AnimatedMapObject)mo.enableMovement();
			    }
		    }
		loadSprung();
		break;
	    case SPRUNG:
		break;
	    }
    }

    public void hendleTouch(LinkedList<MapObject> touching)
    {
	if (sprung)
	    {
		return;
	    }
	for (MapObject mo : touching)
	    {
		if (playerTrap)
		    {
			if (mo.getMapObjectType() == MapObjectType.CHARACTER)
			    {
				sprung = true;
				loadSpring();
				handleTrapEffect();
			    }
		    }
		else
		    {
			if (mo.getMapObjectType() == MapObjectType.PLAYER)
			    {
				sprung = true;
				loadSpring();
				handleTrapEffect();
			    }
		    }
	    }
    }

    private void handleTrapEffect(AnimatedMapObject c)
    {
	//(AnimatedMapObject)(mo).disablMmovement();
	//Damage
    }
}
