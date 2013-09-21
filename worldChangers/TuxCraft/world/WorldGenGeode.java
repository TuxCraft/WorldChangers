package worldChangers.TuxCraft.world;

import java.util.HashMap;
import java.util.Random;

import worldChangers.TuxCraft.WorldChangersCore;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGeode extends WorldGenerator {

	public static final HashMap<Integer, Integer> blockChances = new HashMap<Integer, Integer>();

	static {
		blockChances.put(Block.stone.blockID, 75);
		blockChances.put(Block.oreCoal.blockID, 10);
		blockChances.put(Block.oreIron.blockID, 5);
		blockChances.put(Block.oreLapis.blockID, 2);
		blockChances.put(Block.oreRedstone.blockID, 4);
		blockChances.put(Block.oreGold.blockID, 3);
		blockChances.put(Block.oreDiamond.blockID, 1);
	}

	public boolean generate(World world, Random random, int x, int y, int z) {

		y = random.nextInt(10) + 20;

		double raidus = random.nextInt(5) + 7;

		drawRandomSphere(x, y, z, raidus, random, world);
		WorldGenUtils.clearSphere(x, y, z, raidus - 2, world);
		clearAndCaveThingys(x, y, z, raidus - 2, random, world);

		return true;
	}

	public static void drawRandomSphere(int x, int y, int z, double r, Random random, World world) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				for (double k = -r; k < r; k++)
					if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)) <= r) {

						int rand = random.nextInt(100) + 1;

						if (rand <= 75)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.stone.blockID);
						else if (rand <= 85)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.oreCoal.blockID);
						else if (rand <= 90)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.oreCoal.blockID);
						else if (rand <= 94)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.oreRedstone.blockID);
						else if (rand <= 97)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.oreGold.blockID);
						else if (rand <= 99)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.oreLapis.blockID);
						else
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.oreDiamond.blockID);
					}
	}

	public static void clearAndCaveThingys(int x, int y, int z, double r, Random rand, World world) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				for (double k = -r; k < r; k++)
					if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)) <= r) {
						if (world.getBlockId((int) i + x, (int) j + y - 1, (int) k + z) != 0 && world.getBlockId((int) i + x, (int) j + y - 1, (int) k + z) != Block.sponge.blockID) {
							if (rand.nextInt(3) == 1)
								world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.sponge.blockID);
						} else if (world.getBlockId((int) i + x, (int) j + y + 1, (int) k + z) != 0) {
							if (rand.nextInt(3) == 1)
								world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.brick.blockID, 9, 3);
						}
					}

		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				for (double k = -r; k < r; k++)
					if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)) <= r)
						if (world.getBlockId((int) i + x, (int) j + y, (int) k + z) == Block.sponge.blockID) {
							world.setBlockToAir((int) i + x, (int) j + y, (int) k + z);
							int height = rand.nextInt(5) + 2;
							for (int h = 0; h < height; h++)
								if (world.getBlockId((int) i + x, (int) j + y + 1 + h, (int) k + z) == 0 && world.getBlockId((int) i + x, (int) j + y + 2 + h, (int) k + z) == 0)
									world.setBlock((int) i + x, (int) j + y + h, (int) k + z, WorldChangersCore.caveThingyBlock.blockID);
						} else if (world.getBlockId((int) i + x, (int) j + y, (int) k + z) == Block.brick.blockID) {
							world.setBlockToAir((int) i + x, (int) j + y, (int) k + z);
							int height = rand.nextInt(5) + 2;
							for (int h = 0; h < height; h++)
								if (world.getBlockId((int) i + x, (int) j + y - 1 - h, (int) k + z) == 0 && world.getBlockId((int) i + x, (int) j + y - 2 - h, (int) k + z) == 0)
									world.setBlock((int) i + x, (int) j + y - h, (int) k + z, WorldChangersCore.caveThingyBlock.blockID, 9, 3);
						}
	}
}
