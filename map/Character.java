import java.util.LinkedList;
public abstract class Character extends AbstractAnimatedMapObject
{
    protected int maxHp;
    protected int maxMp;
    protected int hp;
    protected int mp;
    protected int baseSpeed;
    protected int baseAttack;
    protected int baseDefense;
    protected int speed;
    protected int attack;
    protected int defense;

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
    
    public int setMP(int m)
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
    
    public abstract void kill();
    public abstract void loadWalkingAnimation();
    public abstract void loadSpawnAnimation();
    public abstract void loadKillAnimation();
    public abstract void onAnimationEnd(AnimationType t);
    public abstract void move();
    public abstract void handleTouch(LinkedList<MapObject> touching);
}
