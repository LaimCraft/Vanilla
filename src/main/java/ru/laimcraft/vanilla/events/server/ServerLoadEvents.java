package ru.laimcraft.vanilla.events.server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.server.ServerLoadEvent;
import ru.laimcraft.vanilla.Core;

public class ServerLoadEvents {
    private Core core;
    private ServerLoadEvent event;
    public ServerLoadEvents(Core core, ServerLoadEvent event) {this.core = core; this.event = event; start();}
    private void start() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("Перезагрузка....");
        }
    }
}
