package ru.laimcraft.vanilla.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.laimcraft.vanilla.Utils;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.player.PlayerStatus;
import ru.laimcraft.vanilla.components.regions.RegionFlagResult;
import ru.laimcraft.vanilla.components.regions.RegionMessages;
import ru.laimcraft.vanilla.database.mysql.MySQLAccounts;
import ru.laimcraft.vanilla.database.mysql.MySQLChunks;
import ru.laimcraft.vanilla.database.mysql.MySQLRegions;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;

public class RegionCommand implements CommandExecutor {
    public int regionCreateSpawn = RegionMessages.regionCreateSpawn;
    public int regionChunkSpawn = RegionMessages.regionChunkSpawn;
    public int spawnRadius = RegionMessages.spawnRadius;
    public static double chunkRent = RegionMessages.chunkRent;
    public int regionCreate = RegionMessages.regionCreate;
    public int regionChunk = RegionMessages.regionChunk;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return true;
        switch (args.length) {
            case 0:
                RegionMessages.sendHelpMessage(player);
                return true;
            case 1:
                switch (args[0].toLowerCase()) {
                    case "create":
                        RegionMessages.sendRegionCreateMessage(player);
                        return true;
                    case "createcost":
                        RegionMessages.sendRegionCostMessage(player);
                        return true;
                    case "chunk":
                        RegionMessages.sendChunkMessage(player);
                        return true;
                    case "flag":
                        RegionMessages.sendFlagMessage(player);
                        return true;
                    case "delete":
                        RegionMessages.sendDeleteMessage(player);
                        return true;
                    case "ignore":
                        RegionFlagResult result = MySQLVanillaPlayer.getIgnoreRegionMessage(player.getName());
                        if(result == RegionFlagResult.NULL || result == RegionFlagResult.Ex) {
                            player.sendMessage(ChatColor.DARK_RED +
                                    "Произошла критическая ошибка 0001, обратитесь к администрации что бы они её исправили! " + result.getResult());
                        return true;}
                        if(result == RegionFlagResult.Yes) {player.sendMessage(ChatColor.GREEN +
                                "Теперь вы больше не будете получать сообщения о том что находитесь в чужом регионе");}
                        if(result == RegionFlagResult.No) {player.sendMessage(ChatColor.RED +
                                "Теперь вы будете получать сообщения о том что находитесь в чужом регионе");}
                        MySQLVanillaPlayer.changeIgnoreRegionMessage(player.getName());
                        return true;
                    case "help":
                        RegionMessages.sendHelpMessage(player);
                        return true;
                    default:
                        player.sendMessage(ChatColor.RED + "Вы ввели несуществующую под команду");
                        return true;
                }
            case 2:
                switch (args[0].toLowerCase()) {
                    case "create":
                        String region = args[1];
                        if(!Utils.checkRegionNameOtClick(player, region)) return true;
                        String owner = MySQLRegions.getRegionOwner(region);
                        if(owner.equals("ex")) {
                            RegionMessages.createRegionErrorEX(player);
                        return true;}
                        if(owner == null) {
                            RegionMessages.regionNoCreated(player);
                        return true;}

                        Boolean regionCreate = MySQLRegions.create(
                                region,
                                player.getLocation().getWorld().getName()
                        );

                        if(!regionCreate) {
                            if(MySQLRegions.getRegionOwner(region) == null) {
                                RegionMessages.createRegionErrorEX(player);
                            return true;}
                            if(MySQLRegions.getRegionOwner(region).equals("ex")) {
                                RegionMessages.createRegionErrorEX(player);
                                return true;}
                        }

                        MySQLChunks.create(
                                region,
                                player.getLocation().getWorld().getName(),
                                player.getLocation().getChunk().getX(),
                                player.getLocation().getChunk().getZ()
                        );
                        RegionMessages.regionCreated(player);
                        return true;
                    case "createcost":
                        RegionMessages.sendRegionCostMessage(player);
                        return true;
                    case "chunk":
                        RegionMessages.sendChunkMessage(player);
                        return true;
                    case "flag":
                        RegionMessages.sendFlagMessage(player);
                        return true;
                    case "delete":
                        RegionMessages.sendDeleteMessage(player);
                        return true;
                    case "ignore":
                        RegionFlagResult result = MySQLVanillaPlayer.getIgnoreRegionMessage(player.getName());
                        if(result == RegionFlagResult.NULL || result == RegionFlagResult.Ex) {
                            player.sendMessage(ChatColor.DARK_RED +
                                    "Произошла критическая ошибка 0001, обратитесь к администрации что бы они её исправили! " + result.getResult());
                            return true;}
                        if(result == RegionFlagResult.Yes) {player.sendMessage(ChatColor.GREEN +
                                "Теперь вы больше не будете получать сообщения о том что находитесь в чужом регионе");}
                        if(result == RegionFlagResult.No) {player.sendMessage(ChatColor.RED +
                                "Теперь вы будете получать сообщения о том что находитесь в чужом регионе");}
                        MySQLVanillaPlayer.changeIgnoreRegionMessage(player.getName());
                        return true;
                    case "help":
                        RegionMessages.sendHelpMessage(player);
                        return true;
                    default:
                        return true;
                }
            default:
                sender.sendMessage(ChatColor.RED + "Слишком много аргументов");
                return true;
        }
    }
}
