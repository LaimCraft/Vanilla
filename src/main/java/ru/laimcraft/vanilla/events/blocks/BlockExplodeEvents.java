package ru.laimcraft.vanilla.events.blocks;

import net.minecraft.world.entity.ai.sensing.NearestVisibleLivingEntitySensor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.CraftManager.Items;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.database.mysql.MySQLBlocks;
import ru.laimcraft.vanilla.database.mysql.MySQLChests;

public class BlockExplodeEvents {
    private Core core;
    private BlockExplodeEvent event;

    public BlockExplodeEvents(Core core, BlockExplodeEvent event) {this.core = core;this.event = event;start();}

    private void start() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "BLOCK! TNT!");
        event.setCancelled(true); if(1 == 1) return;
        String blockOwner = core.chests.getChestOwner(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                + event.getBlock().getY() + ":" + event.getBlock().getZ());
        String specialBlock = MySQLBlocks.getBlockOwner(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                + event.getBlock().getY() + ":" + event.getBlock().getZ());
        if(check(blockOwner)) {event.setCancelled(true); return;}
        if(check(specialBlock)) {event.setCancelled(true); return;}
    }
    // check Задаёт вопрос запретить взрыв?
    private boolean check(String s) {
        switch (s) {
            case null:
                return false;
            case "":
                return false;
            case "ex":
                return true;
            default:
                return true;
        }
    }
}
