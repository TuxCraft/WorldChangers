package worldChangers.TuxCraft.world;

import java.util.Random;

import worldChangers.TuxCraft.WorldChangersCore;
import worldChangers.TuxCraft.WorldGenUtils;

import net.minecraft.block.Block;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTVolcano extends WorldGenerator {

	public WorldGenTVolcano(boolean b) {
		super(b);
	}

	public boolean generate(World world, Random random, int x, int y, int z) {

		int baseRadius = (random.nextInt(4) + 3) * 5; // Bottom Width
		int mouthRadius = random.nextInt(5) + 2; // Top Width
		int heightScale = random.nextInt(3) + 2; // Height

		y = WorldGenUtils.getMinimumBlockHeight(x - baseRadius, z - baseRadius, baseRadius * 2, baseRadius * 2, world);

		int curRaidus = baseRadius;
		int curY = y;
		int curChanceOfNarrowing = heightScale;

		while (curRaidus > mouthRadius) {
			drawVolcanoCircle(x, curY, z, curRaidus, world, WorldChangersCore.volcanicRock.blockID, Block.lavaMoving.blockID, random);

			if (curChanceOfNarrowing >= 1) {
				if (random.nextInt(curChanceOfNarrowing) == 1) {
					curChanceOfNarrowing = heightScale;
					curRaidus--;
				}
			} else {
				curChanceOfNarrowing = heightScale;
				curRaidus--;
			}

			curChanceOfNarrowing--;
			curY++;
		}
		curY--;

		for (int i = curY; i > y; i--)
			drawVolcanoCircle(x, i, z, mouthRadius - 2, world, Block.lavaMoving.blockID, WorldChangersCore.volcanicRock.blockID, random);

		int volcanoDungeonOffset = ((curY - y) / 2);

		WorldGenUtils.drawCube(x - 2, y - 5 + volcanoDungeonOffset, z - 2, 5, 5, 5, Block.netherBrick.blockID, world);
		WorldGenUtils.drawCube(x - 2, y - 4 + volcanoDungeonOffset, z - 2, 5, 3, 5, Block.fenceIron.blockID, world);
		WorldGenUtils.drawCube(x - 1, y - 4 + volcanoDungeonOffset, z - 1, 3, 4, 3, Block.stoneBrick.blockID, world);
		WorldGenUtils.clearCube(x - 1, y - 4 + volcanoDungeonOffset, z - 1, 3, 3, 3, world);

		world.setBlock(x, y - 4 + volcanoDungeonOffset, z, Block.chest.blockID);
		TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(x, y - 4 + volcanoDungeonOffset, z);

		if (tileentitychest != null) {
			WCChestGenHooks info = WCChestGenHooks.getInfo("volcano");
			WeightedRandomChestContent.generateChestContents(random, info.getItems(random), tileentitychest, info.getCount(random));
		}

		return true;
	}

	public static void drawVolcanoCircle(int x, int y, int z, double r, World world, int bid, int b2id, Random random) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) <= r) {

					int randomInt = random.nextInt(150);

					if (randomInt == 1)
						world.setBlock((int) i + x, y, (int) j + z, b2id);
					else if (randomInt < 30) {
						world.setBlockToAir((int) i + x, y, (int) j + z);
					} else
						world.setBlock((int) i + x, y, (int) j + z, bid);
				}
	}
}
