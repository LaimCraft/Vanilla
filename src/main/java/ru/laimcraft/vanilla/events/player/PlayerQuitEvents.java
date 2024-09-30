package ru.laimcraft.vanilla.events.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.laimcraft.vanilla.Vanilla;

public class PlayerQuitEvents implements Listener {

    @EventHandler
    private void onPlayerQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        playersHashMapUpdate(event);
        Vanilla.tpa.remove(event.getPlayer().getName());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(event.getPlayer().getName())) continue;
            player.sendMessage(ChatColor.DARK_RED + event.getPlayer().getName());
        }
    }

    private void playersHashMapUpdate(PlayerQuitEvent event) {
        if(!Vanilla.players.containsKey(event.getPlayer().getName())) return;
        Vanilla.players.remove(event.getPlayer().getName());
    }
}
