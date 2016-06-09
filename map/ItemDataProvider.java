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
	initAll();
    }

    private HashMap<Integer, ItemData> data;
    //id, name, type, speed, attack, defense, effects, peffects, image, slot

    private void initAll()
    {
	//EXAMPLE ITEM 0
	HashMap<StatEffect, Integer> e = new HashMap<StatEfffect, Integer>();
	LinkedList<ProjectileEffect> p = null;
	e.put(StatEffect.HEAL2,300); //lvl 2 heal for 5 seconds
	e.put(StatEffect.SLOW1,300); //lvl 1 slow for 5 seconds
	data.put(0, new ItemData(0, "Example Item", ItemDataType.USE, 0, 0, 0, e, p, "example.png", -1));	
    }

    public ItemData getData (int id)
    {
	if (id = -1)
	    {
		return null;
	    }
	return data.get(id);
    }
}
