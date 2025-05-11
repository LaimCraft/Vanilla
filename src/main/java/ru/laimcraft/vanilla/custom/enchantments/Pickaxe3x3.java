package ru.laimcraft.vanilla.custom.enchantments;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.spi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Pickaxe3x3 implements Listener {

    public static Enchantment pickaxe3x3Enchantment;

    @EventHandler
    private void onLoadServer(ServerLoadEvent event) {
        pickaxe3x3Enchantment = Registry.ENCHANTMENT.get(spi.pickaxe3x3Key);
    }

    public static void register() {

    }

    @EventHandler
    private void anvilEvent(PrepareAnvilEvent event) {
        
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if(!item.containsEnchantment(pickaxe3x3Enchantment)) return;
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

        if(min >= blockY || max <= blockY) {
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
        for(Block b : blocks) {
            if(Vanilla.blockInventory.getInventoryBlocks().contains(b.getType())) continue;
            b.breakNaturally(item, true, true);
        }
    }

}
