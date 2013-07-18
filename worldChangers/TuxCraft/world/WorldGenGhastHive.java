package worldChangers.TuxCraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldChangers.TuxCraft.WorldChangersCore;
import worldChangers.TuxCraft.WorldGenUtils;

public class WorldGenGhastHive extends WorldGenerator {

	public static final int MAX_SIZE = 20;
	public static final int MIN_SIZE = 10;

	public WorldGenGhastHive(boolean b) {
		super(b);
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {

		int ground = 0, top = 0, raidus = 0;

		for (int i = 126; i > 10; i--) {
			if (world.getBlockId(x, i, z) == 0) {
				top = i;
				break;
			}
		}

		for (int i = top; i > 0; i--) {
			if (world.getBlockId(x, i, z) != 0) {
				ground = i;
				break;
			}
		}

		raidus = (top - ground) / 2;
		if (raidus > MAX_SIZE)
			raidus = MAX_SIZE;
		if (raidus < MIN_SIZE)
			raidus = MIN_SIZE;

		drawSphereWithLava(x, top - raidus, z, raidus, WorldChangersCore.ghastHive.blockID, rand, world); // IRON
		makeHoles(x, top - raidus, z, raidus, world);
		WorldGenUtils.clearSphere(x, top - raidus, z, raidus - 2, world);
		drawRandomSphere(x, top - raidus, z, raidus - 1, rand, world);

		int i;
		for (i = 0; i < raidus; i++) {
			
		}
		
		WorldGenUtils.drawCube(x - 4, ground + i - 1, z - 4, 5, 1, 5, WorldChangersCore.ghastHive.blockID, world);
		world.setBlock(x, ground + i, z, Block.blockDiamond.blockID); // Chest
		
		return true;
	}

	public void drawRandomSphere(int x, int y, int z, double r, Random rand, World world) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				for (double k = -r; k < r; k++)
					if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)) <= r) {
						int random = rand.nextInt(2000);
						if (random <= 1) {
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.mobSpawner.blockID, 0, 2);
							TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getBlockTileEntity((int) i + x, (int) j + y, (int) k + z);
							if (tileentitymobspawner != null) {
								tileentitymobspawner.getSpawnerLogic().setMobID("Ghast");
							} else {
								System.err.println("Failed to fetch mob spawner entity at (" + (i + x) + ", " + (j + y) + ", " + (k + z) + ")");
							}
						} else if (random <= 15) {
							WorldGenUtils.drawCube((int) i + x - 2, (int) j + y, (int) k + z - 2, 4, 1, 4, WorldChangersCore.ghastHive.blockID, world);
							int random2 = rand.nextInt(42);
							if(random2 == 1)world.setBlock((int) i + x, (int) j + y + 1, (int) k + z, Block.blockDiamond.blockID);
						}
					}
	}

	public static void drawSphereWithLava(int x, int y, int z, double r, int block, Random rand, World world) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				for (double k = -r; k < r; k++) {
					if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2)) <= r) {
						int random = rand.nextInt(200);
						if (random <= 3)
							world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.sponge.blockID); //Do not change
						else if (random <= 4) {
							
							for(int a = 1; a <= (128 - y - j); a++)
							{
								if(world.isAirBlock((int) i + x, (int) j + y + a, (int) k + z))
										{
								world.setBlock((int) i + x, (int) j + y + a, (int) k + z, Block.web.blockID);	
										}
							}
							
							
						} else
							world.setBlock((int) i + x, (int) j + y, (int) k + z, block);
					}
				}
	}
	
	public static void makeHoles(int x, int y, int z, double r, World world) {
		for (double i = -r; i < r; i++)
			for (double j = -r; j < r; j++)
				for (double k = -r; k < r; k++) {
					if (world.getBlockId((int) i + x, (int) j + y, (int) k + z) == Block.sponge.blockID) {
						WorldGenUtils.clearCircle((int) i + x, (int) j + y, (int) k + z, 5, world);
					}
				}
	}
}
