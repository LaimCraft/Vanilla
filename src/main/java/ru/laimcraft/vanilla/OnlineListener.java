package ru.laimcraft.vanilla;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class OnlineListener implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Online.add();
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        Online.remove();
    }

    @EventHandler
    private void onReload(ServerLoadEvent event) {
        Online.reset();
    }
}