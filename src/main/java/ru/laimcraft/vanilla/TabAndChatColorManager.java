package ru.laimcraft.vanilla;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class TabAndChatColorManager implements Listener {
    public TabAndChatColorManager() {
        Bukkit.getPluginManager().registerEvents(this, Vanilla.instance);
    }

    @EventHandler
    private void onPlayerChatEvent(PlayerChatEvent event) {
        String message = event.getMessage().replace("%", "%%");
        event.setFormat(Utils.vanillaGetTabColor(event.getPlayer()) + event.getPlayer().getName() + " " + ChatColor.WHITE + message);
    }

    @EventHandler
    private void onPlayerChangedWorldEvents(PlayerChangedWorldEvent event) {
        Utils.vanillaTabColorUpdate(event.getPlayer());
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Utils.vanillaTabColorUpdate(event.getPlayer());
    }

    @EventHandler
    private void onServerRestart(ServerLoadEvent event) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            Utils.vanillaTabColorUpdate(player);
        }
    }

}
