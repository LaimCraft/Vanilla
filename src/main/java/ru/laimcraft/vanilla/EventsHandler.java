package ru.laimcraft.rp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.Events.*;

public class EventsHandler implements Listener {
    private Core core;
    public EventsHandler(Core core) {this.core = core;}

    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        PlayerJoinEvents playerJoinEvents = new PlayerJoinEvents(core, event);}
    @EventHandler
    private void onPlayerQuitEvent(PlayerQuitEvent event) {PlayerQuitEvents playerQuitEvents = new PlayerQuitEvents(core, event);}
    @EventHandler
    private void onPlayerChatEvent(AsyncPlayerChatEvent event) {PlayerChatEvents playerChatEvents = new PlayerChatEvents(core, event);}
    @EventHandler
    private void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {PlayerCommandPreprocessEvents playerCommandPreprocessEvents = new PlayerCommandPreprocessEvents(core, event);}
    @EventHandler
    private void onPlayerMoveEvent(PlayerMoveEvent event) {PlayerMoveEvents playerMoveEvents = new PlayerMoveEvents(core, event);}
    @EventHandler
    private void onPlayerDropItemEvent(PlayerDropItemEvent event) {PlayerDropItemEvents playerDropItemEvents = new PlayerDropItemEvents(core, event);}
    @EventHandler
    private void onInventoryClickEvent(InventoryClickEvent event) {InventoryClickEvents inventoryClickEvents = new InventoryClickEvents(core, event);}
    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {EntityDamageByEntityEvents entityDamageByEntityEvents = new EntityDamageByEntityEvents(core, event);}
    @EventHandler
    private void onBlockPlaceEvent(BlockPlaceEvent event) {BlockPlaceEvents blockPlaceEvents = new BlockPlaceEvents(core, event);}
    @EventHandler
    private void onBlockBreakEvent(BlockBreakEvent event) {
        BlockBreakEvents blockBreakEvents = new BlockBreakEvents(core, event);}
    @EventHandler
    private void onPlayerInteractEvent(PlayerInteractEvent event) {
        PlayerInteractEvents playerInteractEvents = new PlayerInteractEvents(core, event);}
    @EventHandler
    private void onLoad(ServerLoadEvent event) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("Перезагрузка....");
        }
    }
}
