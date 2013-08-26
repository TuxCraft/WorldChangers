package worldChangers.TuxCraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class WCrecipes {
	public static void addRecipes() {
		Block ashenStone = WorldChangersCore.ashenStone;
		Block ashenBrick = WorldChangersCore.ashenStoneBrick;
		Block ashenPillar = WorldChangersCore.ashenStonePillar;
		Block ashenStairs = WorldChangersCore.ashenStairs;
		Block ashenSlab = WorldChangersCore.ashenSingleSlab;

		Block stone = Block.stone;

		Item ash = WorldChangersCore.ash;
		Item blackLight = WorldChangersCore.blackLightItem;
		
		Item glowstone = Item.glowstone;
		Item quartz = Item.netherQuartz;
		
		ItemStack inkSack = new ItemStack(Item.dyePowder, 1, 0);
		ItemStack charcoal = new ItemStack(Item.coal, 1, 1);

		GameRegistry.addShapelessRecipe(new ItemStack(blackLight), quartz, quartz, inkSack, glowstone);
		GameRegistry.addShapelessRecipe(new ItemStack(ashenStone), ash, stone);
		GameRegistry.addShapelessRecipe(new ItemStack(ash, 2), charcoal);

		GameRegistry.addRecipe(new ItemStack(ashenBrick, 4), "xx", "xx", 'x',
				ashenStone);
		GameRegistry.addRecipe(new ItemStack(ashenPillar, 2), " x", " x", 'x',
				ashenStone);
		GameRegistry.addRecipe(new ItemStack(ashenStairs, 4), "x  ", "xx ",
				"xxx", 'x', ashenBrick);
		GameRegistry.addRecipe(new ItemStack(ashenSlab, 6), "   ", "   ",
				"xxx", 'x', ashenBrick);
	}
}
