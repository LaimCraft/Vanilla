package ru.laimcraft.vanilla.events;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryOpenEvent;
import ru.laimcraft.vanilla.Core;

public class InventoryOpenEvents {
    private Core core;
    private InventoryOpenEvent event;
    public InventoryOpenEvents(Core core, InventoryOpenEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(event.getInventory().getLocation() == null) return;
        Block block = event.getInventory().getLocation().getBlock();
        String chestOwner = core.chests.getChestOwner(block.getX() + ":" + block.getY() + ":" + block.getZ());
        if(chestOwner == null || chestOwner.isEmpty()) return;
        if(chestOwner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
        if(chestOwner.equalsIgnoreCase(event.getPlayer().getName())) {
            if(core.chestModePlayers.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "+");
            }
        return;}
        boolean es = false;
        String[] players = core.chestAccess.getPlayersArray(chestOwner);
        if(players == null) {event.setCancelled(true); return;}
        for(String s : players) {if(s.equalsIgnoreCase(event.getPlayer().getName())) es = true;}
        if(es) return;
        event.setCancelled(true);
    }
}
