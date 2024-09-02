package ru.laimcraft.vanilla.Events;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class PlayerChangedWorldEvents {
    private Core core;
    private PlayerChangedWorldEvent event;
    public PlayerChangedWorldEvents(Core core, PlayerChangedWorldEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        core.utils.tabColorUpdate(event.getPlayer());
    }
}
