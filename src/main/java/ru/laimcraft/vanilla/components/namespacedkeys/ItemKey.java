package ru.laimcraft.vanilla.components.namespacedkeys;

import org.bukkit.NamespacedKey;

public enum ItemKey {
    NoMovement(new NamespacedKey("vanilla", "item_nomovement")),
    SpecialBlocks(new NamespacedKey("vanilla", "item_specialblocks")),
    MagicCraftingTable(new NamespacedKey("vanilla", "item_magiccraftingtable"));

    private final NamespacedKey value;

    private ItemKey(NamespacedKey value) {
        this.value = value;
    }

    public NamespacedKey getValue() {
        return value;
    }
}
