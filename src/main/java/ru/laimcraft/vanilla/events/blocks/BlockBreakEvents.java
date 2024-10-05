package ru.laimcraft.vanilla.events.blocks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.Items;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.components.regions.RegionFlagResult;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;
import ru.laimcraft.vanilla.database.mysql.MySQLChunks;
import ru.laimcraft.vanilla.database.mysql.MySQLRegions;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;

import java.util.List;

public class BlockBreakEvents implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    private void regionChecker(BlockBreakEvent event) {
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
            event.getPlayer().sendMessage(ChatColor.RED + "Вы находитесь в чужом регионе поэтому не можете здесь ломать");
        }
    }


    private BlockBreakEvent event;
    @EventHandler (ignoreCancelled = true)
    private void onBlockBreakEvent(BlockBreakEvent event) {
        this.event = event;
        chestRemove();
        if(onSpecialBlockBreak()) {event.setCancelled(true); return;}
    }

    private boolean onSpecialBlockBreak() {
        String blockOwner = MySQLBlocks.getBlockOwner(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                + event.getBlock().getY() + ":"  + event.getBlock().getZ());
        if(blockOwner == null || blockOwner.isEmpty()) return false;
        if(blockOwner.equalsIgnoreCase("ex")) return true;
        if(blockOwner.equalsIgnoreCase(event.getPlayer().getName())) {
            String blockType = MySQLBlocks.getBlockType(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":"  + event.getBlock().getZ());
            ItemKey itemKey = ItemKey.valueOf(blockType);
            boolean dropItemLogic = specialItemDropLogic(itemKey);
            if(dropItemLogic) event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), Items.getMagicCraftingTable());
            MySQLBlocks.removeBlock(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":"  + event.getBlock().getZ());
            //if(!dropItemLogic) //Логика выдачи ачивки! (В будущем!)
        return false;}
        return true;
    }

    private boolean specialItemDropLogic(ItemKey itemKey) {
        switch (itemKey) {
            case MagicCraftingTable:
                event.setDropItems(false);
                if(event.getPlayer().getInventory().getItemInMainHand() == null) return false;
                if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.NETHERITE_AXE) return false;
                return true;
            default:
                event.setDropItems(false);
                return false;
        }
    }

    private void chestRemove() {
        if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            String owner = Vanilla.chests.getChestOwner(event.getBlock().getWorld().getName() + ":" +
                    event.getBlock().getX() + ":" + event.getBlock().getY() + ":" + event.getBlock().getZ());
            if(owner == null || owner.isEmpty()) return;
            if(owner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
            if(!owner.equalsIgnoreCase(event.getPlayer().getName())) {event.setCancelled(true); return;}
            Vanilla.chests.removeChest(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":" + event.getBlock().getZ());}
    }
}
