package worldChangers.TuxCraft.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import worldChangers.TuxCraft.WorldChangersCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlackLight extends WCBlock
{
	
	World world;
	
	public BlackLight(int id, String s)
	{
	
		super(id, Material.glass, s);
		this.setCreativeTab(null);
		this.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
	}	
	
	public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	world = par1World;
    }
    
    @Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
    	
            switch (par5)
            {
                case 0:
                	par9 = 1;
                	break;
                case 1:
                	par9 = 2;
                	break;
                case 2:
                	par9 = 3;
                    break;
                case 3:
                	par9 = 4;
                    break;
                case 4:
                	par9 = 5;
                    break;
                case 5:
                    par9 = 6;
            }
            
            return par9;

    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
        
        float f0 = 0.2F;
        float f1 = 0.2F;
        float f2 = 0.2F;
        float f3 = 0.8F;
        float f4 = 0.8F;
        float f5 = 0.8F;
        
        if (par1IBlockAccess.getBlockMetadata(x, y, z) == 1)
        {
            f1 = 0.4F;
            f4 = 1.0F;
            
            this.setBlockBounds(f0, f1, f2, f3, f4, f5);
            
            return;
        }
        
        if (par1IBlockAccess.getBlockMetadata(x, y, z) == 2)
        {
            f1 = 0.0F;
            f4 = 0.6F;
            
            this.setBlockBounds(f0, f1, f2, f3, f4, f5);
            
            return;
        }
        
        if (par1IBlockAccess.getBlockMetadata(x, y, z) == 3)
        {
            f2 = 0.5F;
            f5 = 1.0F;
            
            this.setBlockBounds(f0, f1, f2, f3, f4, f5);
            
            return;
        }
        
        if (par1IBlockAccess.getBlockMetadata(x, y, z) == 4)
        {
            f2 = 0.0F;
            f5 = 0.5F;
            
            this.setBlockBounds(f0, f1, f2, f3, f4, f5);
            
            return;
        }
        
        if (par1IBlockAccess.getBlockMetadata(x, y, z) == 5)
        {
            f0 = 0.5F;
            f3 = 1.0F;
            
            this.setBlockBounds(f0, f1, f2, f3, f4, f5);
            
            return;
        }
        
        if (par1IBlockAccess.getBlockMetadata(x, y, z) == 6)
        {
            f0 = 0.0F;
            f3 = 0.5F;
            
            this.setBlockBounds(f0, f1, f2, f3, f4, f5);
            
            return;
        }
        
        else
        {
        	this.setBlockBounds(f0, f1, f2, f3, f4, f5);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST,  true) ||
               par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST,  true) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH, true) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH, true) ||
               par1World.isBlockSolidOnSide(par2, par3 - 1, par4, DOWN, true)  ||
               par1World.isBlockSolidOnSide(par2, par3 + 1, par4, UP, true)    ||
               par1World.getBlockId(par2, par3 + 1, par4) == Block.fence.blockID ? true : false ||
               par1World.getBlockId(par2, par3 - 1, par4) == Block.fence.blockID ? true : false ||
               par1World.getBlockId(par2, par3 + 1, par4) == Block.netherFence.blockID ? true : false ||
               par1World.getBlockId(par2, par3 - 1, par4) == Block.netherFence.blockID ? true : false ||
               par1World.getBlockId(par2, par3 + 1, par4) == Block.cobblestoneWall.blockID ? true : false ||
               par1World.getBlockId(par2, par3 - 1, par4) == Block.cobblestoneWall.blockID ? true : false;
    }
    
    @Override
	public int damageDropped(int par1)
    {
        return 0;
    }

	@Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, 0);
    }
	
	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.1F, 0.1F, 0.1F, 0.9F, 0.9F, 0.9F);
    }
	
	public void onNeighborBlockChange(World par1World, int x, int y, int z, int par5)
    {
        System.out.println(par1World.getBlockMetadata(x, y, z));
        
        switch(par1World.getBlockMetadata(x, y, z))
        {
        	case 1:
        		if (par1World.getBlockMaterial(x, y + 1, z) == Material.air) 
        		{
        			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
                    par1World.setBlockToAir(x, y, z);
        		}
        		break;
        		
        	case 2:
        		if (par1World.getBlockMaterial(x, y - 1, z) == Material.air) 
        		{
        			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
                    par1World.setBlockToAir(x, y, z);
        		}
        		break;
        		
        	case 3:
        		if (par1World.getBlockMaterial(x, y, z + 1) == Material.air) 
        		{
        			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
                    par1World.setBlockToAir(x, y, z);
        		}
        		break;
        		
        	case 4:
        		if (par1World.getBlockMaterial(x, y, z - 1) == Material.air) 
        		{
        			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
                    par1World.setBlockToAir(x, y, z);
        		}
        		break;
        		
        	case 5:
        		if (par1World.getBlockMaterial(x + 1, y, z) == Material.air) 
        		{
        			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
                    par1World.setBlockToAir(x, y, z);
        		}
        		break;
        		
        	case 6:
        		if (par1World.getBlockMaterial(x - 1, y, z) == Material.air) 
        		{
        			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
                    par1World.setBlockToAir(x, y, z);
        		}
        		break;
        		
        	default:
        		break;
        }
    }
	
	/**
	 * This is to remind me to add particle effects
	 * 
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        double d0 = (double)((float)par2 + 0.5F);
        double d1 = (double)((float)par3 + 0.7F);
        double d2 = (double)((float)par4 + 0.5F);
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
            par1World.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 2)
        {
            par1World.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 3)
        {
            par1World.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 4)
        {
            par1World.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }*/
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return WorldChangersCore.blackLightItem.itemID;
    }
	
	@SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return WorldChangersCore.blackLightItem.itemID;
    }
}
