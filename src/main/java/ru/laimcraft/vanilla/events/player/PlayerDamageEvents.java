package ru.laimcraft.vanilla.events.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class PlayerDamageEvents {
    private Core core;
    private EntityDamageEvent event;
    private Player player;
    public PlayerDamageEvents(Core core, EntityDamageEvent event, Player player) {this.core = core; this.event = event; this.player=player; start();}

    private void start() {
        PlayerStatus status = Core.players.get(player.getName().toLowerCase());
        if(status == null) {
            event.setCancelled(true);
        return;}
        if(!status.getStatus()) {
            event.setCancelled(true);
        return;}
        double damage = event.getFinalDamage();
        boolean death = status.removeHP(damage);
        event.setDamage(0d);
        if(!death) { //death
            status.resetHP();
            status.resetMP();
            status.resetHunger();
            status.resetSaturation();
            player.setHealth(0);
        return;}
        //player.setExp(1);
        player.setHealth(status.getHP() / ((double) status.getMaxHP() / 20));
    }
}
