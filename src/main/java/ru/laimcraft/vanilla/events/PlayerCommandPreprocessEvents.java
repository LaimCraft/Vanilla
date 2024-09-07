package ru.laimcraft.vanilla.events;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.laimcraft.vanilla.components.auth.AuthChecker;
import ru.laimcraft.vanilla.Core;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommandPreprocessEvents {
    private Core core;
    private PlayerCommandPreprocessEvent event;
    public PlayerCommandPreprocessEvents(Core core, PlayerCommandPreprocessEvent event) {this.core = core; this.event = event; start();}

    private void start() {
        if(new AuthChecker(core, event).getResult()) {
            String[] cmd = event.getMessage().split(" ");
            List<String> allowCommands = new ArrayList<>();
            allowCommands.add("/register");
            allowCommands.add("/reg");
            allowCommands.add("/login");
            allowCommands.add("/l");
            if(allowCommands.contains(cmd[0].toLowerCase())) return;
            event.setCancelled(true); return;}
    }
}
