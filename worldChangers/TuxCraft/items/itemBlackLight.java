package worldChangers.TuxCraft.items;

import worldChangers.TuxCraft.WorldChangersCore;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class itemBlackLight extends WCItem
{

	private int spawnID;

	public itemBlackLight(int par1, String s)
	{
	
		super(par1, s);
		this.spawnID = WorldChangersCore.blackLight.blockID;
	}	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
            if (par3World.canPlaceEntityOnSide(this.spawnID, par4, par5, par6, false, par7, (Entity)null, par1ItemStack))
            {
                Block block = Block.blocksList[this.spawnID];
                int j1 = block.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);

                if (par3World.setBlock(par4, par5, par6, this.spawnID, j1, 3))
                {
                    if (par3World.getBlockId(par4, par5, par6) == this.spawnID)
                    {
                        Block.blocksList[this.spawnID].onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer, par1ItemStack);
                        Block.blocksList[this.spawnID].onPostBlockPlaced(par3World, par4, par5, par6, j1);
                    }

                    par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --par1ItemStack.stackSize;
                }

            }
            
            return true;
    }
	
}
