package ru.laimcraft.vanilla.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import ru.laimcraft.vanilla.Utils;

public class PlayerChangedWorldEvents implements Listener {

    @EventHandler
    private void onPlayerChangedWorldEvents(PlayerChangedWorldEvent event) {
        Utils.vanillaTabColorUpdate(event.getPlayer());
    }
}
