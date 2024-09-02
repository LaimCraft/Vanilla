package ru.laimcraft.vanilla.Events;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class BlockPlaceEvents {
    private Core core;
    private BlockPlaceEvent event;
    public BlockPlaceEvents(Core core, BlockPlaceEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
        if(core.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            core.chests.create(event.getBlock().getX() + ":" + event.getBlock().getY() + ":" + event.getBlock().getZ(), event.getPlayer().getName());
        }
    }
}
