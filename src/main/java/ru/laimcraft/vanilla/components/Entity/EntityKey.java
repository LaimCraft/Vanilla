package ru.laimcraft.vanilla.components.Entity;

import org.bukkit.NamespacedKey;

public enum EntityKey {
    zombie(new NamespacedKey("vanilla", "entity_zombie"));

    private final NamespacedKey value;

    private EntityKey(NamespacedKey value) {
        this.value = value;
    }

    public NamespacedKey getValue() {
        return value;
    }
}
