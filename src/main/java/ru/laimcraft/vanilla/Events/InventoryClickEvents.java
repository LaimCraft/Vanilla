package ru.laimcraft.vanilla.Events;

import org.bukkit.event.inventory.InventoryClickEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class InventoryClickEvents {
    private Core core;
    private InventoryClickEvent event;
    public InventoryClickEvents(Core core, InventoryClickEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}
