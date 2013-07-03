package worldChangers.TuxCraft.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.io.PrintStream;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldChangersGenerator
  implements IWorldGenerator
{
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
  {
    switch (world.provider.dimensionId)
    {
    case 0:
      generateOverworld(world, random, chunkX * 16, chunkZ * 16);
    case 1:
      generateEnd(world, random, chunkX * 16, chunkZ * 16);
    case -1:
      generateNether(world, random, chunkX * 16, chunkZ * 16);
    }
  }

  public void generateOverworld(World world, Random random, int chunkX, int chunkZ)
  {
    generateVolcano(world, random, chunkX, chunkZ);
  }

  private boolean generateVolcano(World world, Random random, int chunkX, int chunkZ)
  {
    int x = chunkX * 16 + random.nextInt(16);
    int z = chunkZ * 16 + random.nextInt(16);
    getHighestBlock(world, x, z);

    if ((world.getBiomeGenForCoords(x, z).biomeName != "Ocean") && (world.getBiomeGenForCoords(x, z).biomeName != "River") && (world.getBiomeGenForCoords(x, z).biomeName != "Swampland") && (world.getBiomeGenForCoords(x, z).biomeName != "Beach") && (world.getBiomeGenForCoords(x, z).biomeName != "JungleHills"))
    {
      int par1 = random.nextInt(100);

      if (par1 == 1)
      {
        double baseRadius = (random.nextInt(6) + 1) * 5;
        double mouthRadius = random.nextInt(5) + 2;

        int[] heights = { getHighestBlock(world, (int)(x + baseRadius), z), getHighestBlock(world, (int)(x - baseRadius), z), getHighestBlock(world, x, (int)(z + baseRadius)), getHighestBlock(world, x, (int)(z - baseRadius)), getHighestBlock(world, x, z) };

        System.out.println("Volcono spawned at " + x + " " + getMinimum(heights) + " " + z + ". In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ". Best TP coords are " + x + " " + 250 + " " + z + ". ");

        new WorldGenVolcano(true, baseRadius, mouthRadius).generate(world, random, x, getMinimum(heights), z);
      }

    }

    return true;
  }

  private int getMinimum(int[] heights)
  {
    int y = 250;

    for (int i = 0; i < heights.length; i++)
    {
      if (heights[i] < y)
      {
        y = heights[i];
      }
    }

    return y;
  }

  public void generateEnd(World world, Random random, int chunkX, int chunkZ)
  {
  }

  public void generateNether(World world, Random random, int chunkX, int chunkZ)
  {
  }

  public int getHighestBlock(World world, int x, int z)
  {
    for (int i = 128; i > 0; i--)
    {
      if (world.getBlockId(x, i, z) != 0)
      {
        return i;
      }
    }

    return 0;
  }
}