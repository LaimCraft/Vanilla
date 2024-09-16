package ru.laimcraft.vanilla.components.CraftManager;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class CraftMaterialAllowed {
    private List<Material> materials;
    public CraftMaterialAllowed() {
        materials = new ArrayList<>();
        materials.add(Material.REDSTONE_BLOCK);
        materials.add(Material.LAPIS_BLOCK);
    }

    public List<Material> getMaterials() {
        return materials;
    }
}
