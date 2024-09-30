package ru.laimcraft.vanilla.events.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;

import java.util.Objects;

public class BlockPlaceEvents implements Listener {

    private BlockPlaceEvent event;
    @EventHandler
    private void onBlockPlaceEvent(BlockPlaceEvent event) {
        this.event = event;
        if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            if(!hopperPlace()) {
                event.setCancelled(true);
            return;}
            Vanilla.chests.create(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":"  + event.getBlock().getZ(), event.getPlayer().getName());}
        onSpecialBlockPlace();
    }


    private boolean hopperPlace() {
        if(event.getBlock().getType() == Material.HOPPER) {
            Location location = new Location(
                    event.getBlock().getWorld(),
                    event.getBlock().getX(),
                    event.getBlock().getY() + 1,
                    event.getBlock().getZ()
            );
            Block block = event.getBlock().getWorld().getBlockAt(location);
            if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
                String owner =
                        Vanilla.chests.getChestOwner
                                (block.getWorld().getName() + ":" + block.getX() + ":" + block.getY() + ":" + block.getZ());
                if(owner == null) return true;
                if(owner.equals(event.getPlayer().getName())) return true;

                String[] players = Vanilla.chestAccess.getPlayersArray(owner);
                if(players == null) return false;

                for(String player : players) {
                    if(player.equals(event.getPlayer().getName())) return true;
                }
                return false;
            }
            return true;
        }
        return true;
    }


    private void onSpecialBlockPlace() {
        String specialBlock = Objects.requireNonNull(event.getItemInHand().getItemMeta()).getPersistentDataContainer()
                .get(ItemKey.SpecialBlocks.getValue(), PersistentDataType.STRING);
        switch (Objects.requireNonNull(specialBlock)) {
            case "MagicCraftingTable":
                MySQLBlocks.create(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                        + event.getBlock().getY() + ":"  + event.getBlock().getZ(),"MagicCraftingTable", event.getPlayer().getName());
                return;
            default:
                return;
        }
    }
}
