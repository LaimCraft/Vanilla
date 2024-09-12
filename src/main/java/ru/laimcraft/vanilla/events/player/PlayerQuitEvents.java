package ru.laimcraft.vanilla.events.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.laimcraft.vanilla.Core;

public class PlayerQuitEvents {
    private Core core;
    private PlayerQuitEvent event;
    public PlayerQuitEvents(Core core, PlayerQuitEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        event.setQuitMessage(null);
        playersHashMapUpdate();
        if (core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.getName().equalsIgnoreCase(event.getPlayer().getName())) continue;
                player.sendMessage(ChatColor.DARK_RED + event.getPlayer().getName());
            } core.AuthPlayers.remove(event.getPlayer().getName().toLowerCase()); return;}
    }

    private void playersHashMapUpdate() {
        if(!core.players.containsKey(event.getPlayer().getName().toLowerCase())) return;
        core.players.remove(event.getPlayer().getName().toLowerCase());
    }
}
