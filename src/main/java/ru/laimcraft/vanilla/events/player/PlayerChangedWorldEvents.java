package ru.laimcraft.vanilla.events.player;

import org.bukkit.event.player.PlayerChangedWorldEvent;
import ru.laimcraft.utils.Utils;
import ru.laimcraft.vanilla.Core;

public class PlayerChangedWorldEvents {
    private Core core;
    private PlayerChangedWorldEvent event;
    public PlayerChangedWorldEvents(Core core, PlayerChangedWorldEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        Utils.vanillaTabColorUpdate(event.getPlayer());
    }
}
