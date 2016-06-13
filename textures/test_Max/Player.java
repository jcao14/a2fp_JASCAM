import java.util.Stack;
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
	baseSpeed = 5;
	baseAttack = 10;
	baseDefense = 8;
	walking = false;
	standing = true;
	type = MapObjectType.PLAYER;
	collidable = true;
	disabled = false;
	velocity = new Velocity(0,0);
	colliding = new HashSet<ObjectOverlapType>();
	sizeX = 32;
	sizeY = 32;
  speed = 0;
	updateStats();
	loadSpawnAnimation();
    }   
    
    public void startMoving()
    {
     speed = baseSpeed; 
     velocity.updateSpeed(speed);
    }
    
    public void stopMoving()
    {
     speed = 0; 
     velocity.updateSpeed(speed);
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
	for ( int i = 14; i >=0; i--)
      {
    String s = "skull_dead";
    a.add(s + i + ".png");
    a.add(s + i + ".png");        
      }
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
		for ( int i = 0; i <=3; i++)
      {
    String s = "skull_walk";
    a.add(s + i + ".png");
    a.add(s + i + ".png");        
      }
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
		a.add("skull0.png");
		setAnimation(new Animation(a, AnimationType.STAND));
	    }
    }

    public synchronized void loadKillAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	for ( int i = 0; i <=14; i++)
      {
    String s = "skull_dead";
    a.add(s + i + ".png");
    a.add(s + i + ".png");        
      }
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
	updateStats();
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
        interactItem(id);
			    }
			break;
		    }
	    }
    }
    
    public void registerEffects(HashMap<StatEffect, Integer> e)
    {
      System.out.println("effect applied");
      for (StatEffect sef : e.keySet())
      {
        effects.put(sef, e.get(sef));
      }
    }
    
    public Inventory getInventory()
    {
     return inv; 
    }

    public void interactItem(int id)
    {
      ItemDataProvider idp = ItemDataProvider.getInstance();
      ItemData data = idp.getData(id);
      System.out.println("using item: " + data.getN());
      if (data.getType() == ItemDataType.EQUIP)
      {
        inv.equipItem(id);
      } else if (data.getType() == ItemDataType.USE)
      {
        inv.remove(id);
        registerEffects(data.getEffects());
      }
    }

    private synchronized void updateStats()
    {
	attack = baseAttack;
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
    if (speed != 0)
		{speed += i.getSpeed();}
		    }
	    }
	//handle effects
  LinkedList<StatEffect> toRemove = new LinkedList<StatEffect>();
  for (StatEffect sef : effects.keySet())
  {
    int duration = effects.get(sef);
    duration -= 1;
    if (duration <= 0)
    {
      System.out.println("effect died");
      toRemove.add(sef);
    } else
    {
     effects.put(sef, duration); 
    }
    
    //if (duration%5 == 0)
    //{
      switch(sef)
      {
       case BURN1:
       lowerHP(1);
       break;
       case BURN2:
       lowerHP(2);
       break;
       case BURN3:
       lowerHP(3);
       break;
       case BURN4:
       lowerHP(4);
       break;
       case BURN5:
       lowerHP(5);
       break;
       case HEAL1:
       raiseHP(1);
       break;
       case HEAL2:
       raiseHP(3);
       break;
       case HEAL3:
       raiseHP(4);
       break;
       case HEAL4:
       raiseHP(5);
       break;
       case HEAL5:
       raiseHP(8);
       break;
       case MPHEAL1:
       raiseMP(1);
       break;
       case MPHEAL2:
       raiseMP(2);
       break;
       case MPHEAL3:
       raiseMP(3);
       break;
       case MPHEAL4:
       raiseMP(4);
       break;
       case MPHEAL5:
       raiseMP(5);
       break;
       case BLEED1:
       lowerHP(1);
       break;
       case BLEED2:
       lowerHP(2);
       break;
       case BLEED3:
       lowerHP(3);
       break;
       case BLEED4:
       lowerHP(4);
       break;
       case BLEED5:
       lowerHP(5);
       break;
       case STUN1:
       disableMovement();
       break;
       case SLOW1:
       speed = speed / 2;
       velocity.updateSpeed(speed);
       break;
       case POISON1:
       lowerHP(1);
       break;
       case POISON2:
       lowerHP(2);
       break;
       case POISON3:
       lowerHP(3);
       break;
       case POISON4:
       lowerHP(4);
       break;
       case POISON5:
       lowerHP(5);
       break;
       case SUPERSPEED:
       speed *= 2;
       break;
      }      
    //}
  }
    if (toRemove.size() != 0)
    {
      for (StatEffect r : toRemove)
      {
        effects.remove(r);
      }
    }
  velocity.updateSpeed(speed);
    }
}