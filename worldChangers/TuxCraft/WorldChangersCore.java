package worldChangers.TuxCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import worldChangers.TuxCraft.blocks.BlackLight;
import worldChangers.TuxCraft.blocks.BlockVolcanicRock;
import worldChangers.TuxCraft.blocks.InfiniteFire;
import worldChangers.TuxCraft.blocks.WCBlock;
import worldChangers.TuxCraft.blocks.WCSlabs;
import worldChangers.TuxCraft.blocks.WCStairs;
import worldChangers.TuxCraft.items.WCItem;
import worldChangers.TuxCraft.items.itemBlackLight;
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
public class WorldChangersCore {

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
	public static Block ashenStairsTile;
	public static Block ashenSingleSlabTile;
	public static Block blackLight;
	public static Block astralCore;
	public static Block infiniteFire;
	public static Block ghastHive;

	public static Item ash;
	public static Item blackLightItem;

	public static int blockIDBase = 180;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {

		volcanicRock = new BlockVolcanicRock(blockIDBase, "volcanicRock")
				.propertyGroup("stone", null);
		GameRegistry.registerBlock(volcanicRock, "volcanicRock");
		LanguageRegistry.addName(volcanicRock, "Volcanic Rock");

		ashenStone = new WCBlock(blockIDBase + 1, Material.rock, "ashenStone")
				.propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStone, "ashenStone");
		LanguageRegistry.addName(ashenStone, "Ashen Stone");

		ashenStoneBrick = new WCBlock(blockIDBase + 2, Material.rock,
				"ashenStoneTile").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStoneBrick, "ashenStoneBrick");
		LanguageRegistry.addName(ashenStoneBrick, "Ashen Stone Tile");

		ashenStonePillar = new WCBlock(blockIDBase + 3, Material.rock,
				"ashenStonePillar").propertyGroup("stone", "pillar");
		GameRegistry.registerBlock(ashenStonePillar, "ashenStonePillar");
		LanguageRegistry.addName(ashenStonePillar, "Ashen Stone Pillar");

		ashenStairs = new WCStairs(blockIDBase + 4, ashenStone, 0,
				"ashenStairs").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStairs, "ashenStairs");
		LanguageRegistry.addName(ashenStairs, "Ashen Stone Stairs");

		ashenSingleSlab = new WCSlabs(blockIDBase + 5, ashenStone, false,
				"ashenSlab").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenSingleSlab, "ashenSingleSlab");
		LanguageRegistry.addName(ashenSingleSlab, "Ashen Stone Slab");

		ashenStairsTile = new WCStairs(blockIDBase + 6, ashenStoneBrick, 0,
				"ashenStairs").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStairsTile, "ashenStairsTile");
		LanguageRegistry.addName(ashenStairsTile, "Ashen Tile Stairs");

		ashenSingleSlabTile = new WCSlabs(blockIDBase + 7, ashenStoneBrick,
				false, "ashenSlab").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenSingleSlabTile, "ashenSingleSlabTile");
		LanguageRegistry.addName(ashenSingleSlabTile, "Ashen Tile Slab");

		blackLight = new BlackLight(blockIDBase + 8, "blackLight")
				.propertyGroup("lightSource", null);
		GameRegistry.registerBlock(blackLight, "BlackLight");
		LanguageRegistry.addName(blackLight, "Black Light");

		astralCore = new WCBlock(blockIDBase + 9, Material.rock, "astralCore")
				.propertyGroup("glowingStone", null);
		GameRegistry.registerBlock(astralCore, "AstralCore");
		LanguageRegistry.addName(astralCore, "Astral Core");

		infiniteFire = new InfiniteFire(blockIDBase + 10).setHardness(0.0F)
				.setLightValue(0.5F).setUnlocalizedName("fire")
				.func_111022_d("fire");
		GameRegistry.registerBlock(infiniteFire, "infiniteFire");
		
		ghastHive = new WCBlock(blockIDBase + 11, Material.web, "ghastHive")
			.propertyGroup("stone", null);
		GameRegistry.registerBlock(ghastHive, "ghastHive");
		LanguageRegistry.addName(ghastHive, "Ghast Hive Bindings");

		ash = new WCItem(8000, "ash").setCreativeTab(CreativeTabs.tabMaterials);
		LanguageRegistry.addName(ash, "Ash");

		blackLightItem = new itemBlackLight(8001, "blackLight")
				.setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(blackLightItem, "Black Light");

		GameRegistry.registerWorldGenerator(this.worldGenerator);

		WCrecipes.addRecipes();

		GameRegistry.registerFuelHandler(new FuelHandler());
		proxy.registerRenderers();
	}
}
