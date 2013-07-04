package worldChangers.TuxCraft;


import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import worldChangers.TuxCraft.blocks.BlockVolcanicRock;
import worldChangers.TuxCraft.blocks.WCBlock;
import worldChangers.TuxCraft.blocks.WCSlabs;
import worldChangers.TuxCraft.blocks.WCStairs;
import worldChangers.TuxCraft.items.itemWorldChangers;
import worldChangers.TuxCraft.world.WorldChangersGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "World Changers", name = "World Changers", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class WorldChangersCore
{
	
	@Mod.Instance("World Changers")
	public static WorldChangersCore instance;
	
	@SidedProxy(clientSide = "worldChangers.TuxCraft.ClientProxy", serverSide = "worldChangers.TuxCraft.CommonProxy")
	public static CommonProxy proxy;
	public static final String modid = "World Changers";
	WorldChangersGenerator worldGenerator = new WorldChangersGenerator();
	
	public static Block volcanicRock;
	public static Block ashenStone;
	public static Block ashenStoneBrick;
	public static Block ashenStonePillar;
	public static Block ashenStairs;
	public static Block ashenSingleSlab;
	
	public static Item ash;
	
	public static int blockIDBase = 180;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	
		volcanicRock = new BlockVolcanicRock(blockIDBase, "volcanicRock").propertyGroup("stone", null);
		GameRegistry.registerBlock(volcanicRock);
		LanguageRegistry.addName(volcanicRock, "Volcanic Rock");
		
		ashenStone = new WCBlock(blockIDBase + 1, Material.rock, "ashenStone").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStone);
		LanguageRegistry.addName(ashenStone, "Ashen Stone");
		
		ashenStoneBrick = new WCBlock(blockIDBase + 2, Material.rock, "ashenStoneBricks").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStoneBrick);
		LanguageRegistry.addName(ashenStoneBrick, "Ashen Stone Bricks");
		
		ashenStonePillar = new WCBlock(blockIDBase + 3, Material.rock, "ashenStonePillar").propertyGroup("stone", "pillar");
		GameRegistry.registerBlock(ashenStonePillar);
		LanguageRegistry.addName(ashenStonePillar, "Ashen Stone Pillar");
		
		ashenStairs = new WCStairs(blockIDBase + 4, ashenStone, 0, "ashenStairs").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStairs);
		LanguageRegistry.addName(ashenStairs, "Ashen Stone Stairs");
		
		ashenSingleSlab = new WCSlabs(blockIDBase + 5, ashenStone, false, "ashenSlab").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenSingleSlab);
		LanguageRegistry.addName(ashenSingleSlab, "Ashen Stone Slab");
		
		ash = new itemWorldChangers(8000, "ash")
				.setCreativeTab(CreativeTabs.tabMaterials);
		LanguageRegistry.addName(ash, "Ash");
		
		GameRegistry.registerWorldGenerator(this.worldGenerator);
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
	
		WCrecipes.addRecipes();
		
		GameRegistry.registerFuelHandler(new FuelHandler());
		proxy.registerRenderers();
	}
}
