package ru.laimcraft.vanilla.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.laimcraft.vanilla.events.player.PlayerDamageEvents;

public class EntityDamageEvents implements Listener {

    @EventHandler
    private void onEntityDamageEvents(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) {
            new PlayerDamageEvents(event, player);
        return;}
    }
}
