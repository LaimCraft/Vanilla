package ru.laimcraft.vanilla.events.blocks;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;

import java.util.Objects;

public class BlockPlaceEvents {
    private Core core;
    private BlockPlaceEvent event;
    public BlockPlaceEvents(Core core, BlockPlaceEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
        if(core.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            core.chests.create(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":"  + event.getBlock().getZ(), event.getPlayer().getName());}
        onSpecialBlockPlace();
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
