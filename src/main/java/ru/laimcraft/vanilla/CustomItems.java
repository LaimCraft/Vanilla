package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.custom.enchantments.Pickaxe3x3;

public class CustomItems implements Listener {

    public CustomItems() {
        Bukkit.getPluginManager().registerEvents(this, Vanilla.instance);
        addCraftPickaxe3x3Upper();
    }

    private void addCraftPickaxe3x3Upper() {
        ItemStack result = new ItemStack(Material.SUGAR);
        ItemMeta meta = result.getItemMeta();
        meta.setItemName(ChatColor.GOLD + "Добыча блоков 3х3");
        meta.setCustomModelData(1);
        meta.getPersistentDataContainer().set(Pickaxe3x3.tool3X3upper, PersistentDataType.BOOLEAN, true);
        result.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(Pickaxe3x3.tool3X3upper, result);
        recipe.shape("XSX", "SYS", "XSX");
        recipe.setIngredient('X', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.HEART_OF_THE_SEA);
        recipe.setIngredient('Y', Material.NETHER_STAR);
        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack left = event.getInventory().getFirstItem();   // левый слот
        ItemStack right = event.getInventory().getSecondItem(); // правый слот

        if (left == null || right == null) return;

        if (right.getType() == Material.SUGAR) {
            Material leftMaterial = left.getType();
            if (leftMaterial == Material.NETHERITE_PICKAXE || leftMaterial == Material.NETHERITE_SHOVEL) {
                if (right.getPersistentDataContainer().get(Pickaxe3x3.tool3X3upper, PersistentDataType.BOOLEAN) == null) return;
                if (right.getAmount() != 1) return;
                ItemStack result = left.clone();
                ItemMeta meta = result.getItemMeta();
                meta.getPersistentDataContainer().set(Pickaxe3x3.tool3X3, PersistentDataType.BOOLEAN, true);
                Pickaxe3x3.addLore(meta);
                result.setItemMeta(meta);

                event.setResult(result);
                event.getInventory().setRepairCost(50); // Стоимость в опыте
            }
        }
    }

    private void sendMessageAll(String s) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(s);
        }
    }
}
