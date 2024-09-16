package ru.laimcraft.vanilla.events.crafts;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.CrafterCraftEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.CraftManager.Items;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.components.namespacedkeys.CraftKeys;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;

public class CrafterCraftEvents {
    private Core core;
    private CrafterCraftEvent event;
    public CrafterCraftEvents(Core core, CrafterCraftEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(event.getResult().getType() != Material.CRAFTING_TABLE) return;
        if(event.getRecipe().getKey() != CraftKeys.MagicCraftingTable.getValue()) return;
        event.setCancelled(true);
    }
}
