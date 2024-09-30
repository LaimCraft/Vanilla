package ru.laimcraft.vanilla.events.server;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosionPrimeEvents implements Listener {

    @EventHandler
    private void ExplosionPrimeEvents(ExplosionPrimeEvent event) {
        event.setRadius(0f);
    }
}
