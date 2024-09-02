package ru.laimcraft.vanilla.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import ru.laimcraft.vanilla.Core;

import java.awt.color.ICC_ColorSpace;

public class PlayerQuitEvents {
    private Core core;
    private PlayerQuitEvent event;
    public PlayerQuitEvents(Core core, PlayerQuitEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        event.setQuitMessage(null);
        if (core.AuthPlayers.contains(event.getPlayer().getName().toLowerCase())) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.getName().equalsIgnoreCase(event.getPlayer().getName())) continue;
                player.sendMessage(ChatColor.DARK_RED + event.getPlayer().getName());
            } core.AuthPlayers.remove(event.getPlayer().getName().toLowerCase()); return;
        }

        vanishDisable();
    }

    private void vanishDisable() {
        Player player = event.getPlayer();
        if(core.vanishPlayers.contains(player.getName())) {
            core.vanishPlayers.remove(player.getName());
            core.utils.tabColorUpdate(player);
            for(Player playerSend : Bukkit.getOnlinePlayers()) {
                if(playerSend.getName().equalsIgnoreCase(player.getName())) continue;
                playerSend.sendMessage(ChatColor.DARK_GREEN + player.getPlayer().getName());}
            player.removePotionEffect(PotionEffectType.INVISIBILITY);}
    }
}
