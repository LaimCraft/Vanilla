package ru.laimcraft.vanilla.Events;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class BlockBreakEvents {
    private Core core;
    private BlockBreakEvent event;
    public BlockBreakEvents(Core core, BlockBreakEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
        if(core.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            String owner = core.chests.getChestOwner(event.getBlock().getX() + ":" + event.getBlock().getY() + ":" + event.getBlock().getZ());
            if(owner == null || owner.isEmpty()) return;
            if(owner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
            if(!owner.equalsIgnoreCase(event.getPlayer().getName())) {event.setCancelled(true); return;}
            core.chests.removeChest(event.getBlock().getX() + ":" + event.getBlock().getY() + ":" + event.getBlock().getZ());
        }
    }
}
