package ru.laimcraft.vanilla.events.blocks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.components.regions.RegionFlagResult;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;
import ru.laimcraft.vanilla.database.mysql.MySQLChunks;
import ru.laimcraft.vanilla.database.mysql.MySQLRegions;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;

import java.util.List;
import java.util.Objects;

public class BlockPlaceEvents implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    private void regionChecker(BlockPlaceEvent event) {
        String login = event.getPlayer().getName();
        String region = MySQLChunks.getRegion(
                event.getBlock().getWorld().getName(),
                event.getBlock().getChunk().getX(),
                event.getBlock().getChunk().getZ()
        );

        if(region == null) return;
        if(region.equals("ex")) {
            event.setCancelled(true);
            return;}

        String regionOwner = MySQLRegions.getRegionOwner(region);
        if(regionOwner != null) {
            if(regionOwner.equals(login)) return;
        }

        List<String> regionMembers = MySQLRegions.getRegionMembers(region);
        if(regionMembers != null) {
            if(regionMembers.size() > 0) {
                if(regionMembers.contains(login)) {
                    return;
                }
            }
        }

        event.setCancelled(true);
        if(MySQLVanillaPlayer.getIgnoreRegionMessage(login) == RegionFlagResult.Yes) {
            event.getPlayer().sendMessage(ChatColor.RED + "Вы находитесь в чужом регионе поэтому не можете здесь ставить блоки");
        }
    }



    @EventHandler (ignoreCancelled = true)
    private void onBlockPlaceEvent(BlockPlaceEvent event) {
        if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            if(!hopperPlace(event)) {
                event.setCancelled(true);
            return;}
            Vanilla.chests.create(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":"  + event.getBlock().getZ(), event.getPlayer().getName());}
        onSpecialBlockPlace(event);
    }


    private boolean hopperPlace(BlockPlaceEvent event) {
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


    private void onSpecialBlockPlace(BlockPlaceEvent event) {
        switch (event.getItemInHand().getItemMeta().getPersistentDataContainer()
                .get(ItemKey.SpecialBlocks.getValue(), PersistentDataType.STRING)) {
            case "MagicCraftingTable":
                MySQLBlocks.create(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                        + event.getBlock().getY() + ":"  + event.getBlock().getZ(),"MagicCraftingTable", event.getPlayer().getName());
                return;
            case null:
                return;
            default:
                return;
        }
    }
}
