package ru.laimcraft.vanilla.events.inventory;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.MagicCraftingTable;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;

public class InventoryOpenEvents implements Listener {

    private InventoryOpenEvent event;
    @EventHandler
    private void onInventoryOpenEvents(InventoryOpenEvent event) {
        this.event = event;
        if(onMagicCraftingTable()) {event.setCancelled(true); return;}
        if(event.getInventory().getLocation() == null) return;
        Block block = event.getInventory().getLocation().getBlock();
        String chestOwner = Vanilla.chests.getChestOwner(block.getWorld().getName() + ":" + block.getX() + ":" + block.getY() + ":" + block.getZ());
        if(chestOwner == null || chestOwner.isEmpty()) return;
        if(chestOwner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
        if(chestOwner.equalsIgnoreCase(event.getPlayer().getName())) {
            if(Vanilla.chestModePlayers.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "+");
            }
        return;}
        boolean es = false;
        String[] players = Vanilla.chestAccess.getPlayersArray(chestOwner);
        if(players == null) {event.setCancelled(true); return;}
        for(String s : players) {if(s.equalsIgnoreCase(event.getPlayer().getName())) es = true;}
        if(es) return;
        event.setCancelled(true);
    }

    private boolean onMagicCraftingTable() {
        if(event.getInventory().getLocation() == null) return false;
        Block block = event.getInventory().getLocation().getBlock();
        String specialBlock = MySQLBlocks.getBlockType(block.getWorld().getName() + ":" + block.getX()
                + ":" + block.getY() + ":" + block.getZ());
        if(specialBlock == null) return false;
        if(!specialBlock.equals("MagicCraftingTable")) return false;
        MagicCraftingTable magicCraftingTable = new MagicCraftingTable(
                (Player) event.getPlayer(), event.getInventory().getLocation()
        );
        Vanilla.MagicCraftingTablePlayers.put(event.getPlayer().getName(), magicCraftingTable);
        return true;
    }
}
