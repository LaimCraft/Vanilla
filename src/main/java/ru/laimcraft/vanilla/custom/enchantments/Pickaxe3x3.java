package ru.laimcraft.vanilla.custom.enchantments;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.Vanilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pickaxe3x3 implements Listener {

    public static final NamespacedKey tool3X3 = new NamespacedKey("vanilla", "tool3x3");
    public static final NamespacedKey tool3X3upper = new NamespacedKey("vanilla", "tool3x3upper");

    @EventHandler
    public void enchant() {

    }

    private static Location lc;
    @EventHandler
    private void serverLoad(ServerLoadEvent event) {
        lc = new Location(Bukkit.getWorld("world"), 589, 62, 32);
    }

    @EventHandler
    private void onDropItem(PlayerDropItemEvent event) {
        if(!event.getPlayer().getName().equals("limeworld")) return;
        if(event.getPlayer().getLocation().distance(lc) > 1) return;
        ItemStack item = event.getItemDrop().getItemStack();
        Set<NamespacedKey> keys = item.getPersistentDataContainer().getKeys();
        if(keys == null || keys.isEmpty()) return;
        for(NamespacedKey key : keys) {
            event.getPlayer().sendMessage(key.toString());
        }
    }

    public static void addLore(ItemMeta meta) {
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<>(1);
        lore.add(ChatColor.GOLD + "Добыча блоков 3x3");
        meta.setLore(lore);
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if (item == null || item.getType() == Material.AIR) return;
        Boolean tool3X3 = item.getPersistentDataContainer().get(Pickaxe3x3.tool3X3, PersistentDataType.BOOLEAN);
        if (tool3X3 == null || !tool3X3) return;
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
