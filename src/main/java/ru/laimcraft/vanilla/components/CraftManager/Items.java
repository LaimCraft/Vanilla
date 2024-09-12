package ru.laimcraft.vanilla.components.CraftManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;

import java.util.Objects;

public class Items {
    public static ItemStack getMagicCraftingTable() {
        ItemStack itemStack = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.DARK_AQUA + "Магический верстак");
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.set(ItemKey.MagicCraftingTable.getValue(), PersistentDataType.BOOLEAN, true);
        persistentDataContainer.set(ItemKey.SpecialBlocks.getValue(), PersistentDataType.STRING, "MagicCraftingTable");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
