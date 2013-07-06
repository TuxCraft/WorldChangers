package worldChangers.TuxCraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldChangers.TuxCraft.WorldChangersCore;

public class WorldGenVolcano extends WorldGenerator {

	double baseRadius;
	double mouthRadius;

	public WorldGenVolcano(boolean par1, double par2, double par3) {

		super(par1);
		this.baseRadius = par2;
		this.mouthRadius = par3;
	}

	@Override
	public boolean generate(World par1World, Random rand, int par3, int par4, int par5) {

		int[] lavaLoc = null;

		int height = rand.nextInt(10) + 20;
		double increment = (this.baseRadius - this.mouthRadius) / height;

		double radius = this.mouthRadius;

		for (int i = height; i >= 0; i--) {
			radius += increment;

			for (double ii = radius * 2.0D; ii >= 0.0D; ii -= 1.0D) {
				makeCylinder(par1World, par3, i + par4, par5, ii / 2.0D, Block.lavaMoving, rand);
			}

			for (double ii = radius * 2.0D; ii >= (radius - 4.0D) * 2.0D; ii -= 1.0D) {
				int[] intArray = makeCylinder(par1World, par3, i + par4, par5, ii / 2.0D, WorldChangersCore.volcanicRock, rand);
				if (intArray != null) {
					lavaLoc = intArray;
				}
			}
			if (lavaLoc != null) {
				setLava(lavaLoc[0], lavaLoc[1], lavaLoc[2], par1World);
			}
		}

		for (double ii = (this.mouthRadius - 1.0D) * 2.0D; ii >= 0.0D; ii -= 1.0D) {
			makeCylinder(par1World, par3, height + par4, par5, ii / 2.0D, Block.lavaMoving, rand);
		}

		par1World.setBlock(par3, par4 + height, par5, Block.lavaMoving.blockID);

		spawnVolcanoDungeon(par1World, par3, par4 + height / 2, par5, rand);

		return true;
	}

	private void spawnVolcanoDungeon(World world, int x, int y, int z, Random rand) {

		int netherBrick = Block.netherBrick.blockID;
		int stoneBrick = Block.stoneBrick.blockID;
		int ironFence = Block.fenceIron.blockID;

		int level = -1;

		world.setBlock(x, y + level, z, netherBrick);

		world.setBlock(x + 1, y + level, z, stoneBrick);
		world.setBlock(x - 1, y + level, z, stoneBrick);
		world.setBlock(x, y + level, z + 1, stoneBrick);
		world.setBlock(x, y + level, z - 1, stoneBrick);
		world.setBlock(x + 1, y + level, z + 1, stoneBrick);
		world.setBlock(x - 1, y + level, z + 1, stoneBrick);
		world.setBlock(x + 1, y + level, z - 1, stoneBrick);
		world.setBlock(x - 1, y + level, z - 1, stoneBrick);

		world.setBlock(x + 2, y + level, z, netherBrick);
		world.setBlock(x - 2, y + level, z, netherBrick);
		world.setBlock(x, y + level, z + 2, netherBrick);
		world.setBlock(x, y + level, z - 2, netherBrick);
		world.setBlock(x + 1, y + level, z + 2, netherBrick);
		world.setBlock(x - 1, y + level, z + 2, netherBrick);
		world.setBlock(x + 1, y + level, z - 2, netherBrick);
		world.setBlock(x - 1, y + level, z - 2, netherBrick);
		world.setBlock(x + 2, y + level, z + 1, netherBrick);
		world.setBlock(x - 2, y + level, z + 1, netherBrick);
		world.setBlock(x + 2, y + level, z - 1, netherBrick);
		world.setBlock(x - 2, y + level, z - 1, netherBrick);
		world.setBlock(x + 2, y + level, z + 2, netherBrick);
		world.setBlock(x - 2, y + level, z + 2, netherBrick);
		world.setBlock(x + 2, y + level, z - 2, netherBrick);
		world.setBlock(x - 2, y + level, z - 2, netherBrick);

		level = 0;

		world.setBlock(x + 2, y + level, z, ironFence);
		world.setBlock(x - 2, y + level, z, ironFence);
		world.setBlock(x, y + level, z + 2, ironFence);
		world.setBlock(x, y + level, z - 2, ironFence);
		world.setBlock(x + 1, y + level, z + 2, ironFence);
		world.setBlock(x - 1, y + level, z + 2, ironFence);
		world.setBlock(x + 1, y + level, z - 2, ironFence);
		world.setBlock(x - 1, y + level, z - 2, ironFence);
		world.setBlock(x + 2, y + level, z + 1, ironFence);
		world.setBlock(x - 2, y + level, z + 1, ironFence);
		world.setBlock(x + 2, y + level, z - 1, ironFence);
		world.setBlock(x - 2, y + level, z - 1, ironFence);
		world.setBlock(x + 2, y + level, z + 2, ironFence);
		world.setBlock(x - 2, y + level, z + 2, ironFence);
		world.setBlock(x + 2, y + level, z - 2, ironFence);
		world.setBlock(x - 2, y + level, z - 2, ironFence);

		world.setBlockToAir(x + 1, y + level, z);
		world.setBlockToAir(x - 1, y + level, z);
		world.setBlockToAir(x, y + level, z + 1);
		world.setBlockToAir(x, y + level, z - 1);
		world.setBlockToAir(x + 1, y + level, z + 1);
		world.setBlockToAir(x - 1, y + level, z + 1);
		world.setBlockToAir(x + 1, y + level, z - 1);
		world.setBlockToAir(x - 1, y + level, z - 1);

		world.setBlock(x, y + level, z, Block.chest.blockID, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(x, y + level, z);

		if (tileentitychest != null) {
			WCChestGenHooks info = WCChestGenHooks.getInfo("volcano");
			WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), tileentitychest, info.getCount(rand));
		}

		level = 1;

		world.setBlock(x + 2, y + level, z, ironFence);
		world.setBlock(x - 2, y + level, z, ironFence);
		world.setBlock(x, y + level, z + 2, ironFence);
		world.setBlock(x, y + level, z - 2, ironFence);
		world.setBlock(x + 1, y + level, z + 2, ironFence);
		world.setBlock(x - 1, y + level, z + 2, ironFence);
		world.setBlock(x + 1, y + level, z - 2, ironFence);
		world.setBlock(x - 1, y + level, z - 2, ironFence);
		world.setBlock(x + 2, y + level, z + 1, ironFence);
		world.setBlock(x - 2, y + level, z + 1, ironFence);
		world.setBlock(x + 2, y + level, z - 1, ironFence);
		world.setBlock(x - 2, y + level, z - 1, ironFence);
		world.setBlock(x + 2, y + level, z + 2, ironFence);
		world.setBlock(x - 2, y + level, z + 2, ironFence);
		world.setBlock(x + 2, y + level, z - 2, ironFence);
		world.setBlock(x - 2, y + level, z - 2, ironFence);

		world.setBlockToAir(x, y + level, z);
		world.setBlockToAir(x + 1, y + level, z);
		world.setBlockToAir(x - 1, y + level, z);
		world.setBlockToAir(x, y + level, z + 1);
		world.setBlockToAir(x, y + level, z - 1);
		world.setBlockToAir(x + 1, y + level, z + 1);
		world.setBlockToAir(x - 1, y + level, z + 1);
		world.setBlockToAir(x + 1, y + level, z - 1);
		world.setBlockToAir(x - 1, y + level, z - 1);

		level = 2;

		world.setBlock(x, y + level, z, netherBrick);

		world.setBlock(x + 1, y + level, z, stoneBrick);
		world.setBlock(x - 1, y + level, z, stoneBrick);
		world.setBlock(x, y + level, z + 1, stoneBrick);
		world.setBlock(x, y + level, z - 1, stoneBrick);
		world.setBlock(x + 1, y + level, z + 1, stoneBrick);
		world.setBlock(x - 1, y + level, z + 1, stoneBrick);
		world.setBlock(x + 1, y + level, z - 1, stoneBrick);
		world.setBlock(x - 1, y + level, z - 1, stoneBrick);

		world.setBlock(x + 2, y + level, z, netherBrick);
		world.setBlock(x - 2, y + level, z, netherBrick);
		world.setBlock(x, y + level, z + 2, netherBrick);
		world.setBlock(x, y + level, z - 2, netherBrick);
		world.setBlock(x + 1, y + level, z + 2, netherBrick);
		world.setBlock(x - 1, y + level, z + 2, netherBrick);
		world.setBlock(x + 1, y + level, z - 2, netherBrick);
		world.setBlock(x - 1, y + level, z - 2, netherBrick);
		world.setBlock(x + 2, y + level, z + 1, netherBrick);
		world.setBlock(x - 2, y + level, z + 1, netherBrick);
		world.setBlock(x + 2, y + level, z - 1, netherBrick);
		world.setBlock(x - 2, y + level, z - 1, netherBrick);
		world.setBlock(x + 2, y + level, z + 2, netherBrick);
		world.setBlock(x - 2, y + level, z + 2, netherBrick);
		world.setBlock(x + 2, y + level, z - 2, netherBrick);
		world.setBlock(x - 2, y + level, z - 2, netherBrick);
	}

	public int[] makeCylinder(World world, int posX, int height, int posZ, double radius, Block block, Random rand) {

		int[] intArray = null;

		for (double i = radius * 100.0D; i >= radius * -100.0D; i -= 1.0D) {
			int random = rand.nextInt(4);

			double x = Math.sqrt(radius * radius - i * i);
			double y = Math.sqrt(radius * radius - x * x);

			world.setBlock((int) Math.round(posX + x), height, (int) Math.round(posZ + y), block.blockID);

			world.setBlock((int) Math.round(posX - x), height, (int) Math.round(posZ - y), block.blockID);

			world.setBlock((int) Math.round(posX + x), height, (int) Math.round(posZ - y), block.blockID);

			world.setBlock((int) Math.round(posX - x), height, (int) Math.round(posZ + y), block.blockID);

			world.setBlock((int) Math.round(posX + y), height, (int) Math.round(posZ + x), block.blockID);

			world.setBlock((int) Math.round(posX - y), height, (int) Math.round(posZ - x), block.blockID);

			world.setBlock((int) Math.round(posX + y), height, (int) Math.round(posZ - x), block.blockID);

			world.setBlock((int) Math.round(posX - y), height, (int) Math.round(posZ + x), block.blockID);

			if (rand.nextInt(20) == 1) {
				if (random == 0) {
					setLava((int) Math.round(posX + x), height, (int) Math.round(posZ + y), world);
				}

				if (random == 1) {
					setLava((int) Math.round(posX - x), height, (int) Math.round(posZ + y), world);
				}

				if (random == 2) {
					setLava((int) Math.round(posX + x), height, (int) Math.round(posZ - y), world);
				}

				if (random == 3) {
					setLava((int) Math.round(posX - x), height, (int) Math.round(posZ - y), world);
				}

			}

		}

		world.setBlockToAir(posX, height, posZ);

		return intArray;
	}

	private void setLava(int x, int height, int z, World world) {
		world.setBlock(x, height, z, Block.lavaMoving.blockID);
	}

}