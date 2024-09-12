package ru.laimcraft.vanilla.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class PlayerRegainHealthEvents {
    private Core core;
    private EntityRegainHealthEvent event;
    private Player player;
    public PlayerRegainHealthEvents(Core core, EntityRegainHealthEvent event, Player player) {this.core = core; this.event = event;
        this.player = player; start();}
    private void start() {
        PlayerStatus status = Core.players.get(player.getName().toLowerCase());
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
