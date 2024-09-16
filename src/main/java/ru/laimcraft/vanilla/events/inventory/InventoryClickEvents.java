package ru.laimcraft.vanilla.events.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;

public class InventoryClickEvents {
    private Core core;
    private InventoryClickEvent event;
    public InventoryClickEvents(Core core, InventoryClickEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
        if(isNoMovement()) {event.setCancelled(true); return;}
    }

    private boolean isNoMovement() {
        if(event.getCurrentItem() == null) return false;
        if(event.getCurrentItem().getItemMeta() == null) return false;
        if(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(ItemKey.NoMovement.getValue(), PersistentDataType.BOOLEAN) == null) return false;
        boolean noMovement = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(ItemKey.NoMovement.getValue(), PersistentDataType.BOOLEAN);
        if(noMovement) return true;
        return false;
    }
}
