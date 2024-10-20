package ru.laimcraft.vanilla;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInventory {
    private List<Material> inventoryBlocks = new ArrayList<>();
    public BlockInventory() {
        inventoryBlocks.add(Material.CHEST);
        inventoryBlocks.add(Material.BARREL);
        inventoryBlocks.add(Material.BREWING_STAND);
        inventoryBlocks.add(Material.CRAFTER);
        inventoryBlocks.add(Material.JUKEBOX);
        inventoryBlocks.add(Material.ARMOR_STAND);
        inventoryBlocks.add(Material.CHISELED_BOOKSHELF);

        // Печи
        inventoryBlocks.add(Material.FURNACE);
        inventoryBlocks.add(Material.BLAST_FURNACE);
        inventoryBlocks.add(Material.SMOKER);

        //Механизмы
        inventoryBlocks.add(Material.DISPENSER);
        inventoryBlocks.add(Material.DROPPER);
        inventoryBlocks.add(Material.HOPPER);

        // SHULKERS
        inventoryBlocks.add(Material.SHULKER_BOX);
        inventoryBlocks.add(Material.WHITE_SHULKER_BOX);
        inventoryBlocks.add(Material.LIGHT_GRAY_SHULKER_BOX);
        inventoryBlocks.add(Material.GRAY_SHULKER_BOX);
        inventoryBlocks.add(Material.BLACK_SHULKER_BOX);
        inventoryBlocks.add(Material.BROWN_SHULKER_BOX);
        inventoryBlocks.add(Material.RED_SHULKER_BOX);
        inventoryBlocks.add(Material.ORANGE_SHULKER_BOX);
        inventoryBlocks.add(Material.YELLOW_SHULKER_BOX);
        inventoryBlocks.add(Material.LIME_SHULKER_BOX);
        inventoryBlocks.add(Material.GREEN_SHULKER_BOX);
        inventoryBlocks.add(Material.CYAN_SHULKER_BOX);
        inventoryBlocks.add(Material.LIGHT_BLUE_SHULKER_BOX);
        inventoryBlocks.add(Material.BLUE_SHULKER_BOX);
        inventoryBlocks.add(Material.PURPLE_SHULKER_BOX);
        inventoryBlocks.add(Material.MAGENTA_SHULKER_BOX);
        inventoryBlocks.add(Material.PINK_SHULKER_BOX);


    }

    public List<Material> getInventoryBlocks() {
        return this.inventoryBlocks;
    }
}
