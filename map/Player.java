
public class Player extends AbstractAnimatedMapObject implements Character
{
    private int maxHp;
    private int maxMp;
    private int maxEXP;
    private int hp;
    private int mp;
    private int exp;
    private int expNeeded;
    private int level;
    private Inventory inv;
    //private SkillTree sTree;
    private HashMap<StatEffect, Integer> effects;
    private int baseSpeed;
    private int baseAttack;
    private int baseDefense;
    private int speed;
    private int attack;
    private int defense;
    private boolean walking;
    private boolean standing;
    private int statTimer = 0;

    public void takeDamage(int a)
    {
	if (defense > a)
	    {
		return;
	    }
	lowerHP(a-defense);
    }

    public int lowerHP(int d)
    {
	int t = hp;
	if (hp < d)
	    {
		hp = 0;
		kill();
	    }
	else
	    {
		hp -= d;
	    }
	return t;
    }
    
    public int raiseHP(int d)
    {
	int t = hp;
	hp += d;
	if (hp > maxHp)
	    {
		hp = maxHp;
	    }
	return t;
    }
    
    public int setHP(int h)
    {
	int t = hp;
	if (h > maxHp || h < 0)
	    {
		return t;
	    }
	hp = h;
	return t;
    }
    
    public int raiseMP(int d)
    {
	int t = mp;
	mp += d;
	if (mp > maxMp)
	    {
		mp = maxMp;
	    }
	return t;
    }
    
    public boolean lowerMP(int d)
    {
	if (mp - d < 0)
	    {
		return false;
	    }
	else
	    {
		mp -= d;
	    }
	return true;
    }
    
    public int setMP(int m);
    {
	int t = mp;
	if (m > maxMp || m < 0)
	    {
		return t;
	    }
	mp = m;
	return t;
    }
    
    public int getHP()
    {
	return hp;
    }
    
    public int getMP()
    {
	return mp;
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
		setAnimation(new Animation(a, AnimationType.STAND));
	    }
    }

    public synchronized void loadKillAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
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
		loadStandingAnimation();
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
		int vx = velocity.getXVelocity();
		int vy = velocity.getYVelocity();
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
			break;
		    case FLOOR:
			break;
		    case HAZARD:
			mo = (Hazard)(mo);
			mo.handleTouch(mo.getTouching());
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
			int id = (MapItem(mo)).getID();
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
		attack += i.getAttack();
		defense += i.getDefense();
		speed += i.getSpeed();
	    }
    }
}
