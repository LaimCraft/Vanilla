package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import ru.laimcraft.vanilla.components.CraftManager.CraftingTableCrafts;

import java.util.List;

public class CraftManager {
    CraftingTableCrafts crafts;
    public CraftManager() {
        this.crafts = new CraftingTableCrafts();
        blockingCrafts();
    }

    public void blockingCrafts() {
        List<Recipe> recipes = Bukkit.getRecipesFor(new ItemStack(Material.TNT_MINECART));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.TNT)));
        for(Recipe recipe : recipes) {
            Bukkit.removeRecipe(recipe.getResult().getType().getKey());
        }
    }

    public CraftingTableCrafts getCrafts() {
        return crafts;
    }
}
