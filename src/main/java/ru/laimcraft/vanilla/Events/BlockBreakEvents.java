package ru.laimcraft.vanilla.Events;

import org.bukkit.event.block.BlockBreakEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class BlockBreakEvents {
    private Core core;
    private BlockBreakEvent event;
    public BlockBreakEvents(Core core, BlockBreakEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}
