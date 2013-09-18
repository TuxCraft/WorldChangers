package worldChangers.TuxCraft;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import worldChangers.TuxCraft.blocks.BlackLight;
import worldChangers.TuxCraft.blocks.BlockVolcanicRock;
import worldChangers.TuxCraft.blocks.CaveThingy;
import worldChangers.TuxCraft.blocks.WCBlock;
import worldChangers.TuxCraft.blocks.WCSlabs;
import worldChangers.TuxCraft.blocks.WCStairs;
import worldChangers.TuxCraft.items.ItemBlackLight;
import worldChangers.TuxCraft.items.WCItem;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "World Changers", name = "World Changers", version = "0.3BETA")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class WorldChangersCore {

	@Mod.Instance("World Changers")
	public static WorldChangersCore instance;

	@SidedProxy(clientSide = "worldChangers.TuxCraft.ClientProxy", serverSide = "worldChangers.TuxCraft.CommonProxy")
	public static CommonProxy proxy;
	public static final String modid = "World Changers";

	public static Block volcanicRock;
	public static Block ashenStone;
	public static Block ashenStoneBrick;
	public static Block ashenStonePillar;
	public static Block ashenStairs;
	public static Block ashenSingleSlab;
	public static Block ashenStairsTile;
	public static CaveThingy caveThingyBlock;
	public static Block ashenSingleSlabTile;
	public static Block blackLight;
	public static Block astralCore;
	public static Block ghastHive;

	public static Item ash;
	public static Item blackLightItem;

	public static int volcanoSpawnRate;
	public static int ghastHiveSpawnRate;
	public static int craterSpawnRate;
	
	public static CaveThingyRendender caveThingyRendender;
	public static int caveThingyRenderId = 0;

	public static CreativeTabsWorldChangers creativeTab = new CreativeTabsWorldChangers("World Changers");

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {

		caveThingyRendender = new CaveThingyRendender();
		caveThingyRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(caveThingyRenderId, caveThingyRendender);
		
		Configuration config = new Configuration(new File("WorldChangers.cfg"));
		config.load();

		volcanicRock = new BlockVolcanicRock(config.getBlock("volcanicRock", 180).getInt(), "volcanicRock").propertyGroup("stone", null);
		GameRegistry.registerBlock(volcanicRock, "volcanicRock");
		LanguageRegistry.addName(volcanicRock, "Volcanic Rock");

		ashenStone = new WCBlock(config.getBlock("ashenStone", 181).getInt(), Material.rock, "ashenStone").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStone, "ashenStone");
		LanguageRegistry.addName(ashenStone, "Ashen Stone");

		ashenStoneBrick = new WCBlock(config.getBlock("ashenStoneTile", 182).getInt(), Material.rock, "ashenStoneTile").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStoneBrick, "ashenStoneBrick");
		LanguageRegistry.addName(ashenStoneBrick, "Ashen Stone Tile");

		ashenStonePillar = new WCBlock(config.getBlock("ashenStonePillar", 183).getInt(), Material.rock, "ashenStonePillar").propertyGroup("stone", "pillar");
		GameRegistry.registerBlock(ashenStonePillar, "ashenStonePillar");
		LanguageRegistry.addName(ashenStonePillar, "Ashen Stone Pillar");

		ashenStairs = new WCStairs(config.getBlock("ashenStairs", 184).getInt(), ashenStone, 0, "ashenStairs").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenStairs, "ashenStairs");
		LanguageRegistry.addName(ashenStairs, "Ashen Stone Stairs");

		ashenSingleSlab = new WCSlabs(config.getBlock("ashenSingleSlab", 185).getInt(), ashenStone, false, "ashenSlab").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenSingleSlab, "ashenSingleSlab");
		LanguageRegistry.addName(ashenSingleSlab, "Ashen Stone Slab");

		// ashenStairsTile = new WCStairs(config.getBlock("ashenStairs",
		// 186).getInt(), ashenStoneBrick, 0,
		// "ashenStairs").propertyGroup("stone", null);
		// GameRegistry.registerBlock(ashenStairsTile, "ashenStairsTile");
		// LanguageRegistry.addName(ashenStairsTile, "Ashen Tile Stairs");

		ashenSingleSlabTile = new WCSlabs(config.getBlock("ashenSingleSlabTile", 187).getInt(), ashenStoneBrick, false, "ashenSlab").propertyGroup("stone", null);
		GameRegistry.registerBlock(ashenSingleSlabTile, "ashenSingleSlabTile");
		LanguageRegistry.addName(ashenSingleSlabTile, "Ashen Tile Slab");

		blackLight = new BlackLight(config.getBlock("blackLight", 188).getInt(), "blackLight").propertyGroup("lightSource", null);
		GameRegistry.registerBlock(blackLight, "BlackLight");
		LanguageRegistry.addName(blackLight, "Black Light");

		astralCore = new WCBlock(config.getBlock("astralCore", 189).getInt(), Material.rock, "astralCore").propertyGroup("glowingStone", null);
		GameRegistry.registerBlock(astralCore, "AstralCore");
		LanguageRegistry.addName(astralCore, "Astral Core");

		ghastHive = new WCBlock(config.getBlock("ghastHive", 190).getInt(), Material.web, "ghastHive").propertyGroup("stone", null);
		GameRegistry.registerBlock(ghastHive, "ghastHive");
		LanguageRegistry.addName(ghastHive, "Ghast Hive Bindings");

		caveThingyBlock = new CaveThingy(config.getBlock("caveThingy", 191).getInt(), Material.rock, "caveThingy");
		GameRegistry.registerBlock(caveThingyBlock, "caveThingy");
		LanguageRegistry.addName(caveThingyBlock, "Cave Thingy");
		
		ash = new WCItem(config.getItem("ash", 8000).getInt(), "ash");
		LanguageRegistry.addName(ash, "Ash");

		blackLightItem = new ItemBlackLight(config.getItem("blackLight", 8001).getInt(), "blackLight");

		LanguageRegistry.addName(blackLightItem, "Black Light");

		Property volcanoSpawnRateP = config.get("Spawn Rates", "volcanoSpawnRate", 142);
		volcanoSpawnRateP.comment = "Higher values = less spawns";

		Property ghastHiveSpawnRateP = config.get("Spawn Rates", "ghastHiveSpawnRate", 142);
		ghastHiveSpawnRateP.comment = "Higher values = less spawns";

		Property craterSpawnRateP = config.get("Spawn Rates", "craterSpawnRate", 142 / 2);
		craterSpawnRateP.comment = "Higher values = less spawns";

		volcanoSpawnRate = volcanoSpawnRateP.getInt();
		ghastHiveSpawnRate = ghastHiveSpawnRateP.getInt();
		craterSpawnRate = craterSpawnRateP.getInt();

		LanguageRegistry.instance().addStringLocalization("itemGroup.World Changers", "World Changers");

		config.save();

		WCrecipes.addRecipes();

		GameRegistry.registerFuelHandler(new FuelHandler());
		proxy.registerRenderers();

		MinecraftForge.EVENT_BUS.register(new EventHookContainerClass());
	}
}
