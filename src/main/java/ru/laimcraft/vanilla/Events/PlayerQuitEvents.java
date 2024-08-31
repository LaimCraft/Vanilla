package ru.laimcraft.vanilla.Events;

import org.bukkit.event.player.PlayerQuitEvent;
import ru.laimcraft.vanilla.Core;

public class PlayerQuitEvents {
    private Core core;
    private PlayerQuitEvent event;
    public PlayerQuitEvents(Core core, PlayerQuitEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        event.setQuitMessage(null);
        core.AuthPlayers.remove(event.getPlayer().getName().toLowerCase());
    }
}
