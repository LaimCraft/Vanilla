package ru.laimcraft.vanilla.events.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class InventoryClickEvents {
    private Core core;
    private InventoryClickEvent event;
    public InventoryClickEvents(Core core, InventoryClickEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}
