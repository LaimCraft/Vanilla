package ru.laimcraft.vanilla.events;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class EntityDamageByEntityEvents {
    private Core core;
    private EntityDamageByEntityEvent event;
    public EntityDamageByEntityEvents(Core core, EntityDamageByEntityEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}