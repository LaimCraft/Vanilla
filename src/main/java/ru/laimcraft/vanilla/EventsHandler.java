package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import ru.laimcraft.vanilla.events.blocks.BlockBreakEvents;
import ru.laimcraft.vanilla.events.blocks.BlockPlaceEvents;
import ru.laimcraft.vanilla.events.chat.PlayerChatEvents;
import ru.laimcraft.vanilla.events.commands.PlayerCommandPreprocessEvents;
import ru.laimcraft.vanilla.events.crafts.CraftItemEvents;
import ru.laimcraft.vanilla.events.crafts.CrafterCraftEvents;
import ru.laimcraft.vanilla.events.entity.EntityDamageByEntityEvents;
import ru.laimcraft.vanilla.events.entity.EntityDamageEvents;
import ru.laimcraft.vanilla.events.entity.EntityRegainHealthEvents;
import ru.laimcraft.vanilla.events.inventory.InventoryClickEvents;
import ru.laimcraft.vanilla.events.inventory.InventoryOpenEvents;
import ru.laimcraft.vanilla.events.player.*;
import ru.laimcraft.vanilla.events.server.ExplosionPrimeEvents;
import ru.laimcraft.vanilla.events.server.ServerLoadEvents;

public class EventsHandler {

    public EventsHandler() {
        register(new BlockBreakEvents());
        register(new BlockPlaceEvents());

        register(new PlayerChatEvents());

        register(new PlayerCommandPreprocessEvents()); //delete

        register(new CrafterCraftEvents());
        register(new CraftItemEvents());

        register(new EntityDamageByEntityEvents());
        register(new EntityDamageEvents());
        register(new EntityRegainHealthEvents());

        register(new InventoryClickEvents());
        register(new InventoryOpenEvents());
        register(new InventoryOpenEvents());

        register(new PlayerChangedWorldEvents());
        //register(new PlayerDamageEvents()); -> EntityDamageEvents();
        register(new PlayerDropItemEvents());
        register(new PlayerExpChangeEvents());
        register(new PlayerInteractEvents());
        register(new PlayerJoinEvents());
        register(new PlayerMoveEvents());
        register(new PlayerQuitEvents());
        //register(new PlayerRegainHealthEvents()); -> EntityRegainHealthEvents();

        register(new ServerLoadEvents());
        register(new ExplosionPrimeEvents());

    }

    private void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Vanilla.instance);
    }
}
