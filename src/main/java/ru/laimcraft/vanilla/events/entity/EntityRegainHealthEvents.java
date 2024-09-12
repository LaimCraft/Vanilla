package ru.laimcraft.vanilla.events.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.events.player.PlayerRegainHealthEvents;

public class EntityRegainHealthEvents {
    private Core core;
    private EntityRegainHealthEvent event;
    public EntityRegainHealthEvents(Core core, EntityRegainHealthEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(event.getEntity() instanceof Player player) {
            new PlayerRegainHealthEvents(core, event, player);
        return;}
    }
}
