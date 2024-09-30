package ru.laimcraft.vanilla.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class PlayerExpChangeEvents implements Listener {

    @EventHandler
    private void onPlayerExpChangeEvents(PlayerExpChangeEvent event) {
        PlayerStatus playerStatus = Vanilla.players.get(event.getPlayer().getName());
        playerStatus.addXP(event.getAmount());
        event.setAmount(0);
    }
}
