package worldChangers.TuxCraft.world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import worldChangers.TuxCraft.WorldChangersCore;
import worldChangers.TuxCraft.WorldGenUtils;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCrater extends WorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z) {

		int length = rand.nextInt(5) + 10;
		int yr = rand.nextInt(360);
		int depth = rand.nextInt(8) + 5;
		int chainLength = rand.nextInt(10) + 10;
		int startWidth = rand.nextInt(8) + 5;
		int endWidth = rand.nextInt(2) + 1;

		int astroidSize = endWidth + rand.nextInt(5) + 4;

		y = WorldGenUtils.getMaximumBlockHeight(x - startWidth, z - startWidth, startWidth * 2, startWidth * 2, world);

		double nx = length * Math.sin(Math.toRadians(yr)) + x;
		double nz = length * Math.cos(Math.toRadians(yr)) + z;
		double ny = y - depth;

		double distance = Math.sqrt(Math.abs(nx - x) + Math.abs(ny - y) + Math.abs(nz - z)) * 4;

		double xi = (x - nx) / distance;
		double yi = (y - ny) / distance;
		double zi = (z - nz) / distance;

		double i = x;
		double j = y;
		double k = z;

		boolean genning = true;

		int numberOfBlocks = 0;

		double deltaWidth = (startWidth - endWidth) / chainLength;
		double curWidth = startWidth;

		while (genning) {
			drawRandomCircle((int) i, (int) j, (int) k, (int) curWidth + 1, world, rand);
			WorldGenUtils.clearCircle((int) i, (int) j, (int) k, curWidth, world);
			

			i += xi;
			j -= yi;
			k += zi;
			curWidth -= deltaWidth;

			numberOfBlocks++;

			if (numberOfBlocks > 100) {
				genning = false;
				break;
			}
		}

		j += astroidSize;

		for (int l = 0; l < astroidSize; l++) {
			drawAstroidCircle((int) i, (int) j, (int) k, l, world, Block.whiteStone.blockID, Block.whiteStone.blockID, rand);
			drawAstroidCircle((int) i, (int) j, (int) k, l - 1, world, WorldChangersCore.astralCore.blockID, Block.whiteStone.blockID, rand);
			j--;
		}

		for (int l = astroidSize; l > 0; l--) {
			drawAstroidCircle((int) i, (int) j, (int) k, l, world, Block.whiteStone.blockID, Block.whiteStone.blockID, rand);
			drawAstroidCircle((int) i, (int) j, (int) k, l - 1, world, WorldChangersCore.astralCore.blockID, Block.whiteStone.blockID, rand);
			j--;
		}

		return false;
	}

	public static void drawRandomCircle(int x, int y, int z, double r, World world, Random random) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) <= r) {
					int randomInt = random.nextInt(50);
					if (randomInt == 1)
						world.setBlock((int) i + x, y, (int) j + z, WorldChangersCore.infiniteFire.blockID);
					else if (randomInt < 4)
						world.setBlockToAir((int) i + x, y, (int) j + z);
				}
	}

	public static void drawAstroidCircle(int x, int y, int z, double r, World world, int bid, int b2id, Random random) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) <= r) {

					int randomInt = random.nextInt(150);

					if (randomInt == 1)
						world.setBlock((int) i + x, y, (int) j + z, b2id);
					else if (randomInt < 10) {
						world.setBlockToAir((int) i + x, y, (int) j + z);
					} else
						world.setBlock((int) i + x, y, (int) j + z, bid);
				}
	}
}
