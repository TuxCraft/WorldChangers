package worldChangers.TuxCraft;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import worldChangers.TuxCraft.world.WorldGenCrater;
import worldChangers.TuxCraft.world.WorldGenGhastHive;
import worldChangers.TuxCraft.world.WorldGenVolcano;

public class EventHookContainerClass
{
    
    // Set back to 90
    public static final int spawnRate = 90;
    // Note: craters spawn 2x as much
    
    @ForgeSubscribe
    public void decorateChunk(DecorateBiomeEvent.Post event)
    {
        switch (event.world.provider.dimensionId)
        {
            case 0: // Overworld generation goes here
                generateOverworld(event.world, event.rand, event.chunkX, event.chunkZ);
                break;
                
            case 1: // End generation goes here
                break;
                
            case -1: // Nether generation goes here
                generateNether(event.world, event.rand, event.chunkX, event.chunkZ);
                break;
        }
        
    }
    
    private boolean generateOverworld(World world, Random random, int chunkX, int chunkZ)
    {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        int y = WorldGenUtils.getHighestBlock(world, x, z);

        if ((world.getBiomeGenForCoords(x, z).minHeight > 0.0F) && (world.getBiomeGenForCoords(x, z).maxHeight < 0.4F))
        {
            if (random.nextInt(spawnRate) == 1)
            {
                long start = System.currentTimeMillis();
                new WorldGenVolcano(true).generate(world, random, x, y, z);
                System.out.println("[World Changers] Volcono spawned around " + x + " " + y + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
            }

            if (random.nextInt((int) (spawnRate / 2)) == 1)
            {
                long start = System.currentTimeMillis();
                new WorldGenCrater().generate(world, random, x, y, z);
                System.out.println("[World Changers] Crater spawned around " + x + " " + y + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
            }

        }

        return true;
    }
    
    public void generateNether(World world, Random random, int chunkX, int chunkZ)
    {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        int y = WorldGenUtils.getHighestBlock(world, x, z);

        if (random.nextInt(spawnRate) == 1)
        {
            long start = System.currentTimeMillis();
            new WorldGenGhastHive(true).generate(world, random, x, y, z);
            System.out.println("[World Changers] Ghasthive spawned around " + x + " " + y + " " + z + " In biome " + String.valueOf(world.getBiomeGenForCoords(x, z).biomeName) + ": Took " + (System.currentTimeMillis() - start) + " milliseconds");
        }
    }

}
