import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
public class Player extends Character
{

    private int maxEXP;
    private int exp;
    private int level;
    private Inventory inv;
    //private SkillTree sTree;
    private HashMap<StatEffect, Integer> effects;
    private boolean walking;
    private boolean standing;
    private int statTimer = 0;

    public Player()
    {
	maxHp = 100;
	maxMp = 20;
	maxEXP = 100;
	hp = 100;
	mp = 20;
	exp = 0;
	level = 1;
	inv = new Inventory();
	effects = new HashMap<StatEffect, Integer>();
	baseSpeed = 1;
	baseAttack = 10;
	baseDefense = 10;
	walking = false;
	standing = true;
	type = MapObjectType.PLAYER;
	collidable = true;
	disabled = false;
	velocity = new Velocity(0,0);
	colliding = new HashSet<ObjectOverlapType>();
	sizeX = 32;
	sizeY = 32;
	updateStats();
	loadSpawnAnimation();
    }   

    public void kill()
    {
	loadKillAnimation();
    }

    public synchronized void loadSpawnAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
	for (int i = 0; i < 50; i++) {a.add("slime (1).png");}
	disableMovement();
	setAnimation(new Animation(a, AnimationType.SPAWN));
    }

    public synchronized void loadWalkingAnimation()
    {
	standing = false;
	if (!walking && !disabled)
	    {
		walking = true;
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		a.add("slime (1).png");
		a.add("slime (2).png");
		setAnimation(new Animation(a, AnimationType.WALK));
	    }
    }

    public synchronized void loadStandAnimation()
    {
	walking = false;
	if(!standing && !disabled)
	    {
		standing = true;
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		a.add("slime (1).png");
		setAnimation(new Animation(a, AnimationType.STAND));
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
		loadStandAnimation();
		break;
	    case WALK:
		walking = false;
		loadWalkingAnimation();
		break;
	    case STAND:
		standing = false;
		loadStandAnimation();
		break;
	    case DIE:
	        GMap map = GMap.getInstance();
		map.removeObject(this);
		//game over/respawn code
		break;
	    }
    }

    public synchronized void move()
    {
	LinkedList<MapObject> touching = getTouching();
	handleTouch(touching);
	statTimer += 1;
	if (statTimer >= 5)
	    {
		statTimer = 0;
		updateStats();
	    }
	if (!disabled)
	    {
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
		map.moveAll(-vx, -vy);
	    }
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
      //GMap a = GMap.getInstance();
      //a.removeObject(mo);
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
			MapItem mi = (MapItem)(mo);
			int id = mi.getID();
			if (inv.add(id))
			    {
				GMap map = GMap.getInstance();
				map.removeObject(mo);
			    }
			break;
		    }
	    }
    }

    private void updateStats()
    {
	attack = baseAttack;
	speed = baseSpeed;
	defense = baseDefense;
	int[] eq = inv.getEquipped();
	ItemDataProvider idp = ItemDataProvider.getInstance();
	for (int k = 0; k < eq.length; k++)
	    {
		ItemData i = idp.getData(k);
		if (i != null)
		    {
		attack += i.getAttack();
		defense += i.getDefense();
		speed += i.getSpeed();
		    }
	    }
	//handle effects
  velocity.updateSpeed(speed);
    }
}