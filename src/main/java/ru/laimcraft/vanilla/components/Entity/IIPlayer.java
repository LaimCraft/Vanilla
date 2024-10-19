package ru.laimcraft.vanilla.components.Entity;

import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class IIPlayer extends CraftPlayer {
    public IIPlayer(CraftServer server, ServerPlayer entity) {
        super(server, entity);
    }
}
