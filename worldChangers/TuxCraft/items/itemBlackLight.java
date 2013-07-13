package worldChangers.TuxCraft.items;

import worldChangers.TuxCraft.WorldChangersCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class itemBlackLight extends ItemReed
{

	private String textureName;
	
	public itemBlackLight(int par1, String s)
	{
	
		super(par1, WorldChangersCore.blackLight);
		this.textureName = s;
	    this.setUnlocalizedName(s);
	    this.setMaxStackSize(64);
	}	
	
	 @Override
	 public void registerIcons(IconRegister icon)
	 {
	   this.itemIcon = icon.registerIcon(WorldChangersCore.modid + ":" + this.textureName);
	 }
	
}
