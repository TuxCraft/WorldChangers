package worldChangers.TuxCraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import worldChangers.TuxCraft.WorldChangersCore;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.UP;

public class WCBlock extends Block
{	

	private String textureName;
	private String propGroup;
	private String behaviorGroup;
	
	@SideOnly(Side.CLIENT)
	private Icon blockIconTop;
	
	public WCBlock(int id, Material m, String s)
	{
	
		super(id, m);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.textureName = s;
		this.setUnlocalizedName(s);
	}
	
	@Override
	public void registerIcons(IconRegister icon)
	{
	
		this.blockIcon = icon.registerIcon(WorldChangersCore.modid + ":"
				+ this.textureName);
		
		if(behaviorGroup == "pillar")
		{
			this.blockIconTop = icon.registerIcon(WorldChangersCore.modid + ":"
					+ this.textureName + "_top");
		}
	}
	
	public Block propertyGroup(String s, String s2)
	{
		PropertyGroups.propertyGroup(s, this);
		
		this.propGroup = s;
		this.behaviorGroup = s2;
		return this;
	}
	
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
		
        if (behaviorGroup == "pillar")
        {
        	
            switch (par5)
            {
                case 0:
                case 1:
                    par9 = 2;
                    break;
                case 2:
                case 3:
                    par9 = 4;
                    break;
                case 4:
                case 5:
                    par9 = 3;
            }
        }

        return par9;
    }
	
	@Override
	public int damageDropped(int par1)
    {
		if (behaviorGroup == "pillar")
        {
			return 2;
        }
		
		else
		{
			return 0;
		}
    }

	@Override
    protected ItemStack createStackedBlock(int par1)
    {
		if (behaviorGroup == "pillar")
        {
			return new ItemStack(this, 1, 2);
        }
		else
		{
			return new ItemStack(this, 1, 0);
		}
    }
    
    @Override
    public Icon getIcon(int par1, int par2)
    {
    	if (behaviorGroup == "pillar")
        {
            return par2 == 0 && (par1 == 1 || par1 == 0) ? this.blockIconTop : par2 == 1 ? this.blockIconTop : par2 == 2 && (par1 == 1 || par1 == 0) ? this.blockIconTop : (par2 == 3 && (par1 == 5 || par1 == 4) ? this.blockIconTop : (par2 == 4 && (par1 == 2 || par1 == 3) ? this.blockIconTop : this.blockIcon));
        }
    	
    	return this.blockIcon;
    }
    
    @Override
    public int getRenderType()
    {
        return 39;
    }
    
}
