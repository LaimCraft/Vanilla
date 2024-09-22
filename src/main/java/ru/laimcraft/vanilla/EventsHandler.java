package ru.laimcraft.vanilla;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;
import ru.laimcraft.vanilla.events.blocks.BlockBreakEvents;
import ru.laimcraft.vanilla.events.blocks.BlockExplodeEvents;
import ru.laimcraft.vanilla.events.blocks.BlockPlaceEvents;
import ru.laimcraft.vanilla.events.crafts.CraftItemEvents;
import ru.laimcraft.vanilla.events.crafts.CrafterCraftEvents;
import ru.laimcraft.vanilla.events.chat.PlayerChatEvents;
import ru.laimcraft.vanilla.events.commands.PlayerCommandPreprocessEvents;
import ru.laimcraft.vanilla.events.entity.EntityDamageByEntityEvents;
import ru.laimcraft.vanilla.events.entity.EntityDamageEvents;
import ru.laimcraft.vanilla.events.entity.EntityRegainHealthEvents;
import ru.laimcraft.vanilla.events.inventory.InventoryClickEvents;
import ru.laimcraft.vanilla.events.inventory.InventoryCloseEvents;
import ru.laimcraft.vanilla.events.inventory.InventoryOpenEvents;
import ru.laimcraft.vanilla.events.player.*;
import ru.laimcraft.vanilla.events.server.ServerLoadEvents;

public class EventsHandler implements Listener {
    private Core core;
    public EventsHandler(Core core) {this.core = core;}

    @EventHandler (ignoreCancelled = true)
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        new PlayerJoinEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerQuitEvent(PlayerQuitEvent event) {
        new PlayerQuitEvents(core, event);
    }
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        new PlayerChatEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        new PlayerCommandPreprocessEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerMoveEvent(PlayerMoveEvent event) {
        new PlayerMoveEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        new PlayerDropItemEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onInventoryClickEvent(InventoryClickEvent event) {
        new InventoryClickEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        new EntityDamageByEntityEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onBlockPlaceEvent(BlockPlaceEvent event) {
        new BlockPlaceEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onInventoryOpenEvent(InventoryOpenEvent event) {
        new InventoryOpenEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        new PlayerChangedWorldEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onBlockBreakEvent(BlockBreakEvent event) {
        new BlockBreakEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerInteractEvent(PlayerInteractEvent event) {
        new PlayerInteractEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerExpChangeEvent(PlayerExpChangeEvent event) {
        new PlayerExpChangeEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onEntityRegainHealthEvent(EntityRegainHealthEvent event) {
        new EntityRegainHealthEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onEntityDamage(EntityDamageEvent event) {
        new EntityDamageEvents(core, event);
    }
    @EventHandler
    private void onLoad(ServerLoadEvent event) {
        new ServerLoadEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onInventoryCloseEvent(InventoryCloseEvent event) {
        new InventoryCloseEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerCraftEvent(CrafterCraftEvent event) {
        new CrafterCraftEvents(core, event);
    }
    @EventHandler (ignoreCancelled = true)
    private void onPlayerCraftEvent(CraftItemEvent event) {
        new CraftItemEvents(core, event);
    }
    @EventHandler
    private void onBlockExplodeEvent(BlockExplodeEvent event) {
        new BlockExplodeEvents(core, event);
    }
    @EventHandler
    private void onTRr(ExplosionPrimeEvent event) {event.setRadius(0f);}
}
