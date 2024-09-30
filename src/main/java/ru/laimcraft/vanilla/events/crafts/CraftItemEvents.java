package ru.laimcraft.vanilla.events.crafts;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.namespacedkeys.CraftKeys;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class CraftItemEvents implements Listener {

    private CraftItemEvent event;
    @EventHandler
    private void onCraftItemEvents(CraftItemEvent event) {
        this.event = event;
        if(onMagicCraftingTableCraft()) return;
    }

    private boolean onMagicCraftingTableCraft() {
        if(event.getRecipe().getResult() == null) return false;
        if(event.getRecipe().getResult().getType() != Material.CRAFTING_TABLE) return false;
        if((event.getRecipe().getResult().getItemMeta().getPersistentDataContainer().get(CraftKeys.MagicCraftingTable.getValue(), PersistentDataType.BOOLEAN) == null)
        && (event.getRecipe().getResult().getItemMeta().getPersistentDataContainer().get(ItemKey.MagicCraftingTable.getValue(), PersistentDataType.BOOLEAN) == null)) {
            return false;}
        PlayerStatus status = Vanilla.players.get(event.getWhoClicked().getName());
        if(status.getLevel() != 1) return true;
        status.addLevel(1);
        return true;
    }
}
