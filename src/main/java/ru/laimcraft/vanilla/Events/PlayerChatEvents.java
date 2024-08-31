package ru.laimcraft.vanilla.Events;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.laimcraft.vanilla.Components.Auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class PlayerChatEvents {
    private Core core;
    private AsyncPlayerChatEvent event;
    public PlayerChatEvents(Core core, AsyncPlayerChatEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}
    }
}
