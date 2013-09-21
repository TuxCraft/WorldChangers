package worldChangers.TuxCraft.items;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import worldChangers.TuxCraft.WorldChangersCore;
import worldChangers.TuxCraft.world.WorldGenGeode;

public class WCItem extends Item {
	private String textureName;

	public WCItem(int par1, String s) {
		super(par1);
		this.textureName = s;
		this.setUnlocalizedName(s);
		this.setMaxStackSize(64);
		this.setCreativeTab(WorldChangersCore.creativeTab);
	}
	
	@Override
	public void registerIcons(IconRegister icon) {
		this.itemIcon = icon.registerIcon(WorldChangersCore.modid + ":" + this.textureName);
	}
}