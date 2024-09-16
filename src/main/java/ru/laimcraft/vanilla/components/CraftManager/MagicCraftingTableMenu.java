package ru.laimcraft.vanilla.components.CraftManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;

public class MagicCraftingTableMenu {
    private Inventory inventory;
    public MagicCraftingTableMenu() {
        inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_AQUA + "Магический верстак");
        ItemStack ir = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS);
        ItemMeta meta = ir.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + " ");
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(ItemKey.NoMovement.getValue(), PersistentDataType.BOOLEAN, true);
        ir.setItemMeta(meta);
        for(int i = 0; i!=11; i++) {
            inventory.setItem(i, ir);}
        for(int i = 16; i!=20; i++) {
            inventory.setItem(i, ir);}
        for(int i = 25; i!=29; i++) {
            inventory.setItem(i, ir);}
        for(int i = 34; i!=38; i++) {
            inventory.setItem(i, ir);}
        for(int i = 43; i!=54; i++) {
            inventory.setItem(i, ir);}
    }

    public Inventory getInventory() {
        return inventory;
    }
}
