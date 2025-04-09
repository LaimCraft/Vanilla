package ru.laimcraft.vanilla.admin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CHCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof final Player player)) return true;
        Chunk[] chunks = Bukkit.getWorld("world").getLoadedChunks();
        for(Chunk chunk : chunks) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chunk.getX());
            stringBuilder.append(":");
            stringBuilder.append(chunk.getZ());
            stringBuilder.append("  ");
            stringBuilder.append(chunk.getEntities().length);
            Bukkit.getConsoleSender().sendMessage(stringBuilder.toString());
            if(chunk.getX() < 100) player.sendMessage(stringBuilder.toString());
        }
        return true;
    }
}
