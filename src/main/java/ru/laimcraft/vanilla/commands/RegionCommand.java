package ru.laimcraft.vanilla.commands;

import net.minecraft.Util;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
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

import java.util.ArrayList;
import java.util.List;

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
            case 1: // 1 Подкоманда
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




            case 2: // 2 Подкоманды
                switch (args[0].toLowerCase()) {
                    case "create":
                        String region = args[1];
                        if(!Utils.checkRegionNameOtClick(player, region)) return true;
                        String owner = MySQLRegions.getRegionOwner(region);
                        switch (owner) {
                            case "ex":
                                RegionMessages.createRegionErrorEX(player);
                                return true;
                            case null:
                                String chunkRegion = MySQLChunks.getRegion(
                                        player.getLocation().getWorld().getName(),
                                        player.getLocation().getChunk().getX(),
                                        player.getLocation().getChunk().getZ()
                                );
                                switch (chunkRegion) {
                                    case "ex":
                                        RegionMessages.createRegionErrorEX(player);
                                        return true;
                                    case null:
                                        //Проверка маны
                                        PlayerStatus status = Vanilla.players.get(player.getName());
                                        int create = (RegionMessages.regionCreateSpawn + RegionMessages.regionChunkSpawn);
                                        if(status.getMP() < create) {
                                            RegionMessages.regionCreateNoMP(player, create);
                                        return true;}
                                        status.removeMP(create);
                                        Boolean regionCreate = MySQLRegions.create(
                                                region,
                                                player.getLocation().getWorld().getName(),
                                                player.getName()
                                        );
                                        if(!regionCreate) {
                                            if(MySQLRegions.getRegionOwner(region) == null) {
                                                RegionMessages.createRegionErrorEX(player);}
                                            if(MySQLRegions.getRegionOwner(region).equals("ex")) {
                                                RegionMessages.createRegionErrorEX(player);}}

                                        MySQLChunks.create(
                                                region,
                                                player.getLocation().getWorld().getName(),
                                                player.getLocation().getChunk().getX(),
                                                player.getLocation().getChunk().getZ()
                                        );
                                        RegionMessages.regionCreated(player);
                                        return true;
                                    default:
                                        RegionMessages.regionOtherChunk(player);
                                        return true;
                                }
                            default:
                                RegionMessages.regionNoCreated(player);
                                return true;
                        }
                    case "createcost":
                        RegionMessages.sendRegionCostMessage(player);
                        return true;
                    case "chunk":
                        switch (args[1].toLowerCase()) {
                            case "info":
                                String regionInfo = MySQLChunks.getRegion(
                                        player.getLocation().getWorld().getName(),
                                        player.getLocation().getChunk().getX(),
                                        player.getLocation().getChunk().getZ()
                                );
                                switch (regionInfo) {
                                    case "ex":
                                        RegionMessages.regionError(player, "args.l-3, chunk, switch region ex");
                                        return true;
                                    case null:
                                        player.sendMessage(ChatColor.GREEN + "На этом чанке регион отсутствует");
                                        return true;
                                    default:
                                        player.sendMessage(ChatColor.RED + "Этот чанк запривачен регион: " + regionInfo);
                                        player.sendMessage(ChatColor.GOLD + "Данные об регионе скрыты что бы избежать намеренного грифа!");
                                        return true;
                                }
                            case "payday":
                                RegionMessages.payDayMessage(player);
                                return true;
                            case "cost":
                                RegionMessages.chunkCost(player);
                                return true;
                            case "add":
                                RegionMessages.chunkAddMessage(player);
                                return true;
                            case "remove":
                                //remove chunk to region
                                return true;
                            default:
                                player.sendMessage(ChatColor.RED + "Вы ввели неправильную подкоманду /region chunk <ОШИБКА> 3 аргумент неверный!");
                                return true;
                        }
                    case "flag":
                        RegionMessages.sendFlagMessage(player);
                        return true;
                    case "delete": // В будещем т.к. сейчас делать мне лень) НО ОБЯЗАТЕЛЬНО ДОДЕЛАТЬ!
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



            case 3:
                switch (args[0].toLowerCase()) {
                    case "create": // Не используется тут
                        RegionMessages.sendRegionCreateMessage(player);
                        return true;
                    case "createcost": // Не используется тут
                        RegionMessages.sendRegionCostMessage(player);
                        return true;
                    case "chunk":
                        if (!args[1].equalsIgnoreCase("add")) {
                            RegionMessages.sendChunkMessage(player);
                            player.sendMessage(ChatColor.RED + "Подкоманды с 3 аргументами: /region chunk add <Название региона>");
                            return true;
                        }
                        if (!Utils.checkRegionNameOtClick(player, args[2])) return true;
                        String addToRegion = MySQLRegions.getRegionOwner(args[2]);
                        if (addToRegion == null) {
                            RegionMessages.regionNo(player);
                            return true;
                        }
                        if (addToRegion.equals("ex")) {
                            RegionMessages.regionError(player, "addToRegion = ex");
                            return true;
                        }
                        Boolean addChunk = false;
                        Chunk chunk = player.getLocation().getChunk();
                        Chunk chunkWest = chunk.getWorld().getChunkAt(
                                chunk.getX() - 1,
                                chunk.getZ()
                        );
                        Chunk chunkSouth = chunk.getWorld().getChunkAt(
                                chunk.getX(),
                                chunk.getZ() + 1
                        );
                        Chunk chunkEast = chunk.getWorld().getChunkAt(
                                chunk.getX() + 1,
                                chunk.getZ()
                        );
                        Chunk chunkNorth = chunk.getWorld().getChunkAt(
                                chunk.getX(),
                                chunk.getZ() - 1
                        );
                        List<Chunk> chunks = new ArrayList<>();
                        chunks.add(chunkWest);
                        chunks.add(chunkSouth);
                        chunks.add(chunkEast);
                        chunks.add(chunkNorth);

                        for (Chunk chunk2 : chunks) {
                            String chunk2s = MySQLChunks.getRegion(
                                    chunk2.getWorld().getName(),
                                    chunk2.getX(),
                                    chunk2.getZ()
                            );
                            if (chunk2s == null) continue;
                            if (chunk2s.equals("ex")) continue;
                            if (!chunk2s.equalsIgnoreCase(addToRegion)) continue;
                            addChunk = true;
                        }

                        if (!addChunk) {
                            String chunkRegion = MySQLChunks.getRegion(chunk.getWorld().getName(), chunk.getX(), chunk.getZ());
                            if (chunkRegion == null) {
                                player.sendMessage(ChatColor.RED + "Регион " + addToRegion + " не соприкасается с чанком где вы стоите");
                                return true;}
                            if(chunkRegion.equals("ex")) {
                                RegionMessages.regionError(player, "chunkRegion.equals(EX) error chunk add");
                            return true;}
                            player.sendMessage(ChatColor.GOLD + "Данный чанк уже запривачен");
                            return true;}
                        PlayerStatus status = Vanilla.players.get(player.getName());
                        if(status.getMP() < RegionMessages.regionChunkSpawn) {
                            player.sendMessage(ChatColor.YELLOW + "У вас не хватает маны");
                            player.sendMessage(ChatColor.YELLOW + "вам нужно " + RegionMessages.regionChunkSpawn + "ед. маны для привата");
                        return true;}
                        status.removeMP(RegionMessages.regionChunkSpawn);
                        MySQLChunks.create(addToRegion, chunk.getWorld().getName(), chunk.getX(), chunk.getZ());
                        player.sendMessage(ChatColor.GREEN + "Вы успешно заприватили этот чанк!");
                        return true;
                    case "flag":
                        RegionMessages.sendFlagMessage(player);
                        return true;
                    case "delete": // Не используется тут
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
            default:
                sender.sendMessage(ChatColor.RED + "Слишком много аргументов");
                return true;
        }
    }

    private void rgInfo(Player player) {

    }
}
