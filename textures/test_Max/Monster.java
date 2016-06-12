import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
public class Monster extends Enemy
{
protected Stack<Tile> pPath;
    public Monster(LinkedList<Tile> p)
    {
	exp = 1;
	aggro = false;
	aggroRange = 10;
	patrol = p;
	pather = new Pathfinder(6);
  pPather = new Pathfinder(aggroRange);
	path = new Stack<Tile>();
pPath = new Stack<Tile>();
	maxHp = 100;
	maxMp = 20;
 	hp = 100;
	mp = 20;
	baseSpeed = 8;
	baseAttack = 10;
	baseDefense = 10;
	speed = baseSpeed/2;
	attack = baseAttack;
	defense = baseDefense;
	sizeX = 32;
	sizeY = 32;
	type = MapObjectType.CHARACTER;
	collidable = true;
	disabled = false;
	colliding = new HashSet<ObjectOverlapType>();
	velocity = new Velocity(0,0);
	velocity.updateSpeed(speed);
 	loadSpawnAnimation();
    }
    
    public synchronized void move()
    {
	LinkedList<MapObject> touching = getTouching();
	handleTouch(touching);
	if (!disabled)
	    {
		//System.out.println(0);
		try
		    {
			if (path.empty())
			    {
				//System.out.println(1);
				path = pather.getPath(this.getCurrentTile(), patrol.get(0));
				patrol.addLast(patrol.remove(0));
				path.pop();
			    }
			Tile ti = this.getCurrentTile();
			if (old == null || ti.getMatrixX() != old.getMatrixX() || ti.getMatrixY() != old.getMatrixY() )
			    {
				//System.out.println(2);
				Tile ti1 = path.pop();
				this.setDirectionTowards(ti1);
				old = ti;
				//System.out.println("popped");
				//System.out.println(ti1.getMatrixX() + ", " + ti1.getMatrixY());
			    }
			//handleAggro();
		    } catch (Exception e){}
		double vx = velocity.getXVelocity();
		double vy = velocity.getYVelocity();
		if (colliding.contains(ObjectOverlapType.RIGHT) && vx > 0)
		    {
			vx = 0;
		    }
		if (colliding.contains(ObjectOverlapType.LEFT) && vx < 0)
		    {
			vx = 0;
		    }
		if (colliding.contains(ObjectOverlapType.UP) && vy > 0)
		    {
			vy = 0;
		    }
		if (colliding.contains(ObjectOverlapType.DOWN) && vy < 0)
		    {
			vy = 0;
		    }
		GMap map = GMap.getInstance();
		moveTo(x+vx,x+vy);
	    }	
    }

    public void handleAggro()
    {
	GMap map= GMap.getInstance();
	if (aggro)
	    {
  System.out.println(5);
	        path = pPather.getPath(this.getCurrentTile(), map.getPlayer().getCurrentTile());
		path.pop();
		//other aggro behavior goes here. eg. shooting at player, using abilities, etc.
	    }
	else
	    {
		System.out.println(4);
		pPath = pPather.getPath(this.getCurrentTile(), map.getPlayer().getCurrentTile());
		if (!pPath.empty())
		    {
			speed = baseSpeed;
			velocity.updateSpeed(speed);
			aggro = true;
			path = pPath;
			path.pop();
		    }
	    }
    }

    public synchronized void loadSpawnAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
	for (int i = 0; i < 5; i++) {a.add("slime (1).png");}
	disableMovement();
	setAnimation(new Animation(a, AnimationType.SPAWN));
    }

    public synchronized void loadWalkingAnimation()
    {
	if (!disabled)
	    {
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		a.add("slime (1).png");
		a.add("slime (2).png");
		setAnimation(new Animation(a, AnimationType.WALK));
	    }
    }

    public synchronized void loadAttackAnimation()
    {
	if(!disabled)
	    {
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		a.add("slime (1).png");
		setAnimation(new Animation(a, AnimationType.ATTACK));
	    }
    }

    public synchronized void loadKillAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
	a.add("slime (1).png");
	disableMovement();
	setAnimation(new Animation(a, AnimationType.DIE));
    }

    public void onAnimationEnd(AnimationType t)
    {
	switch (t)
	    {
	    case SPAWN:
		enableMovement();
		loadWalkingAnimation();
		break;
	    case WALK:
		loadWalkingAnimation();
		break;
	    case ATTACK:
		loadWalkingAnimation();
		break;
	    case DIE:
	        GMap map = GMap.getInstance();
		map.removeObject(this);
		break;
	    }
    }

    public void kill()
    {
	loadKillAnimation();
	handleItemDrop();
	//give player exp too
    }

    public void handleTouch(LinkedList<MapObject> touching)
    {
	for (MapObject mo : touching)
	    {
		switch(mo.getMapObjectType())
		    {
		    case PLAYER:
			//do dmg to player
			break;
		    case WALL:
			break;
		    case FLOOR:
			break;
		    case HAZARD:
			Hazard h = (Hazard)(mo);
			h.handleTouch(h.getTouching());
			break;
		    case PROJECTILE:
			//projectile collision handled in projectile code
			break;
		    case CHARACTER:
			break;
		    case SPECIAL:
			// do things
			break;
		    case ITEM:
			break;
		    }
	    }
    }

    public void handleItemDrop()
    {
	//spawn MapItems
    }
    
}