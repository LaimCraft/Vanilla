package ru.laimcraft.vanilla.events.server;

import net.coreprotect.CoreProtect;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import ru.laimcraft.vanilla.Random;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.Items;
import ru.laimcraft.vanilla.components.MagicItemsTime;
import ru.laimcraft.vanilla.components.namespacedkeys.ItemKey;
import ru.laimcraft.vanilla.components.player.CreateAllDataBaseColumns;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

import java.util.*;

public class ServerLoadEvents implements Listener {

    @EventHandler
    private void onLoad(ServerLoadEvent event) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            Vanilla.players.put(player.getName(), new PlayerStatus(player.getName()));
            Vanilla.damageController.put(player.getName(), new Date().getTime());
            CreateAllDataBaseColumns createAllDataBaseColumns = new CreateAllDataBaseColumns(player);
        }
    }


    @EventHandler
    private void onSpawnChests(LootGenerateEvent event) {
        for(ItemStack item : lootGenerator()) {
            event.getLoot().add(item);
        }
    }

    private List<ItemStack> lootGenerator() {
        List<ItemStack> items = new ArrayList<>();
        items.add(Items.getMagicAppleMaxMP());
        return items;
    }

    @EventHandler
    private void onFood(PlayerItemConsumeEvent event) {
        switch (event.getItem().getType()) {
            case APPLE:
                if(event.getItem().getItemMeta().getPersistentDataContainer()
                        .get(ItemKey.MagicApple.getValue(), PersistentDataType.DOUBLE) == null) return;
                Double mp = event.getItem().getItemMeta().getPersistentDataContainer()
                        .get(ItemKey.MagicApple.getValue(), PersistentDataType.DOUBLE);
                Vanilla.players.get(event.getPlayer().getName()).addMP(mp);
                return;
            default:
                return;
        }
    }

    /*@EventHandler
    private void onEventEe(LeavesDecayEvent event) {
        if(event.getBlock() == null) return;
        if(event.getBlock().getType() == null) return;
        if(event.getBlock().getType() != Material.OAK_LEAVES) return;
        List<String[]> strings = CoreProtect.getInstance().getAPI().blockLookup(event.getBlock(), MagicItemsTime.MagicApple);
        if(!(strings.size() != 0)) return;
        for(ItemStack itemStack : event.getBlock().getDrops()) {
            if(itemStack.getType() != Material.APPLE) return;
            event.getBlock().setBlock
        }
    }*/


    @EventHandler
    private void onEventEe(LeavesDecayEvent event) {
        if(event.getBlock() == null) return;
        if(event.getBlock().getType() == null) return;
        if(event.getBlock().getType() != Material.OAK_LEAVES) return;
        List<String[]> strings = CoreProtect.getInstance().getAPI().blockLookup(event.getBlock(), MagicItemsTime.MagicApple);
        if(strings.size() != 0) return;
        for(ItemStack itemStack : event.getBlock().getDrops()) {
            //Bukkit.getPlayer("limeworld").sendMessage("" + itemStack.getType());
            if(itemStack.getType() != Material.APPLE) continue;
            event.getBlock().getDrops().remove(itemStack);
            event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), Items.getMagicAppleMaxMP());
        }
    }

    /*@EventHandler
    private void onBlockDropItemEvent(BlockDropItemEvent event) {
        if(event.getBlock() == null) return;
        if(event.getBlock().getType() == null) return;
        if(event.getBlock().getType() != Material.OAK_LEAVES) return;
        List<String[]> strings = CoreProtect.getInstance().getAPI().blockLookup(event.getBlock(), MagicItemsTime.MagicApple);
        if(!(strings.size() != 0)) return;
        for(event.getBlock().getDrops())
        /*List<String[]> lookup = null;//CoreProtect.getInstance().getAPI().blockLookup(event.getBlock(), 1000);
        if(lookup == null) {
            Bukkit.getConsoleSender().sendMessage("NULL");
        return;} Bukkit.getConsoleSender().sendMessage("ARGUMENT");
        //if(!(event.getBlock() instanceof Leaves leaves)) return;
    }*/



}
