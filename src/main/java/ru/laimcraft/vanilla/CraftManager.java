package ru.laimcraft.vanilla;

import ru.laimcraft.vanilla.components.CraftManager.CraftingTableCrafts;

public class CraftManager {
    CraftingTableCrafts crafts;
    public CraftManager() {
        this.crafts = new CraftingTableCrafts();
    }

    public CraftingTableCrafts getCrafts() {
        return crafts;
    }
}
