package worldChangers.TuxCraft;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelHandler
  implements IFuelHandler
{
  public int getBurnTime(ItemStack fuel)
  {
    /**if (fuel.field_77993_c == WorldChangersCore.ash.field_77779_bT)
    {
      return 400;
    }*/

    return 0;
  }
}