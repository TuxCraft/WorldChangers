package worldChangers.TuxCraft.blocks;

import worldChangers.TuxCraft.WorldChangersCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.StepSound;


public class WCStairs extends BlockStairs
{

	private String propGroup;
	private String behaviorGroup;
	
	public WCStairs(int par1, Block par2Block, int par3, String s)
	{
		super(par1, par2Block, par3);
		this.setUnlocalizedName(s);
		this.setCreativeTab(WorldChangersCore.creativeTab);
	}	
	
	public Block propertyGroup(String s, String s2)
	{
		PropertyGroups.propertyGroup(s, this);
		
		this.propGroup = s;
		this.behaviorGroup = s2;
		return this;
	}
	
}
