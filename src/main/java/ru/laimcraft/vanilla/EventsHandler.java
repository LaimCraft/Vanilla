package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;
import ru.laimcraft.vanilla.events.*;

public class EventsHandler implements Listener {
    private Core core;
    public EventsHandler(Core core) {this.core = core;}

    @EventHandler (ignoreCancelled = true)
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        PlayerJoinEvents playerJoinEvents = new PlayerJoinEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerQuitEvent(PlayerQuitEvent event) {PlayerQuitEvents playerQuitEvents = new PlayerQuitEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerChatEvent(AsyncPlayerChatEvent event) {PlayerChatEvents playerChatEvents = new PlayerChatEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {PlayerCommandPreprocessEvents playerCommandPreprocessEvents = new PlayerCommandPreprocessEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerMoveEvent(PlayerMoveEvent event) {PlayerMoveEvents playerMoveEvents = new PlayerMoveEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerDropItemEvent(PlayerDropItemEvent event) {PlayerDropItemEvents playerDropItemEvents = new PlayerDropItemEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onInventoryClickEvent(InventoryClickEvent event) {InventoryClickEvents inventoryClickEvents = new InventoryClickEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {EntityDamageByEntityEvents entityDamageByEntityEvents = new EntityDamageByEntityEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onBlockPlaceEvent(BlockPlaceEvent event) {BlockPlaceEvents blockPlaceEvents = new BlockPlaceEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onInventoryOpenEvent(InventoryOpenEvent event) {InventoryOpenEvents inventoryOpenEvents = new InventoryOpenEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {PlayerChangedWorldEvents playerChangedWorldEvents = new PlayerChangedWorldEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onBlockBreakEvent(BlockBreakEvent event) {
        BlockBreakEvents blockBreakEvents = new BlockBreakEvents(core, event);}
    @EventHandler (ignoreCancelled = true)
    private void onPlayerInteractEvent(PlayerInteractEvent event) {
        PlayerInteractEvents playerInteractEvents = new PlayerInteractEvents(core, event);}
    @EventHandler
    private void onLoad(ServerLoadEvent event) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("Перезагрузка....");
        }
    }
}
