package ru.laimcraft.vanilla.events.player;

import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import ru.laimcraft.utils.Utils;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class PlayerExpChangeEvents {
    private Core core;
    private PlayerExpChangeEvent event;
    public PlayerExpChangeEvents(Core core, PlayerExpChangeEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {event.setAmount(0);return;}
        PlayerStatus playerStatus = Core.players.get(event.getPlayer().getName().toLowerCase());
        playerStatus.addXP(event.getAmount());
        event.setAmount(0);
    }
}
