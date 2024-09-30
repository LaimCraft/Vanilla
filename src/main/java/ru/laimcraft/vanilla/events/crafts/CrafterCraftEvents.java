package ru.laimcraft.vanilla.events.crafts;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;
import ru.laimcraft.vanilla.components.namespacedkeys.CraftKeys;

public class CrafterCraftEvents implements Listener {

    @EventHandler
    private void onCrafterCraftEvents(CrafterCraftEvent event) {
        if(event.getResult().getType() != Material.CRAFTING_TABLE) return;
        if(event.getRecipe().getKey() != CraftKeys.MagicCraftingTable.getValue()) return;
        event.setCancelled(true);
    }
}
