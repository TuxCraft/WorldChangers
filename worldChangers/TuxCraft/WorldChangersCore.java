package worldChangers.TuxCraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import worldChangers.TuxCraft.blocks.BlockVolcanicRock;
import worldChangers.TuxCraft.items.itemWorldChangers;
import worldChangers.TuxCraft.world.WorldChangersGenerator;

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
	public static Item ash;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		volcanicRock = new BlockVolcanicRock(252, "volcanicRock")
				.setHardness(1.5F).setResistance(10.0F)
				.setStepSound(new StepSound("stone", 1.0F, 1.0F));

		GameRegistry.registerBlock(volcanicRock);
		LanguageRegistry.addName(volcanicRock, "Volcanic Rock");

		ash = new itemWorldChangers(8000, "ash")
				.setCreativeTab(CreativeTabs.tabMaterials);
		LanguageRegistry.addName(ash, "Ash");

		GameRegistry.registerWorldGenerator(this.worldGenerator);
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {

		GameRegistry.registerFuelHandler(new FuelHandler());
		proxy.registerRenderers();
	}
}