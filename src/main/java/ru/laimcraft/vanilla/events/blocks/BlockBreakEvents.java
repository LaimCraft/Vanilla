package ru.laimcraft.vanilla.events.blocks;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import ru.laimcraft.vanilla.components.CraftManager.Items;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;

public class BlockBreakEvents {
    private Core core;
    private BlockBreakEvent event;
    public BlockBreakEvents(Core core, BlockBreakEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
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
        if(core.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            String owner = core.chests.getChestOwner(event.getBlock().getWorld().getName() + ":" +
                    event.getBlock().getX() + ":" + event.getBlock().getY() + ":" + event.getBlock().getZ());
            if(owner == null || owner.isEmpty()) return;
            if(owner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
            if(!owner.equalsIgnoreCase(event.getPlayer().getName())) {event.setCancelled(true); return;}
            core.chests.removeChest(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":" + event.getBlock().getZ());}
    }
}
