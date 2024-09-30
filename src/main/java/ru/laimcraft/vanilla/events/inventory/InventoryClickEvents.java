package ru.laimcraft.vanilla.events.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;

public class InventoryClickEvents implements Listener {

    private InventoryClickEvent event;
    @EventHandler
    private void onInventoryClickEvents(InventoryClickEvent event) {
        this.event = event;
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
