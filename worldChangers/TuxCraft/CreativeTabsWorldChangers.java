package worldChangers.TuxCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsWorldChangers extends CreativeTabs {

	public CreativeTabsWorldChangers(String par2Str) {
		super(par2Str);
	}

	public ItemStack getIconItemStack() {
		return new ItemStack(WorldChangersCore.volcanicRock, 1, 0);
	}

}
