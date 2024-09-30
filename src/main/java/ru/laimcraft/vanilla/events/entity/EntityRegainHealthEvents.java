package ru.laimcraft.vanilla.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import ru.laimcraft.vanilla.events.player.PlayerRegainHealthEvents;

public class EntityRegainHealthEvents implements Listener {

    @EventHandler
    private void onEntityRegainHealthEvents(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player player) {
            new PlayerRegainHealthEvents(event, player);
        return;}
    }
}
