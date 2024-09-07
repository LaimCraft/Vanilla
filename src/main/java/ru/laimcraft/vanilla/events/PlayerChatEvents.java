package ru.laimcraft.vanilla.events;

import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.laimcraft.utils.Utils;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

public class PlayerChatEvents {
    private Core core;
    private AsyncPlayerChatEvent event;
    public PlayerChatEvents(Core core, AsyncPlayerChatEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setCancelled(true); return;}

        event.setFormat(Utils.vanillaGetTabColor(event.getPlayer()) + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA +
                core.players.get(event.getPlayer().getName().toLowerCase()).getLevel() + ChatColor.DARK_AQUA + "] " + ChatColor.GRAY +"> " + ChatColor.WHITE);
    }
}
