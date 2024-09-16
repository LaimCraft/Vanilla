package ru.laimcraft.vanilla.components.CraftManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import ru.laimcraft.vanilla.components.namespacedkeys.CraftKeys;

public class CraftingTableCrafts {
    public CraftingTableCrafts() {
        addMagicCraftingTableRecipe();
    }


    private void addMagicCraftingTableRecipe() {
        ShapedRecipe shapedRecipe = new ShapedRecipe(CraftKeys.MagicCraftingTable.getValue(), Items.getMagicCraftingTable());
        shapedRecipe.shape("ABA", "BCB", "ABA");
        shapedRecipe.setIngredient('A', Material.CRYING_OBSIDIAN);
        shapedRecipe.setIngredient('B', Material.DIAMOND_BLOCK);
        shapedRecipe.setIngredient('C', Material.NETHERITE_BLOCK);
        Bukkit.addRecipe(shapedRecipe);
    }
}
