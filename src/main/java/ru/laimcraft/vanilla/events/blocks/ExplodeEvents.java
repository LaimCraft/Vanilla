package ru.laimcraft.vanilla.events.blocks;

import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.database.mysql.MySQLChests;

public class ExplodeEvents implements Listener {


    //ТНТ КРИПЕР КРИСТАЛЛ ЭНДА
    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
    protected void onBlockExplode(EntityExplodeEvent event) {
        for(Block block : event.blockList()) {
            if(!Vanilla.blockInventory.getInventoryBlocks().contains(block.getType())) continue;
            String owner = Vanilla.chests.getChestOwner(
                    block.getWorld().getName()
                            + ":" +
                            block.getX()
                            + ":" +
                            block.getY()
                            + ":" +
                            block.getZ()
            );
            if(owner == null) continue;
            event.blockList().remove(block);
        }
    }

    //КРОВАТЬ
    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
    protected void onBlockExplode(BlockExplodeEvent event) {
        for(Block block : event.blockList()) {
            if(!Vanilla.blockInventory.getInventoryBlocks().contains(block.getType())) continue;
            String owner = Vanilla.chests.getChestOwner(
                    block.getWorld().getName()
                            + ":" +
                            block.getX()
                            + ":" +
                            block.getY()
                            + ":" +
                            block.getZ()
            );
            if(owner == null) continue;
            event.blockList().remove(block);
        }
    }

    /*@EventHandler
    private void onE() {
        if(event.getEntity().getItemStack().getItemMeta().getPersistentDataContainer().get(ItemKey.MagicApple.getValue(), PersistentDataType.DOUBLE) != null) {
            event.setCancelled(true);
        }
        //event.setCancelled(true);
    }*/
}
