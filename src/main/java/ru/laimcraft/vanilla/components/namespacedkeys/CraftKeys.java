package ru.laimcraft.vanilla.components.namespacedkeys;

import org.bukkit.NamespacedKey;

public enum CraftKeys {
    MagicCraftingTable(new NamespacedKey("vanilla", "craft_magiccraftingtable"));

    private final NamespacedKey value;

    private CraftKeys(NamespacedKey value) {
        this.value = value;
    }

    public NamespacedKey getValue() {
        return value;
    }
}
