package worldChangers.TuxCraft.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.StepSound;

public class PropertyGroups
{
	
	public static void propertyGroup(String s, Block b)
	{
	
		if (s == "stone")
		{
			b.setHardness(1.5F);
			b.setResistance(10.0F);
			b.setStepSound(new StepSound("stone", 1.0F, 1.0F));
		}
		
		if (s == "lightSource")
		{
			b.setHardness(0.3F);
			b.setLightValue(1.0F);
			b.setStepSound(new StepSound("stone", 1.0F, 1.0F));
			b.setLightValue(1.0F);
		}
		
		if (s == "glowingStone")
		{
			b.setHardness(1.5F);
			b.setResistance(10.0F);
			b.setStepSound(new StepSound("stone", 1.0F, 1.0F));
			b.setLightValue(0.5F);
		}
		
	}
}
