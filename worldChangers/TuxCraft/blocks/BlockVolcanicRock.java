package worldChangers.TuxCraft.blocks;


import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import worldChangers.TuxCraft.WorldChangersCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVolcanicRock extends WCBlock
{
	
	private String textureName;
	
	public BlockVolcanicRock(int id, String s)
	{
	
		super(id, Material.rock, s);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.textureName = s;
		this.setUnlocalizedName(s);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
	
		if (world.getBlockMaterial(x, y + 1, z) == Material.air)
		{
			if (rand.nextInt(1000) == 0)
			{
				double d1 = x + rand.nextFloat();
				double d2 = y + this.maxY;
				double d3 = z + rand.nextFloat();
				world.spawnParticle("lava", d1, d2, d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	public int idDropped(int par1, Random rand, int par3)
	{
	
		if (rand.nextInt(4) == 0)
		{
			return WorldChangersCore.ash.itemID;
		}
		
		return WorldChangersCore.volcanicRock.blockID;
	}
	
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        if (side == UP)
        {
            return true;
        }
        
        return false;
    }
}
