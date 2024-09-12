package ru.laimcraft.vanilla;

import ru.laimcraft.vanilla.components.CraftManager.Crafts;

public class CraftManager {
    Crafts crafts;
    public CraftManager() {
        this.crafts = new Crafts();
    }

    public Crafts getCrafts() {
        return crafts;
    }
}
