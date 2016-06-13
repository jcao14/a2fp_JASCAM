import java.util.LinkedList;
import java.util.HashMap;
public class ItemDataProvider
{
    private static ItemDataProvider instance = null;
    public static ItemDataProvider getInstance()
    {
	if (instance == null)
	    {
		instance = new ItemDataProvider();
	    }
	return instance;
    }

    private ItemDataProvider()
    {
	data = new HashMap<Integer, ItemData>();
	initAll();
    }

    private HashMap<Integer, ItemData> data;
    //id, name, type, speed, attack, defense, effects, peffects, image, slot
    //Armor, Head, Feet, Acc1, Wep, Mod1

    private void initAll()
    {
	//EXAMPLE ITEM 0
	HashMap<StatEffect, Integer> e = new HashMap<StatEffect, Integer>();
	LinkedList<ProjectileEffect> p = null;
	e.put(StatEffect.HEAL2,300); //lvl 2 heal for 5 seconds
	e.put(StatEffect.SLOW1,300); //lvl 1 slow for 5 seconds
	data.put(0, new ItemData(0, "Example Item", ItemDataType.USE, 0, 0, 0, e, p, "0.png", -1));

  //1 - HEALING POTION 1
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL1, 300);
  data.put(1, new ItemData(1, "Healing Potion 1", ItemDataType.USE, 0, 0, 0, e, p, "hp1.png", -1));
  
  //2 - HEALING POTION 2
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL1, 400);
  data.put(2, new ItemData(2, "Healing Potion 2", ItemDataType.USE, 0, 0, 0, e, p, "hp1.png", -1));

  //3 - HEALING POTION 3
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL2, 400);
  data.put(3, new ItemData(3, "Healing Potion 3", ItemDataType.USE, 0, 0, 0, e, p, "hp1.png", -1));
  
  //4 - HEALING POTION 4
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL3, 400);
  data.put(4, new ItemData(4, "Healing Potion 4", ItemDataType.USE, 0, 0, 0, e, p, "hp1.png", -1));
  
  //5 - HEALING POTION 5
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL4, 400);
  data.put(5, new ItemData(5, "Healing Potion 5", ItemDataType.USE, 0, 0, 0, e, p, "hp1.png", -1));
  
  //6 - Super Healing Potion
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL5, 100);
  data.put(6, new ItemData(6, "Super Healing Potion", ItemDataType.USE, 0, 0, 0, e, p, "hp2.png", -1));
  
  //7 - Ultra Healing Potion
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL5, 200);
  data.put(7, new ItemData(7, "Ultra Healing Potion", ItemDataType.USE, 0, 0, 0, e, p, "hp2.png", -1));
  
  //8 - Ultimate Healing Potion
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.HEAL5, 300);
  data.put(8, new ItemData(8, "Ultimate Healing Potion", ItemDataType.USE, 0, 0, 0, e, p, "hp2.png", -1));
  
  //9 - Elixir 1
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL1, 300);
  data.put(9, new ItemData(9, "Elixir 1", ItemDataType.USE, 0, 0, 0, e, p, "mp1.png", -1));
  
  //10 - Elixir 2
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL2, 300);
  data.put(10, new ItemData(10, "Elixir 2", ItemDataType.USE, 0, 0, 0, e, p, "mp1.png", -1));
  
  //11 - Elixir 3
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL3, 300);
  data.put(11, new ItemData(11, "Elixir 3", ItemDataType.USE, 0, 0, 0, e, p, "mp1.png", -1));
  
  //12 - Elixir 4
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL4, 300);
  data.put(12, new ItemData(12, "Elixir 4", ItemDataType.USE, 0, 0, 0, e, p, "mp1.png", -1));
  
  //13 - Elixir 5
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL5, 300);
  data.put(13, new ItemData(13, "Elixir 5", ItemDataType.USE, 0, 0, 0, e, p, "mp1.png", -1));
  
  //14 - Super Elixir
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL5, 600);
  data.put(14, new ItemData(14, "Super Elixir", ItemDataType.USE, 0, 0, 0, e, p, "mp2.png", -1));
  
  //15 - Ultra Elixir
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL5, 900);
  data.put(15, new ItemData(15, "Ultra Elixir", ItemDataType.USE, 0, 0, 0, e, p, "mp2.png", -1));
  
  //16 - Ultimate Elixir
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.MPHEAL5, 900);
  data.put(16, new ItemData(16, "Ultimate Elixir", ItemDataType.USE, 0, 0, 0, e, p, "mp2.png", -1));
  
  //17 - Superspeed Potion
  e = new HashMap<StatEffect, Integer>();
  p = null;
  e.put(StatEffect.SUPERSPEED, 500);
  data.put(17, new ItemData(17, "Superspeed Potion", ItemDataType.USE, 0, 0, 0, e, p, "ss.png", -1));
  
  //18 - Pea Shooter
  e = null;
  p = new LinkedList<ProjectileEffect>();
  data.put(18, new ItemData(18, "Pea Shooter", ItemDataType.EQUIP, 0, 0, 0, e, p, "peashooter.png", 4));  

  //19 - Bouncy Pea Shooter
  e = null;
  p = new LinkedList<ProjectileEffect>();
  p.add(ProjectileEffect.BOUNCE);
  data.put(19, new ItemData(19, "Bouncy Pea Shooter", ItemDataType.EQUIP, 0, 1, 0, e, p, "peashooter.png", 4));
 
  //20 - Super Pea Shooter
  e = null;
  p = new LinkedList<ProjectileEffect>();
  p.add(ProjectileEffect.EXPLODE);
  data.put(20, new ItemData(20, "Super Pea Shooter", ItemDataType.EQUIP, 0, 3, 0, e, p, "peashooter.png", 4));
  
  //21 - Pea Sniper
  e = null;
  p = new LinkedList<ProjectileEffect>();
  p.add(ProjectileEffect.PENETRATE);
  data.put(21, new ItemData(21, "Pea Sniper", ItemDataType.EQUIP, 0, 5, 0, e, p, "peashooter.png", 4));
  
  //22 - Mega Cannon
  e = null;
  p = new LinkedList<ProjectileEffect>();
  p.add(ProjectileEffect.EXPLODE);
  p.add(ProjectileEffect.EXPLODE);
  p.add(ProjectileEffect.EXPLODE);
  p.add(ProjectileEffect.EXPLODE);
  data.put(22, new ItemData(22, "Mega Cannon", ItemDataType.EQUIP, -1, 15, 0, e, p, "cannon.png", 4));
  
  //23 - Laser Minigun
  e = null;
  p = new LinkedList<ProjectileEffect>();
  p.add(ProjectileEffect.BOUNCE);
  p.add(ProjectileEffect.BOUNCE);
  p.add(ProjectileEffect.BOUNCE);
  p.add(ProjectileEffect.BOUNCE);
  p.add(ProjectileEffect.BOUNCE);
  p.add(ProjectileEffect.BOUNCE);
  p.add(ProjectileEffect.BOUNCE);
  data.put(23, new ItemData(23, "Laser Minigun", ItemDataType.EQUIP, -1, 7, 0, e, p, "lasergun.png", 4));  
  
  //24 - Laser Chaingun
  e = null;
  p = new LinkedList<ProjectileEffect>();
  p.add(ProjectileEffect.PENETRATE);
  p.add(ProjectileEffect.PENETRATE);
  p.add(ProjectileEffect.PENETRATE);
  p.add(ProjectileEffect.PENETRATE);
  data.put(24, new ItemData(24, "Laser Chaingun", ItemDataType.EQUIP, 0, 11, 0, e, p, "lasergun.png", 4));
  
  //25 - Laser Pistol
  e = null;
  p = new LinkedList<ProjectileEffect>();
  data.put(25, new ItemData(25, "Laser Chaingun", ItemDataType.EQUIP, 1, 13, 0, e, p, "lasergun.png", 4));  
    }

    public ItemData getData (int id)
    {
	if (id == -1)
	    {
		return null;
	    }
	return data.get(id);
    }
}