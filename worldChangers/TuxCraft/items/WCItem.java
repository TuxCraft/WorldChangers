package worldChangers.TuxCraft.items;

import worldChangers.TuxCraft.WorldChangersCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class WCItem extends Item
{
  private String textureName;

  public WCItem(int par1, String s)
  {
    super(par1);
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