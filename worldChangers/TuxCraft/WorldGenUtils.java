package worldChangers.TuxCraft;

import net.minecraft.world.World;

public class WorldGenUtils {

	public static int getMinimumBlockHeight(int x, int z, int w, int h,
			World world) {
		int cur = world.getTopSolidOrLiquidBlock(x, z);

		for (int i = x; i < x + w; i++)
			for (int j = z; j < z + h; j++)
				if (world.getTopSolidOrLiquidBlock(i, j) < cur)
					cur = world.getTopSolidOrLiquidBlock(i, j);
		return cur;
	}

	public static int getMaximumBlockHeight(int x, int z, int w, int h,
			World world) {
		int cur = world.getTopSolidOrLiquidBlock(x, z);

		for (int i = x; i < x + w; i++)
			for (int j = z; j < z + h; j++)
				if (world.getTopSolidOrLiquidBlock(i, j) > cur)
					cur = world.getTopSolidOrLiquidBlock(i, j);
		return cur;
	}

	public static int getHighestBlock(World world, int x, int z) {
		for (int i = 128; i > 0; i--) {
			if (world.getBlockId(x, i, z) != 0) {
				return i;
			}
		}
		return 0;
	}

	public static void clearCircle(int x, int y, int z, double r, World world) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) <= r)
					world.setBlockToAir((int) i + x, y, (int) j + z);
	}

	public static void drawCircle(int x, int y, int z, double r, World world,
			int bid) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)) <= r)
					world.setBlock((int) i + x, y, (int) j + z, bid);
	}

	public static void drawCube(int x, int y, int z, int w, int h, int d,
			int bid, World world) {
		for (int i = x; i < x + w; i++)
			for (int j = y; j < y + h; j++)
				for (int k = z; k < z + d; k++) {
					world.setBlock(i, j, k, bid);
				}
	}

	public static void clearCube(int x, int y, int z, int w, int h, int d,
			World world) {
		for (int i = x; i < x + w; i++)
			for (int j = y; j < y + h; j++)
				for (int k = z; k < z + d; k++) {
					world.setBlockToAir(i, j, k);
				}
	}
}
