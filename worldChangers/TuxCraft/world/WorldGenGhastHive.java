package worldChangers.TuxCraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldChangers.TuxCraft.WorldChangersCore;
import worldChangers.TuxCraft.WorldGenUtils;

public class WorldGenGhastHive extends WorldGenerator
{

    public static final WeightedRandomChestContent[] ghastHiveSmallChestContents = new WeightedRandomChestContent[] {
                                                                                 new WeightedRandomChestContent(Item.blazePowder.itemID, 0, 1, 3, 6),
                                                                                 new WeightedRandomChestContent(Item.blazeRod.itemID, 0, 1, 1, 4),
                                                                                 new WeightedRandomChestContent(Item.netherQuartz.itemID, 0, 1, 5, 10),
                                                                                 new WeightedRandomChestContent(new ItemStack(Item.skull, 1, 1), 1, 2, 3),
                                                                                 new WeightedRandomChestContent(Item.gunpowder.itemID, 0, 1, 6, 8),
                                                                                 new WeightedRandomChestContent(Item.ghastTear.itemID, 0, 1, 5, 10)
                                                                                 };

    public static final int                          MAX_SIZE                    = 30;
    public static final int                          MIN_SIZE                    = 15;

    public WorldGenGhastHive(boolean b)
    {
        super(b);
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {

        int ground = 0, top = 0, raidus = 0;

        for (int i = 126; i > 10; i--)
        {
            if (world.getBlockId(x, i, z) == 0)
            {
                top = i;
                break;
            }
        }

        for (int i = top; i > 0; i--)
        {
            if (world.getBlockId(x, i, z) != 0)
            {
                ground = i;
                break;
            }
        }

        raidus = (top - ground) / 2;
        if (raidus > MAX_SIZE)
            raidus = MAX_SIZE;
        if (raidus < MIN_SIZE)
            raidus = MIN_SIZE;

        drawSphereWithLava(x, top - raidus, z, raidus,
                WorldChangersCore.ghastHive.blockID, rand, world); // IRON
        makeHoles(x, top - raidus, z, raidus, world);
        WorldGenUtils.clearSphere(x, top - raidus, z, raidus - 2, world);
        drawRandomSphere(x, top - raidus, z, raidus - 1, rand, world);

        int i;
        for (i = 0; i < raidus; i++)
        {

        }

        WorldGenUtils.drawCube(x, ground + i - 1, z, 5, 1, 5,
                WorldChangersCore.ghastHive.blockID, world);
        world.setBlock(x, ground + i, z, Block.chest.blockID); // Chest

        TileEntityChest tileentitychest = (TileEntityChest) world
                .getBlockTileEntity(x, ground + i, z);

        if (tileentitychest != null)
        {
            WCChestGenHooks info = WCChestGenHooks
                    .getInfo(WCChestGenHooks.VOLCANO);
            WeightedRandomChestContent.generateChestContents(rand,
                    info.getItems(rand), tileentitychest, info.getCount(rand));
        }

        return true;
    }

    public void drawRandomSphere(int x, int y, int z, double r, Random rand,
            World world)
    {
        for (double i = -r; i < r; i++)
            for (double j = -r; j < r; j++)
                for (double k = -r; k < r; k++)
                    if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)
                            + Math.pow(k, 2)) <= r)
                    {
                        int random = rand.nextInt(2500);
                        if (random == 1)
                        {
                            world.setBlock((int) i + x, (int) j + y, (int) k + z, Block.mobSpawner.blockID);
                            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world.getBlockTileEntity((int) i + x, (int) j + y, (int) k + z);
                            if (tileentitymobspawner != null)
                            {
                                tileentitymobspawner.getSpawnerLogic().setMobID("Ghast");
                            }
                        }
                        else if (random <= 19)
                        {
                            WorldGenUtils.drawCube((int) i + x - 2,
                                    (int) j + y, (int) k + z - 2, 4, 1, 4,
                                    WorldChangersCore.ghastHive.blockID, world);
                            int random2 = rand.nextInt(12);
                            if (random2 == 1)
                            {
                                world.setBlock((int) i + x, (int) j + y + 1,
                                        (int) k + z, Block.chest.blockID);
                                TileEntityChest tileentitychest = (TileEntityChest) world
                                        .getBlockTileEntity((int) i + x,
                                                (int) j + y + 1, (int) k + z);

                                if (tileentitychest != null)
                                {
                                    WCChestGenHooks info = WCChestGenHooks.getInfo(WCChestGenHooks.HIVE_SMALL);
                                    WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), tileentitychest, info.getCount(rand));
                                }
                            }
                        }
                    }
    }

    public static void drawSphereWithLava(int x, int y, int z, double r,
            int block, Random rand, World world)
    {
        for (double i = -r; i < r; i++)
            for (double j = -r; j < r; j++)
                for (double k = -r; k < r; k++)
                {
                    if (Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2)
                            + Math.pow(k, 2)) <= r)
                    {
                        int random = rand.nextInt(200);
                        if (random <= 3)
                            world.setBlock((int) i + x, (int) j + y, (int) k
                                    + z, Block.sponge.blockID);
                        else if (random <= 4)
                        {
                            for (int a = 1; a <= (128 - y - j); a++)
                            {
                                if (world.isAirBlock((int) i + x, (int) j + y
                                        + a, (int) k + z))
                                {
                                    world.setBlock((int) i + x,
                                            (int) j + y + a, (int) k + z,
                                            Block.web.blockID);
                                }
                            }

                        }
                        else if (random <= 6)
                        {
                            world.setBlock((int) i + x, (int) j + y, (int) k
                                    + z, Block.mobSpawner.blockID, 0, 2);
                            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world
                                    .getBlockTileEntity((int) i + x, (int) j
                                            + y, (int) k + z);
                            if (tileentitymobspawner != null)
                            {
                                tileentitymobspawner.getSpawnerLogic()
                                        .setMobID("Ghast");
                            }
                            else
                            {
                                System.err
                                        .println("Failed to fetch mob spawner entity at ("
                                                + (i + x)
                                                + ", "
                                                + (j + y)
                                                + ", " + (k + z) + ")");
                            }
                        }
                        else
                            world.setBlock((int) i + x, (int) j + y, (int) k
                                    + z, block);
                    }
                }
    }

    public static void makeHoles(int x, int y, int z, double r, World world)
    {
        for (double i = -r; i < r; i++)
            for (double j = -r; j < r; j++)
                for (double k = -r; k < r; k++)
                {
                    if (world.getBlockId((int) i + x, (int) j + y, (int) k + z) == Block.sponge.blockID)
                    {
                        WorldGenUtils.clearCircle((int) i + x, (int) j + y,
                                (int) k + z, 5, world);
                    }
                }
    }
}
