package ru.laimcraft.vanilla.events.crafts;

import org.bukkit.Material;
import org.bukkit.event.block.CrafterCraftEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.namespacedkeys.CraftKeys;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class CraftItemEvents {
    private Core core;
    private CraftItemEvent event;
    public CraftItemEvents(Core core, CraftItemEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(onMagicCraftingTableCraft()) return;
    }

    private boolean onMagicCraftingTableCraft() {
        if(event.getRecipe().getResult() == null) return false;
        if(event.getRecipe().getResult().getType() != Material.CRAFTING_TABLE) return false;
        if((event.getRecipe().getResult().getItemMeta().getPersistentDataContainer().get(CraftKeys.MagicCraftingTable.getValue(), PersistentDataType.BOOLEAN) == null)
        && (event.getRecipe().getResult().getItemMeta().getPersistentDataContainer().get(ItemKey.MagicCraftingTable.getValue(), PersistentDataType.BOOLEAN) == null)) {
            return false;}
        PlayerStatus status = Core.players.get(event.getWhoClicked().getName().toLowerCase());
        if(status.getLevel() != 1) return true;
        status.addLevel(1);
        return true;
    }
}
