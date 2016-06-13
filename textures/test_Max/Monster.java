import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
public class Monster extends Enemy
{
protected Stack<Tile> pPath;
    protected boolean attacking;
    public Monster(LinkedList<Tile> p)
    {
	exp = 1;
	aggro = false;
	aggroRange = 15;
	patrol = p;
	pather = new Pathfinder(11);
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
	sizeX = 42;
	sizeY = 42;
	type = MapObjectType.CHARACTER;
	collidable = true;
	disabled = false;
	colliding = new HashSet<ObjectOverlapType>();
	velocity = new Velocity(0,0);
	velocity.updateSpeed(speed);
 	loadSpawnAnimation();
	attacking = false;
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
				//Pathfinder.printStack(path);
				//path = pather.getPath(this.getCurrentTile(), patrol.get(0));        
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
				System.out.println(ti1.getMatrixX() + ", " + ti1.getMatrixY());
			    }
		    } catch (Exception e){}
		handleAggro();
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
		moveTo(x+vx,y+vy);
	    }	
    }

    public void handleAggro()
    {
	GMap map= GMap.getInstance();
	if (aggro)
	    {
		//other aggro behavior goes here. eg. shooting at player, using abilities, etc.
		this.setDirectionTowards(map.getPlayer().getCurrentTile());
	    }
	else
	    {
		double dist = getDistance(map.getPlayer());
		if (dist <= aggroRange)
		    {
			aggro = true;
			path = null;
		    }
	    }
    }

    public void loadSpawnAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
	for ( int i = 18; i >= 0; i--)
	    {
		String s = "melt_slime (";
		a.add(s + i + ").png");
		a.add(s + i + ").png");				
	    }
	disableMovement();
	setAnimation(new Animation(a, AnimationType.SPAWN));
    }

    public void loadWalkingAnimation()
    {
      //System.out.println("walking");
	if (!disabled)
	    {
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		a.add("slime (2).png");
		a.add("slime (2).png");
    a.add("slime (3).png");
    a.add("slime (4).png");
		setAnimation(new Animation(a, AnimationType.WALK));
	    }
    }

    public void loadAttackAnimation()
    {
      //System.out.println("attacking");
	if(!disabled)
	    {
    disableMovement();
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		for (int i = 0; i <= 12; i++)
{
    String s = "jump_slime (";
    a.add(s + i + ").png");
    a.add(s + i + ").png");      
}
		setAnimation(new Animation(a, AnimationType.ATTACK));
	    }
    }

    public void loadKillAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
	for ( int i = 0; i <= 18; i++)
	    {
		String s = "melt_slime (";
		a.add(s + i + ").png");
		a.add(s + i + ").png");				
	    }
	disableMovement();
	setAnimation(new Animation(a, AnimationType.DIE));
    }

    public synchronized void onAnimationEnd(AnimationType t)
    {
	switch (t)
	    {
	    case SPAWN:
		enableMovement();
    //System.out.println("spawned");
		loadWalkingAnimation();
		break;
	    case WALK:
		loadWalkingAnimation();
		break;
	    case ATTACK:
    enableMovement();
		attacking = false;
    //System.out.println("attecked");
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

    public synchronized void handleTouch(LinkedList<MapObject> touching)
    {
	for (MapObject mo : touching)
	    {
		switch(mo.getMapObjectType())
		    {
		    case PLAYER:
			//System.out.println("touch");
			if (!attacking)
			    {
        loadAttackAnimation();
				Player p = (Player)(mo);
				p.takeDamage(attack);
				attacking = true;
				//System.out.println("hit");
			    }
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