package ru.laimcraft.vanilla.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class PlayerRegainHealthEvents {
    private EntityRegainHealthEvent event;
    private Player player;
    public PlayerRegainHealthEvents(EntityRegainHealthEvent event, Player player) {this.event = event;
        this.player = player; start();}
    private void start() {
        PlayerStatus status = Vanilla.players.get(player.getName());
        if(status == null) {
            event.setCancelled(true);
            return;}
        if(!status.getStatus()) {
            event.setCancelled(true);
            return;}
        status.addHP(event.getAmount());
        event.setAmount(0d);
        player.setHealth(status.getHP() / ((double) status.getMaxHP() / 20));
    }
}
