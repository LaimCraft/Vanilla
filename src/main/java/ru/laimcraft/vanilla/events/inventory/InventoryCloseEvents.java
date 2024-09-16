package ru.laimcraft.vanilla.events.inventory;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.CraftManager.MagicCraftingTable;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;

public class InventoryCloseEvents {
    private Core core;
    private InventoryCloseEvent event;
    public InventoryCloseEvents(Core core, InventoryCloseEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        magicCraftingTableClose();
    }

    private void magicCraftingTableClose() {
        if(!Core.MagicCraftingTablePlayers.containsKey(event.getPlayer().getName().toLowerCase())) return;
        MagicCraftingTable magicCraftingTable = Core.MagicCraftingTablePlayers.get(event.getPlayer().getName().toLowerCase());
        magicCraftingTable.close();
        Core.MagicCraftingTablePlayers.remove(event.getPlayer().getName().toLowerCase());
    }
}
