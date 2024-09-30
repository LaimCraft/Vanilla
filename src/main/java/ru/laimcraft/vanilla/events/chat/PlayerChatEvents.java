package ru.laimcraft.vanilla.events.chat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import ru.laimcraft.vanilla.Utils;
import ru.laimcraft.vanilla.Vanilla;

public class PlayerChatEvents implements Listener {

    @EventHandler
    private void onPlayerChatEvent(PlayerChatEvent event) {
        String message = event.getMessage().replace("%", "%%");
        event.setFormat(Utils.vanillaGetTabColor(event.getPlayer()) + event.getPlayer().getName() + ChatColor.DARK_AQUA + " [" + ChatColor.AQUA +
                Vanilla.players.get(event.getPlayer().getName()).getLevel() + ChatColor.DARK_AQUA + "] " + ChatColor.GRAY +"> " + ChatColor.WHITE + message);


    }
}
