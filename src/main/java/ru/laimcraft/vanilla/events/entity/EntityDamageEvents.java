package ru.laimcraft.vanilla.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.events.player.PlayerDamageEvents;
import ru.laimcraft.vanilla.events.player.PlayerRegainHealthEvents;

public class EntityDamageEvents {
    private Core core;
    private EntityDamageEvent event;
    public EntityDamageEvents(Core core, EntityDamageEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(event.getEntity() instanceof Player player) {
            new PlayerDamageEvents(core, event, player);
        return;}
    }
}
