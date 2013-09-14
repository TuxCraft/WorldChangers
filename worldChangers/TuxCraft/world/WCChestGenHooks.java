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
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;

public class WCChestGenHooks
{

	public static final String VOLCANO = "volcano";
	public static final String HIVE_SMALL = "hiveSmall";
	private static final HashMap<String, WCChestGenHooks> WCchestInfo = new HashMap<String, WCChestGenHooks>();
	
	private static boolean hasInit = false;
    static
    {
        init();
    }

	private static void init()
	{
		if (hasInit)
        {
            return;
        }
		
		hasInit = true;
		
		addInfo(VOLCANO, WorldGenVolcano.volcanoDungeonLoot, 10, 10);
		addInfo(HIVE_SMALL, WorldGenGhastHive.ghastHiveSmallChestContents, 3, 5);
		
		ItemStack book = new ItemStack(Item.enchantedBook, 1, 0);
        WeightedRandomChestContent tmp = new WeightedRandomChestContent(book, 1, 1, 1);
        getInfo(VOLCANO).addItem(tmp);
	}
	
	static void addDungeonLoot(ChestGenHooks dungeon, ItemStack item, int weight, int min, int max)
    {
        dungeon.addItem(new WeightedRandomChestContent(item, min, max, weight));
    }
	
	private static void addInfo(String category, WeightedRandomChestContent[] items, int min, int max)
    {
		WCchestInfo.put(category, new WCChestGenHooks(category, items, min, max));
    }
	
	public static WCChestGenHooks getInfo(String category)
    {
        if (!WCchestInfo.containsKey(category))
        {
        	WCchestInfo.put(category, new WCChestGenHooks(category));
        }
        return (WCChestGenHooks) WCchestInfo.get(category);
    }
	
	public static ItemStack[] generateStacks(Random rand, ItemStack source, int min, int max)
    {
        int count = min + (rand.nextInt(max - min + 1));

        ItemStack[] ret;
        if (source.getItem() == null)
        {
            ret = new ItemStack[0];
        }
        else if (count > source.getItem().getItemStackLimit())
        {
            ret = new ItemStack[count];
            for (int x = 0; x < count; x++)
            {
                ret[x] = source.copy();
                ret[x].stackSize = 1;
            }
        }
        else
        {
            ret = new ItemStack[1];
            ret[0] = source.copy();
            ret[0].stackSize = count;
        }
        return ret;
    }
	
	public static WeightedRandomChestContent[] getItems(String category, Random rnd){ return getInfo(category).getItems(rnd); }
    public static int getCount(String category, Random rand){ return getInfo(category).getCount(rand); }
    public static void addItem(String category, WeightedRandomChestContent item){ getInfo(category).addItem(item); }
    public static void removeItem(String category, ItemStack item){ getInfo(category).removeItem(item); }
    public static ItemStack getOneItem(String category, Random rand){ return getInfo(category).getOneItem(rand); }

    private String category;
    private int countMin = 0;
    private int countMax = 0;
    //TO-DO: Privatize this once again when we remove the Deprecated stuff in DungeonHooks
    ArrayList<WeightedRandomChestContent> contents = new ArrayList<WeightedRandomChestContent>();

    public WCChestGenHooks(String category)
    {
        this.category = category;
    }

    public WCChestGenHooks(String category, WeightedRandomChestContent[] items, int min, int max)
    {
        this(category);
        for (WeightedRandomChestContent item : items)
        {
            contents.add(item);
        }
        countMin = min;
        countMax = max;
    }

    /**
     * Adds a new entry into the possible items to generate.
     *
     * @param item The item to add.
     */
    public void addItem(WeightedRandomChestContent item)
    {
        contents.add(item);
    }

    /**
     * Removes all items that match the input item stack, Only metadata and item ID are checked.
     * If the input item has a metadata of -1, all metadatas will match.
     *
     * @param item The item to check
     */
    public void removeItem(ItemStack item)
    {
        Iterator<WeightedRandomChestContent> itr = contents.iterator();
        while(itr.hasNext())
        {
            WeightedRandomChestContent cont = itr.next();
            if (item.isItemEqual(cont.theItemId) || (item.getItemDamage() == OreDictionary.WILDCARD_VALUE && item.itemID == cont.theItemId.itemID))
            {
                itr.remove();
            }
        }
    }

    /**
     * Gets an array of all random objects that are associated with this category.
     *
     * @return The random objects
     */
    public WeightedRandomChestContent[] getItems(Random rnd)
    {
        ArrayList<WeightedRandomChestContent> ret = new ArrayList<WeightedRandomChestContent>();

        for (WeightedRandomChestContent orig : contents)
        {
            Item item = orig.theItemId.getItem();

            if (item != null)
            {
                WeightedRandomChestContent n = getChestGenBase(this, rnd, orig, item);
                if (n != null)
                {
                    ret.add(n);
                }
            }
        }

        return ret.toArray(new WeightedRandomChestContent[ret.size()]);
    }
    
    public WeightedRandomChestContent getChestGenBase(WCChestGenHooks chest, Random rnd, WeightedRandomChestContent original, Item item)
    {
        if (item instanceof ItemEnchantedBook)
        {
            return ((ItemEnchantedBook)item).func_92112_a(rnd,
                    original.theMinimumChanceToGenerateItem,
                    original.theMaximumChanceToGenerateItem, original.itemWeight);
        }
        return original;
    }

    /**
     * Gets a random number between countMin and countMax.
     *
     * @param rand A RNG
     * @return A random number where countMin <= num <= countMax
     */
    public int getCount(Random rand)
    {
        return countMin < countMax ? countMin + rand.nextInt(countMax - countMin) : countMin;
    }

    /**
     * Returns a single ItemStack from the possible items in this registry,
     * Useful if you just want a quick and dirty random Item.
     *
     * @param rand  A Random Number gen
     * @return A single ItemStack, or null if it could not get one.
     */
    public ItemStack getOneItem(Random rand)
    {
        WeightedRandomChestContent[] items = getItems(rand);
        WeightedRandomChestContent item = (WeightedRandomChestContent)WeightedRandom.getRandomItem(rand, items);
        ItemStack[] stacks = ChestGenHooks.generateStacks(rand, item.theItemId, item.theMinimumChanceToGenerateItem, item.theMaximumChanceToGenerateItem);
        return (stacks.length > 0 ? stacks[0] : null);
    }

    //Accessors
    public int getMin(){ return countMin; }
    public int getMax(){ return countMax; }
    public void setMin(int value){ countMin = value; }
    public void setMax(int value){ countMax = value; }
}