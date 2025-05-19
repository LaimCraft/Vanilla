package ru.laimcraft.vanilla.custom.enchantments;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import ru.laimcraft.vanilla.Vanilla;

import java.util.ArrayList;
import java.util.List;

import static ru.laimcraft.vanillaEnchantment.VanillaEnchantments.pickaxe3x3Enchantment;

public class Pickaxe3x3 implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if(item == null || item.getType() == Material.AIR) return;
        if (!item.containsEnchantment(pickaxe3x3Enchantment)) return;
        event.setCancelled(true);
        Location location = event.getPlayer().getLocation();
        Block block = event.getBlock();
        int y = location.getBlockY();
        int blockX = block.getLocation().getBlockX();
        int blockY = block.getLocation().getBlockY();
        int blockZ = block.getLocation().getBlockZ();
        BlockFace blockFace = event.getPlayer().getFacing();
        int min = y - 1;
        int max = min + 4;

        World world = event.getPlayer().getWorld();
        List<Block> blocks = new ArrayList<>(9);

        if (min >= blockY || max <= blockY) {
            blocks.add(block);
            blocks.add(world.getBlockAt(blockX, blockY, blockZ + 1));
            blocks.add(world.getBlockAt(blockX, blockY, blockZ - 1));

            blocks.add(world.getBlockAt(blockX + 1, blockY, blockZ));
            blocks.add(world.getBlockAt(blockX + 1, blockY, blockZ + 1));
            blocks.add(world.getBlockAt(blockX + 1, blockY, blockZ - 1));

            blocks.add(world.getBlockAt(blockX - 1, blockY, blockZ));
            blocks.add(world.getBlockAt(blockX - 1, blockY, blockZ + 1));
            blocks.add(world.getBlockAt(blockX - 1, blockY, blockZ - 1));
            blocksBreak(blocks, item);
            return;
        } else {
            switch (blockFace) {
                case WEST, EAST:
                    blocks.add(block);
                    blocks.add(world.getBlockAt(blockX, blockY, blockZ + 1));
                    blocks.add(world.getBlockAt(blockX, blockY, blockZ - 1));

                    blocks.add(world.getBlockAt(blockX, blockY + 1, blockZ));
                    blocks.add(world.getBlockAt(blockX, blockY + 1, blockZ + 1));
                    blocks.add(world.getBlockAt(blockX, blockY + 1, blockZ - 1));

                    blocks.add(world.getBlockAt(blockX, blockY - 1, blockZ));
                    blocks.add(world.getBlockAt(blockX, blockY - 1, blockZ + 1));
                    blocks.add(world.getBlockAt(blockX, blockY - 1, blockZ - 1));
                    blocksBreak(blocks, item);
                    return;
                case NORTH, SOUTH:
                    blocks.add(block);
                    blocks.add(world.getBlockAt(blockX, blockY + 1, blockZ));
                    blocks.add(world.getBlockAt(blockX, blockY - 1, blockZ));

                    blocks.add(world.getBlockAt(blockX + 1, blockY, blockZ));
                    blocks.add(world.getBlockAt(blockX + 1, blockY + 1, blockZ));
                    blocks.add(world.getBlockAt(blockX + 1, blockY - 1, blockZ));

                    blocks.add(world.getBlockAt(blockX - 1, blockY, blockZ));
                    blocks.add(world.getBlockAt(blockX - 1, blockY + 1, blockZ));
                    blocks.add(world.getBlockAt(blockX - 1, blockY - 1, blockZ));
                    blocksBreak(blocks, item);
                    return;
                default:
                    return;
            }
        }
    }

    private void blocksBreak(List<Block> blocks, ItemStack item) {
        for (Block b : blocks) {
            if (Vanilla.blockInventory.getInventoryBlocks().contains(b.getType())) continue;
            b.breakNaturally(item, true, true);
        }
    }

}
