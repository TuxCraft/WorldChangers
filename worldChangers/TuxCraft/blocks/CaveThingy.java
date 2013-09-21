package worldChangers.TuxCraft.blocks;

import java.awt.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import worldChangers.TuxCraft.WorldChangersCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CaveThingy extends Block {

	public CaveThingy(int id, Material m, String s) {
		super(id, m);
		this.setUnlocalizedName(s);
	}

	public int getRenderType() {
		return WorldChangersCore.caveThingyRenderId;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int quantityDropped(Random par1Random) {
		return 0;
	}
	
	  @Override
		public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	    {
	    	
	            switch (par5)
	            {
	                case 0:
	                	par9 = 9;
	                	break;
	                case 1:
	                	par9 = 0;
	                	break;
	                case 2:
	                	par9 = 0;
	                    break;
	                case 3:
	                	par9 = 0;
	                    break;
	                case 4:
	                	par9 = 0;
	                    break;
	                case 5:
	                    par9 = 0;
	            }
	            
	            return par9;

	    }
}
