
public class Player extends AbstractAnimatedMapObject implements Character
{
    private int maxHp;
    private int maxMp;
    private int maxEXP;
    private int hp;
    private int mp;
    private int exp;
    private int level;
    //private Inventory inv;
    //private SkillTree sTree;
    //private LinkedList<StatEffect> effects;
    private int maxSpeed;
    private int speed;
    private boolean walking;
    private boolean standing;

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
	
    }

    public void loadSpawnAnimation()
    {
	String url = "";
	ArrayList<String> a = new ArrayList<String>();
	//add animation frames to list
	disableMovement();
	setAnimation(new Animation(a, AnimationType.SPAWN));
    }

    public void loadWalkingAnimation()
    {
	if (!walking)
	    {
		walking = true;
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		setAnimation(new Animation(a, AnimationType.WALK));
	    }
    }

    public void loadStandAnimation()
    {
	if(!standing)
	    {
		standing = true;
		String url = "";
		ArrayList<String> a = new ArrayList<String>();
		//add animation frames to list
		setAnimation(new Animation(a, AnimationType.STAND));
	    }
    }

    public void move()
    {
	LinkedList<MapObject> touching = getTouching();
	handleTouch(touching);
	vx = velocity.getXVelocity();
	vy = velocity.getYVelocity();
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
	x += vx;
	y += vy;
    }

    public void handleTouch(LinkedList<MapObject> touching)
    {
	for (MapObject mo : touching)
	    {
		switch(mo.getMapObjectType())
		    {
		    case WALL:
			break;
		    case FLOOR:
			break;
		    case HAZARD:
			
		    }
	    }
    }
}
