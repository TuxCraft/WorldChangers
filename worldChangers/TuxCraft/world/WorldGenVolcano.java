package worldChangers.TuxCraft.world;

import java.util.Random;

import worldChangers.TuxCraft.WorldChangersCore;

import net.minecraft.block.Block;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVolcano extends WorldGenerator {

	public WorldGenVolcano(boolean b) {
		super(b);
	}

	public boolean generate(World world, Random random, int x, int y, int z) {

		int baseRadius = (random.nextInt(4) + 7) * 5;
		int mouthRadius = random.nextInt(5) + 2;
		int heightScale = random.nextInt(3) + 2;

		y = WorldGenUtils.getMinimumBlockHeight(x - baseRadius, z - baseRadius, baseRadius * 2, baseRadius * 2, world);

		int curRaidus = baseRadius;
		int curY = y;
		int curChanceOfNarrowing = heightScale;
		
		int height = 0;

		while (curRaidus > mouthRadius) {
			drawVolcanoCircle(x, curY, z, curRaidus, world, WorldChangersCore.volcanicRock.blockID, Block.lavaMoving.blockID, random);

			if (random.nextInt(curChanceOfNarrowing) == 1) {
				curChanceOfNarrowing = heightScale;
				curRaidus--;
			}

			curY++;
			height++;
		}
		curY--;

		drawVolcanoCircle(x, curY, z, mouthRadius - 2, world, Block.lavaMoving.blockID, WorldChangersCore.volcanicRock.blockID, random);

		WorldGenUtils.drawCube(x - 2, (height / 2) + 1, z - 2, 5, 5, 5, Block.netherBrick.blockID, world);
		WorldGenUtils.drawCube(x - 2, (height / 2), z - 2, 5, 3, 5, Block.fenceIron.blockID, world);
		WorldGenUtils.drawCube(x - 1, (height / 2), z - 1, 3, 4, 3, Block.stoneBrick.blockID, world);
		WorldGenUtils.clearCube(x - 1, (height / 2), z - 1, 3, 3, 3, world);
		world.setBlock(x, (height / 2) - 3, z, Block.glowStone.blockID);

		world.setBlock(x, (height / 2), z, Block.chest.blockID);
		TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(x, (height / 2), z);

		if (tileentitychest != null) {
			WCChestGenHooks info = WCChestGenHooks.getInfo("volcano");
			WeightedRandomChestContent.generateChestContents(random, info.getItems(random), tileentitychest, info.getCount(random));
		}

		return true;
	}

	void drawVolcanoCircle(int x, int y, int z, double r, World world, int bid, int b2id, Random random) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) <= r) {

					int randomInt = random.nextInt(150);

					if (randomInt == 1)
						world.setBlock((int) i + x, y, (int) j + z, b2id);
					else if (randomInt < 30) {
						world.setBlockToAir((int) i + x, y, (int) j + z);
						world.updateAllLightTypes((int) i + x, y, (int) j + z);
					} else
						world.setBlock((int) i + x, y, (int) j + z, bid);
				}
	}
}
