package worldChangers.TuxCraft.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.oredict.OreDictionary;

public class WCChestGenHooks
{

	public static final String VOLCANO = "volcano";
	private static final HashMap chestInfo = new HashMap();
	private int countMin = 0;
	private int countMax = 0;

	ArrayList<WeightedRandomChestContent> contents = new ArrayList<WeightedRandomChestContent>();

	private static void init()
	{

		ItemStack book = new ItemStack(Item.enchantedBook, 1, 0);

		WCChestGenHooks d = new WCChestGenHooks("volcano");
		d.countMin = 5;
		d.countMax = 8;
		chestInfo.put("volcano", d);
		addDungeonLoot(d, new ItemStack(Item.saddle), 150, 1, 2);
		addDungeonLoot(d, new ItemStack(Item.redstone), 100, 1, 8);
		addDungeonLoot(d, new ItemStack(Item.diamond), 50, 1, 1);
		addDungeonLoot(d, new ItemStack(Item.ingotIron), 100, 1, 4);
		addDungeonLoot(d, new ItemStack(Item.carrot), 100, 1, 8);
		addDungeonLoot(d, new ItemStack(Item.pumpkinPie), 100, 1, 8);
		addDungeonLoot(d, new ItemStack(Item.melonSeeds), 80, 1, 2);
		addDungeonLoot(d, new ItemStack(Item.appleGold), 50, 1, 2);
		addDungeonLoot(d, new ItemStack(Item.enderPearl), 80, 1, 4);
		addDungeonLoot(d, new ItemStack(Item.hoeDiamond), 50, 1, 1);
		addDungeonLoot(d, new ItemStack(Item.field_111213_cg), 50, 1, 1);
		addDungeonLoot(d, new ItemStack(Item.field_111216_cf), 75, 1, 1);
		addDungeonLoot(d, new ItemStack(Item.field_111215_ce), 100, 1, 1);
		addDungeonLoot(d, new ItemStack(Item.field_111212_ci), 100, 1, 3);
		addDungeonLoot(d, book, 100, 1, 1);
		addDungeonLoot(d, book, 100, 1, 1);
	}

	static void addDungeonLoot(WCChestGenHooks dungeon, ItemStack item,
	        int weight, int min, int max)
	{

		dungeon.addItem(new WeightedRandomChestContent(item, min, max, weight));
	}

	public static WCChestGenHooks getInfo(String category)
	{

		if (!chestInfo.containsKey(category))
		{
			chestInfo.put(category, new WCChestGenHooks(category));
		}
		return (WCChestGenHooks) chestInfo.get(category);
	}

	public static ItemStack[] generateStacks(Random rand, ItemStack source,
	        int min, int max)
	{

		int count = min + rand.nextInt(max - min + 1);

		ItemStack[] ret;
		if (source.getItem() == null)
		{
			ret = new ItemStack[0];
		} else if (count > source.getItem().getItemStackLimit())
		{
			ret = new ItemStack[count];
			for (int x = 0; x < count; x++)
			{
				ret[x] = source.copy();
				ret[x].stackSize = 1;
			}
		} else
		{
			ret = new ItemStack[1];
			ret[0] = source.copy();
			ret[0].stackSize = count;
		}
		return ret;
	}

	public static WeightedRandomChestContent[] getItems(String category,
	        Random rnd)
	{

		return getInfo(category).getItems(rnd);
	}

	public static int getCount(String category, Random rand)
	{

		return getInfo(category).getCount(rand);
	}

	public static void addItem(String category, WeightedRandomChestContent item)
	{

		getInfo(category).addItem(item);
	}

	public static void removeItem(String category, ItemStack item)
	{

		getInfo(category).removeItem(item);
	}

	public static ItemStack getOneItem(String category, Random rand)
	{

		return getInfo(category).getOneItem(rand);
	}

	public WCChestGenHooks(String category)
	{

	}

	public WCChestGenHooks(String category, WeightedRandomChestContent[] items,
	        int min, int max)
	{

		this(category);
		for (WeightedRandomChestContent item : items)
		{
			this.contents.add(item);
		}
		this.countMin = min;
		this.countMax = max;
	}

	public void addItem(WeightedRandomChestContent item)
	{

		this.contents.add(item);
	}

	public void removeItem(ItemStack item)
	{

		Iterator<WeightedRandomChestContent> itr = contents.iterator();
		while (itr.hasNext())
		{
			WeightedRandomChestContent cont = itr.next();
			if (item.isItemEqual(cont.theItemId)
			        || item.getItemDamage() == OreDictionary.WILDCARD_VALUE
			        && item.itemID == cont.theItemId.itemID)
			{
				itr.remove();
			}
		}
	}

	public WeightedRandomChestContent[] getItems(Random rnd)
	{

		ArrayList<WeightedRandomChestContent> ret = new ArrayList<WeightedRandomChestContent>();

		for (WeightedRandomChestContent orig : this.contents)
		{
			Item item = orig.theItemId.getItem();

			if (item != null)
			{
				WeightedRandomChestContent n = getChestGenBase(this, rnd, orig,
				        item);
				if (n != null)
				{
					ret.add(n);
				}
			}
		}

		return ret.toArray(new WeightedRandomChestContent[ret.size()]);
	}

	private WeightedRandomChestContent getChestGenBase(
	        WCChestGenHooks wcChestGenHooks, Random rnd,
	        WeightedRandomChestContent orig, Item item)
	{

		if (item instanceof ItemEnchantedBook)
		{
			return ((ItemEnchantedBook) item).func_92112_a(rnd,
			        orig.theMinimumChanceToGenerateItem,
			        orig.theMaximumChanceToGenerateItem, orig.itemWeight);
		}

		return orig;
	}

	public int getCount(Random rand)
	{

		return this.countMin < this.countMax ? this.countMin
		        + rand.nextInt(this.countMax - this.countMin) : this.countMin;
	}

	public ItemStack getOneItem(Random rand)
	{

		WeightedRandomChestContent[] items = getItems(rand);
		WeightedRandomChestContent item = (WeightedRandomChestContent) WeightedRandom
		        .getRandomItem(rand, items);
		ItemStack[] stacks = WCChestGenHooks.generateStacks(rand,
		        item.theItemId, item.theMinimumChanceToGenerateItem,
		        item.theMaximumChanceToGenerateItem);
		return stacks.length > 0 ? stacks[0] : null;
	}

	public int getMin()
	{

		return this.countMin;
	}

	public int getMax()
	{

		return this.countMax;
	}

	public void setMin(int value)
	{

		this.countMin = value;
	}

	public void setMax(int value)
	{

		this.countMax = value;
	}

	static
	{
		init();
	}
}