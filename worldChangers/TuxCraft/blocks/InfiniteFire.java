package worldChangers.TuxCraft.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InfiniteFire extends BlockFire {

	public InfiniteFire(int par1) {
		super(par1);
		this.setTickRandomly(true);
		this.enableStats = false;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {
			Block base = Block.blocksList[par1World.getBlockId(par2, par3 - 1,
					par4)];
			boolean flag = (base != null && base.isFireSource(par1World, par2,
					par3 - 1, par4,
					par1World.getBlockMetadata(par2, par3 - 1, par4), UP));

			if (!flag
					&& par1World.isRaining()
					&& (par1World.canLightningStrikeAt(par2, par3, par4)
							|| par1World.canLightningStrikeAt(par2 - 1, par3,
									par4)
							|| par1World.canLightningStrikeAt(par2 + 1, par3,
									par4)
							|| par1World.canLightningStrikeAt(par2, par3,
									par4 - 1) || par1World
								.canLightningStrikeAt(par2, par3, par4 + 1))) {
				par1World.setBlockToAir(par2, par3, par4);
			} else {
				int l = par1World.getBlockMetadata(par2, par3, par4);

				if (l < 15) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, l
							+ par5Random.nextInt(3) / 2, 4);
				}

				par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID,
						this.tickRate(par1World) + par5Random.nextInt(10));

				boolean flag1 = par1World.isBlockHighHumidity(par2, par3, par4);
				byte b0 = 0;

				if (flag1) {
					b0 = -50;
				}

				this.tryToCatchBlockOnFire(par1World, par2 + 1, par3, par4,
						300 + b0, par5Random, l, WEST);
				this.tryToCatchBlockOnFire(par1World, par2 - 1, par3, par4,
						300 + b0, par5Random, l, EAST);
				this.tryToCatchBlockOnFire(par1World, par2, par3 - 1, par4,
						250 + b0, par5Random, l, UP);
				this.tryToCatchBlockOnFire(par1World, par2, par3 + 1, par4,
						250 + b0, par5Random, l, DOWN);
				this.tryToCatchBlockOnFire(par1World, par2, par3, par4 - 1,
						300 + b0, par5Random, l, SOUTH);
				this.tryToCatchBlockOnFire(par1World, par2, par3, par4 + 1,
						300 + b0, par5Random, l, NORTH);

				for (int i1 = par2 - 1; i1 <= par2 + 1; ++i1) {
					for (int j1 = par4 - 1; j1 <= par4 + 1; ++j1) {
						for (int k1 = par3 - 1; k1 <= par3 + 4; ++k1) {
							if (i1 != par2 || k1 != par3 || j1 != par4) {
								int l1 = 100;

								if (k1 > par3 + 1) {
									l1 += (k1 - (par3 + 1)) * 100;
								}

								int i2 = this
										.getChanceOfNeighborsEncouragingFire(
												par1World, i1, k1, j1);

								if (i2 > 0) {
									int j2 = (i2 + 40 + par1World.difficultySetting * 7)
											/ (l + 30);

									if (flag1) {
										j2 /= 2;
									}

									if (j2 > 0
											&& par5Random.nextInt(l1) <= j2
											&& (!par1World.isRaining() || !par1World
													.canLightningStrikeAt(i1,
															k1, j1))
											&& !par1World.canLightningStrikeAt(
													i1 - 1, k1, par4)
											&& !par1World.canLightningStrikeAt(
													i1 + 1, k1, j1)
											&& !par1World.canLightningStrikeAt(
													i1, k1, j1 - 1)
											&& !par1World.canLightningStrikeAt(
													i1, k1, j1 + 1)) {
										int k2 = l + par5Random.nextInt(5) / 4;

										if (k2 > 15) {
											k2 = 15;
										}

										par1World.setBlock(i1, k1, j1,
												this.blockID, k2, 3);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void tryToCatchBlockOnFire(World par1World, int par2, int par3, int par4, int par5, Random par6Random, int par7, ForgeDirection face)
    {
        int j1 = 0;
        Block block = Block.blocksList[par1World.getBlockId(par2, par3, par4)];
        if (block != null)
        {
            j1 = block.getFlammability(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), face);
        }

        if (par6Random.nextInt(par5) < j1)
        {
            boolean flag = par1World.getBlockId(par2, par3, par4) == Block.tnt.blockID;

            if (par6Random.nextInt(par7 + 10) < 5 && !par1World.canLightningStrikeAt(par2, par3, par4))
            {
                int k1 = par7 + par6Random.nextInt(5) / 4;

                if (k1 > 15)
                {
                    k1 = 15;
                }

                par1World.setBlock(par2, par3, par4, this.blockID, k1, 3);
            }
            else
            {
                par1World.setBlockToAir(par2, par3, par4);
            }

            if (flag)
            {
                Block.tnt.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            }
        }
    }
	
	private int getChanceOfNeighborsEncouragingFire(World par1World, int par2, int par3, int par4)
	    {
	        byte b0 = 0;

	        if (!par1World.isAirBlock(par2, par3, par4))
	        {
	            return 0;
	        }
	        else
	        {
	            int l = this.getChanceToEncourageFire(par1World, par2 + 1, par3, par4, b0, WEST);
	            l = this.getChanceToEncourageFire(par1World, par2 - 1, par3, par4, l, EAST);
	            l = this.getChanceToEncourageFire(par1World, par2, par3 - 1, par4, l, UP);
	            l = this.getChanceToEncourageFire(par1World, par2, par3 + 1, par4, l, DOWN);
	            l = this.getChanceToEncourageFire(par1World, par2, par3, par4 - 1, l, SOUTH);
	            l = this.getChanceToEncourageFire(par1World, par2, par3, par4 + 1, l, NORTH);
	            return l;
	        }
	    }
}
