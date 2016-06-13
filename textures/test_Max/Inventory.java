import java.util.HashMap;
public class Inventory
{
    private final int maxItems = 30;
    //Item ID, quantity owned
    private HashMap<Integer, Integer> bag;
    //Armor, Head, Feet, Acc1, Wep, Mod1
    private int[] equipped;

    public int[] getEquipped()
    {
	return equipped;
    }

    public HashMap<Integer, Integer> getBag()
    {
	return bag;
    }

    public Inventory()
    {
	bag = new HashMap<Integer, Integer>();
	equipped = new int[9];
	for (int k = 0; k < equipped.length; k++)
	    {
		equipped[k] = -1;
	    }
    }

    public boolean add(int id)
    {
	if (bag.containsKey(id))
	    {
		bag.put(id, bag.get(id)+1);
	    }
	else
	    {
		if (bag.keySet().size() < maxItems)
		    {
			bag.put(id, 1);
		    }
		else
		    {
			return false;
		    }
	    }
	return true;
    }

    public boolean remove (int id)
    {
	if (bag.containsKey(id))
	    {
		int q = bag.get(id);
		if (q > 0)
		    {
			q -= 1;
			if (q <= 0)
			    {
				bag.remove(id);
			    }
			else
			    {
				bag.put(id, q);
			    }
			return true;
		    }
	    }
	return false;
    }

    public boolean equipItem(int id)
    {
	ItemDataProvider idp = ItemDataProvider.getInstance();
	ItemData toEquip = idp.getData(id);
	int k = toEquip.getSlot();
	ItemData equip = idp.getData(equipped[k]);
	if (remove(toEquip.getId()))
	    {
		if (equip == null || add(equip.getId()))
		    {
			equipped[toEquip.getSlot()] = toEquip.getId();
			return true;
		    }
		else
		    {
			add(toEquip.getId());
		    }
	    }
	return false;
    }

    
}