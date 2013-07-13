package worldChangers.TuxCraft;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {

		/**
		 * if (fuel.field_77993_c == WorldChangersCore.ash.field_77779_bT) {
		 * return 400; }
		 */

		return 0;
	}
}
