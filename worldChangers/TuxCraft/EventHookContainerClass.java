package worldChangers.TuxCraft;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import worldChangers.TuxCraft.world.WorldGenCrater;
import worldChangers.TuxCraft.world.WorldGenGeode;
import worldChangers.TuxCraft.world.WorldGenGhastHive;
import worldChangers.TuxCraft.world.WorldGenVolcano;

public class EventHookContainerClass {

	// TODO Tux, generate the geodes in the world, heres the code:
	// WorldGenGeode geode = new WorldGenGeode();
	// geode.generate(world, new Random(), (int) player.posX, (int) player.posY,
	// (int) player.posZ);

	@ForgeSubscribe
	public void decorateChunk(DecorateBiomeEvent.Post event) {
		switch (event.world.provider.dimensionId) {
		case 0:
			generateOverworld(event.world, event.rand, event.chunkX, event.chunkZ);
			break;
		case 1:
			break;
		case -1:
			generateNether(event.world, event.rand, event.chunkX, event.chunkZ);
			break;
		}

	}

	private boolean generateOverworld(World world, Random random, int chunkX, int chunkZ) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = WorldGenUtils.getHighestBlock(world, x, z);

		if ((world.getBiomeGenForCoords(x, z).minHeight > 0.0F) && (world.getBiomeGenForCoords(x, z).maxHeight < 0.4F)) {
			if (random.nextInt(WorldChangersCore.volcanoSpawnRate) == 1) {
				long start = System.currentTimeMillis();
				new WorldGenVolcano(true).generate(world, random, x, y, z);
				System.out.println("[World Changers] Volcono spawned around " + x + " " + y + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
			} else if (random.nextInt(WorldChangersCore.craterSpawnRate) == 1) {
				long start = System.currentTimeMillis();
				new WorldGenCrater().generate(world, random, x, y, z);
				System.out.println("[World Changers] Crater spawned around " + x + " " + y + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
			} else if (random.nextInt(WorldChangersCore.geodeSpawnRate) == 1) {
				long start = System.currentTimeMillis();
				new WorldGenGeode().generate(world, random, x, y, z);
				System.out.println("[World Changers] Geode spawned around " + x + " " + 30 + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
			}
		}
		return true;
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ) {
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = WorldGenUtils.getHighestBlock(world, x, z);

		if (random.nextInt(WorldChangersCore.volcanoSpawnRate) == 1) {
			long start = System.currentTimeMillis();
			new WorldGenGhastHive(true).generate(world, random, x, y, z);
			System.out.println("[World Changers] Ghasthive spawned around " + x + " " + y + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
		}
	}

}
