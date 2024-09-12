package ru.laimcraft.vanilla.events.player;

import org.bukkit.event.player.PlayerInteractEvent;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class PlayerInteractEvents {
    private Core core;
    private PlayerInteractEvent event;
    public PlayerInteractEvents(Core core, PlayerInteractEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}
