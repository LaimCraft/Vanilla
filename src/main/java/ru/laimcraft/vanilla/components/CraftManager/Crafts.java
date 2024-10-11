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
        crafts.add(elytra());
        crafts.add(magicElytra());
        crafts.add(light());
        craftList.put(health(), "health");
        craftList.put(elytra(), "elytra");
        craftList.put(magicElytra(), "magicelytra");
        craftList.put(light(), "light");
    }

    private HashMap<Material, Integer> health() {
        HashMap<Material, Integer> craft = new HashMap<>();
        craft.put(Material.REDSTONE_BLOCK, 13);
        return craft;
    }

    private HashMap<Material, Integer> elytra() {
        HashMap<Material, Integer> craft = new HashMap<>();
        craft.put(Material.ELYTRA, 4);
        craft.put(Material.DIAMOND_BLOCK, 16);
        return craft;
    }

    private HashMap<Material, Integer> magicElytra() {
        HashMap<Material, Integer> craft = new HashMap<>();
        craft.put(Material.ELYTRA, 4);
        craft.put(Material.NETHERITE_INGOT, 16);
        return craft;
    }

    private HashMap<Material, Integer> light() {
        HashMap<Material, Integer> craft = new HashMap<>();
        craft.put(Material.TORCH, 20);
        return craft;
    }
}
