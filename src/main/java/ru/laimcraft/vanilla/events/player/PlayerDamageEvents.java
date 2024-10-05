package ru.laimcraft.vanilla.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

import java.util.Date;

public class PlayerDamageEvents { //Old Version Class
    private EntityDamageEvent event;
    private Player player;
    public PlayerDamageEvents(EntityDamageEvent event, Player player) {this.event = event; this.player=player; start();}

    private void start() {
        double damage = event.getFinalDamage();
        if(event.getCause() == DamageCause.LAVA) {
            if((Vanilla.damageController.get(player.getName()) + 500) > new Date().getTime()) {
                event.setDamage(0d);
                event.setCancelled(true);
            return;}
            Vanilla.damageController.replace(player.getName(), new Date().getTime());
        }
        PlayerStatus status = Vanilla.players.get(player.getName());
        if(status == null) {
            event.setCancelled(true);
        return;}
        if(!status.getStatus()) {
            event.setCancelled(true);
        return;}

        //damage = damage / 2;

        boolean death = status.removeHP(damage);
        event.setDamage(0d);
        if(!death) { //death
            status.resetHP();
            //status.resetMP();
            status.resetHunger();
            status.resetSaturation();
            player.setHealth(0);
        return;}
        //player.setExp(1);
        player.setHealth(status.getHP() / ((double) status.getMaxHP() / 20));
    }
}
