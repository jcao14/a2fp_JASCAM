public class ItemData
{
    private int id;
    private int speed;
    private int attack;
    private int defense;
    private String name;
    private HashMap<StatEffect, Integer> effects;
    private LinkedList<ProjectileEffect> projectileEffects;
    private ItemDataType type;
    private String image;

    public ItemData(int i, String n, ItemDataType t, int s, int a, int d, HashMap<StatEffect, Integer> e, LinkedList<ProjectileEffect> p, String im)
    {
	xsid = i;
	type = t;
	speed = s;
	defense = d;
	attack = a;
	name = n;
	effects = e;
	projectileEffects = p;
	image = im;
    }

    public int getId() {return id;}
    public int getSpeed() {return speed;}
    public int getAttack() {return attack;}
    public int getDefense(){return defense;}
    public String getName() {return name;}
    public HashMap<StatEffect,Integer> getEffects() {return effects;}
    public LinkedList<ProjectileEffect> getProjectileEffects(){return projectileEffects;}
    public ItemDataType getType(){return type;}
    public String getImage() {return image;}

    public boolean sameAs(ItemData other)
    {
	if (this.getID() == other.getID())
	    {
		return true;
	    }
	else
	    {
		return false;
	    }
    }
}