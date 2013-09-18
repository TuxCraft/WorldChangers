package worldChangers.TuxCraft;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class CaveThingyRendender implements ISimpleBlockRenderingHandler {

	public static final int NUMBER_SECTIONS = 8;
	public static final float DEGREES_PER_SECTION = 360f / NUMBER_SECTIONS;
	public static final float RADIUS_SCALER = 1.8f;

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)  {
		
		System.out.println("G=hia");
		
		Tessellator t = Tessellator.instance;

		t.setBrightness((int) (16));
		t.setColorOpaque_F(1f, 1f, 1f);

		Icon stoneIcon = block.getBlockTextureFromSide(0);

		float[] pos = new float[] {0, 1};

		double textureX = stoneIcon.getMinU();
		double textureY = stoneIcon.getMinV();
		double textureXEnd = stoneIcon.getMaxU();
		double textureYEnd = stoneIcon.getMaxV();

		for (double i = 0; i < 360; i += DEGREES_PER_SECTION) {

			double r = pos[1] * RADIUS_SCALER;

			double j = (Math.cos(Math.toRadians(i)) * r) / 2;
			double k = (Math.sin(Math.toRadians(i)) * r) / 2;
			double j2 = (Math.cos(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;
			double k2 = (Math.sin(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;

			r = pos[0] * RADIUS_SCALER;

			double j3 = (Math.cos(Math.toRadians(i)) * r) / 2;
			double k3 = (Math.sin(Math.toRadians(i)) * r) / 2;
			double j4 = (Math.cos(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;
			double k4 = (Math.sin(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;

			t.addVertexWithUV(j2 + .5, 0 ,  k2 + .5, textureXEnd, textureY);
			t.addVertexWithUV(j + .5, 0 ,  k + .5, textureXEnd, textureYEnd);
			t.addVertexWithUV(j3 + .5, 0 + 1,  k3 + .5, textureX, textureYEnd);
			t.addVertexWithUV(j4 + .5, 0 + 1,  k4 + .5, textureX, textureY);

		}
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int metaData = world.getBlockMetadata(x, y, z);

		Tessellator t = Tessellator.instance;

		float light = block.getMixedBrightnessForBlock(world, x, y, z);
		light = world.getLightBrightnessForSkyBlocks(x, y, z, 1);
		t.setBrightness((int) (light - 20));
		t.setColorOpaque_F(1f, 1f, 1f);

		Icon stoneIcon;

		float[] pos;

		if (metaData <= 8) {
			stoneIcon = getIcon(world, x, y, z, block.blockID, renderer);
			pos = getPosIntStackU(world, x, y, z, block.blockID);
		} else {
			stoneIcon = getIconU(world, x, y, z, block.blockID, renderer);
			pos = getPosIntStack(world, x, y, z, block.blockID);
		}

		double textureX = stoneIcon.getMinU();
		double textureY = stoneIcon.getMinV();
		double textureXEnd = stoneIcon.getMaxU();
		double textureYEnd = stoneIcon.getMaxV();

		for (double i = 0; i < 360; i += DEGREES_PER_SECTION) {

			double r = pos[1] * RADIUS_SCALER;

			double j = (Math.cos(Math.toRadians(i)) * r) / 2;
			double k = (Math.sin(Math.toRadians(i)) * r) / 2;
			double j2 = (Math.cos(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;
			double k2 = (Math.sin(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;

			r = pos[0] * RADIUS_SCALER;

			double j3 = (Math.cos(Math.toRadians(i)) * r) / 2;
			double k3 = (Math.sin(Math.toRadians(i)) * r) / 2;
			double j4 = (Math.cos(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;
			double k4 = (Math.sin(Math.toRadians(i + DEGREES_PER_SECTION)) * r) / 2;

			t.addVertexWithUV(x + j2 + .5, y, z + k2 + .5, textureXEnd, textureY);
			t.addVertexWithUV(x + j + .5, y, z + k + .5, textureXEnd, textureYEnd);
			t.addVertexWithUV(x + j3 + .5, y + 1, z + k3 + .5, textureX, textureYEnd);
			t.addVertexWithUV(x + j4 + .5, y + 1, z + k4 + .5, textureX, textureY);

		}

		return true;
	}

	public float[] getPosIntStack(IBlockAccess world, int x, int y, int z, int id) {

		int curY = y - 1;

		while (world.getBlockId(x, curY, z) == id) {
			curY--;
		}

		int bottom = curY + 1;

		curY = y + 1;

		while (world.getBlockId(x, curY, z) == id) {
			curY++;
		}

		int top = curY - 1;

		int height = top - bottom + 1;

		curY = y - bottom;

		float[] value = new float[2];
		value[1] = ((float) curY / (float) height) * .5f;
		value[0] = ((float) (curY + 1) / (float) height) * .5f;

		return value;
	}

	public float[] getPosIntStackU(IBlockAccess world, int x, int y, int z, int id) {

		int curY = y - 1;

		while (world.getBlockId(x, curY, z) == id) {
			curY--;
		}

		int top = curY - 1;

		curY = y + 1;

		while (world.getBlockId(x, curY, z) == id) {
			curY++;
		}

		int bottom = curY;

		int height = top - bottom + 2;

		curY = y - bottom;

		float[] value = new float[2];
		value[1] = ((float) curY / (float) height) * .5f;
		value[0] = ((float) (curY + 1) / (float) height) * .5f;

		return value;
	}

	public Icon getIcon(IBlockAccess world, int x, int y, int z, int blockID, RenderBlocks renderer) {
		int curY = y;
		while (world.getBlockId(x, curY, z) == blockID) {
			curY--;
		}

		int id = world.getBlockId(x, curY, z);

		if (id != 0)
			return renderer.getBlockIcon(Block.blocksList[id]);
		else
			return renderer.getBlockIcon(Block.blocksList[1]);
	}

	public Icon getIconU(IBlockAccess world, int x, int y, int z, int blockID, RenderBlocks renderer) {

		int curY = y;

		while (world.getBlockId(x, curY, z) == blockID) {
			curY++;
		}

		int id = world.getBlockId(x, curY, z);

		if (id != 0)
			return renderer.getBlockIcon(Block.blocksList[id]);
		else
			return renderer.getBlockIcon(Block.blocksList[1]);
	}

	public boolean shouldRender3DInInventory() {
		return true;
	}

	public int getRenderId() {
		return WorldChangersCore.caveThingyRenderId;
	}

}
