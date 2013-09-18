package worldChangers.TuxCraft.blocks;

import java.awt.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import worldChangers.TuxCraft.WorldChangersCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CaveThingy extends Block {

	public CaveThingy(int id, Material m, String s) {
		super(id, m);
		this.setUnlocalizedName(s);
	}

	public int getRenderType() {
		return WorldChangersCore.caveThingyRenderId;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int quantityDropped(Random par1Random) {
		return 0;
	}

}
