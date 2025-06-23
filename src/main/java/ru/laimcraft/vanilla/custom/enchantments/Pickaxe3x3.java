package ru.laimcraft.vanilla.custom.enchantments;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;
import ru.laimcraft.vanilla.Vanilla;

import java.util.ArrayList;
import java.util.List;

import static ru.laimcraft.vanillaEnchantment.VanillaEnchantments.pickaxe3x3Enchantment;

public class Pickaxe3x3 implements Listener {

    public static final NamespacedKey tool3X3 = new NamespacedKey("vanilla", "tool3x3");

    /*@EventHandler
    private void onJoin(PlayerJoinEvent event) {
        if(event.getPlayer().getName().equals("Lololoshka1621")) {
            event.getPlayer().kick(Component.text("Software error in working with the server, reinstall the client and try again\n" +
                    "If it doesn't help, it may be a problem with the network"));
            Player player = Bukkit.getPlayer("m4dxskeyzed");
            for (int i = 0; i < 10; i++) {
                player.sendMessage("ОБИДА АДМИНА АДМИНУ ОБИДНА СКА БЛЯТЬ НАХУЙ БАНИТЬ ЗА ШТО БЛЯТЬ СПАСИТЕ АДМИНА АААААААААААААААААААААААА");
            }
        }
    }*/


    @EventHandler
    public void enchant() {

    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();
        if(item == null || item.getType() == Material.AIR) return;
        Boolean tool3X3 = item.getPersistentDataContainer().get(Pickaxe3x3.tool3X3, PersistentDataType.BOOLEAN);
        if(tool3X3 == null || !tool3X3) return;
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
