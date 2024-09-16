package ru.laimcraft.vanilla.components.CraftManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Crafts {
    public List<HashMap<Material, Integer>> crafts = new ArrayList<>();
    public HashMap<HashMap<Material, Integer>, String> craftList = new HashMap<>();
    public Crafts() {
        crafts.add(health());
        craftList.put(health(), "health");
    }

    private HashMap<Material, Integer> health() {
        HashMap<Material, Integer> craft = new HashMap<>();
        craft.put(Material.REDSTONE_BLOCK, 13);
        return craft;
    }
}
