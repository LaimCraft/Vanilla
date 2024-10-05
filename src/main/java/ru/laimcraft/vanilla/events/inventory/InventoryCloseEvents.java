package ru.laimcraft.vanilla.events.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.MagicCraftingTable;

public class InventoryCloseEvents implements Listener {

    @EventHandler
    private void onInventoryCloseEvents(InventoryCloseEvent event) {
        magicCraftingTableClose(event);
    }

    private void magicCraftingTableClose(InventoryCloseEvent event) {
        if(!Vanilla.MagicCraftingTablePlayers.containsKey(event.getPlayer().getName())) return;
        MagicCraftingTable magicCraftingTable = Vanilla.MagicCraftingTablePlayers.get(event.getPlayer().getName());
        magicCraftingTable.close();
        Vanilla.MagicCraftingTablePlayers.remove(event.getPlayer().getName());
    }
}
