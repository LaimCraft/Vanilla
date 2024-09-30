package ru.laimcraft.vanilla.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.laimcraft.vanilla.Utils;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.CreateAllDataBaseColumns;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class PlayerJoinEvents implements Listener {

    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Vanilla.players.put(event.getPlayer().getName(), new PlayerStatus(event.getPlayer().getName()));
        Utils.vanillaTabColorUpdate(event.getPlayer());
        CreateAllDataBaseColumns createAllDataBaseColumns = new CreateAllDataBaseColumns(event.getPlayer());
    }
}
