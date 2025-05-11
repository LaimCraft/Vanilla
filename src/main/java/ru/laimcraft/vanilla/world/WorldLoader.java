package ru.laimcraft.vanilla.world;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.world.WorldLoadEvent;
import ru.laimcraft.vanilla.Worlds;

public class WorldLoader {


    public WorldLoader() {
        Worlds.world = new World(Bukkit.getWorld("world"));
        Worlds.nether = new World(Bukkit.getWorld("nether"));
        Worlds.end = new World(Bukkit.getWorld("end"));
    }


}
