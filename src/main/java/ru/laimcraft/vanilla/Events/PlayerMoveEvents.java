package ru.laimcraft.vanilla.Events;

import org.bukkit.event.player.PlayerMoveEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class PlayerMoveEvents {
    private Core core;
    private PlayerMoveEvent event;
    public PlayerMoveEvents(Core core, PlayerMoveEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}