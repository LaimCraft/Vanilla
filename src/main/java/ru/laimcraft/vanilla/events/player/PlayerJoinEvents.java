package ru.laimcraft.vanilla.events.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.laimcraft.vanilla.Utils;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.CreateAllDataBaseColumns;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

import java.util.Date;

public class PlayerJoinEvents implements Listener {

    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Vanilla.players.put(event.getPlayer().getName(), new PlayerStatus(event.getPlayer().getName()));
        Utils.vanillaTabColorUpdate(event.getPlayer());
        CreateAllDataBaseColumns createAllDataBaseColumns = new CreateAllDataBaseColumns(event.getPlayer());
        Vanilla.damageController.put(event.getPlayer().getName(), new Date().getTime());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(event.getPlayer().getName())) continue;
            player.sendMessage(ChatColor.DARK_AQUA + "Игрок " + event.getPlayer().getName() + " присоединился к игре");
        }
    }
}
