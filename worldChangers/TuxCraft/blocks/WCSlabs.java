package worldChangers.TuxCraft.blocks;

import java.util.Random;

import worldChangers.TuxCraft.WorldChangersCore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class WCSlabs extends BlockHalfSlab
{

	private String propGroup;
	private String behaviorGroup;
	private Block block;
	
	public WCSlabs(int par1, Block block, boolean par2, String s)
	{
	
		super(par1, par2, block.blockMaterial);
		this.block = block;
		this.setCreativeTab(WorldChangersCore.creativeTab);
		this.setUnlocalizedName(s);
	}
	
	public Block propertyGroup(String s, String s2)
	{
		PropertyGroups.propertyGroup(s, this);
		
		this.propGroup = s;
		this.behaviorGroup = s2;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
    {
        return this.block.getIcon(par1, 0);
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) { }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
	
	protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this);
    }
	
	public String getFullSlabName(int par1)
    {

        return "slab";
    }
	
}
