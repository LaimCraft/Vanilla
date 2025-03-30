package ru.laimcraft.vanilla.admin.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class gmCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof final Player player)) return true;
        if(player.getGameMode() == GameMode.SURVIVAL) player.setGameMode(GameMode.SPECTATOR);
        else player.setGameMode(GameMode.SURVIVAL);
        return true;
    }
}
