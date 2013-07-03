package worldChangers.TuxCraft.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class itemWorldChangers extends Item
{
  private String textureName;

  public itemWorldChangers(int par1, String s)
  {
    super(par1);
    this.textureName = s;
    this.setUnlocalizedName(s);
    this.setMaxStackSize(64);
  }

  @Override
  public void registerIcons(IconRegister icon)
  {
    this.itemIcon = icon.registerIcon("World Changers:" + this.textureName);
  }
}