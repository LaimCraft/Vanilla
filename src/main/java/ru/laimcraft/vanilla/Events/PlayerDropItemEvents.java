package ru.laimcraft.vanilla.Events;

import org.bukkit.event.player.PlayerDropItemEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class PlayerDropItemEvents {
    private Core core;
    private PlayerDropItemEvent event;
    public PlayerDropItemEvents(Core core, PlayerDropItemEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}
