package ru.laimcraft.vanilla.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.Items;
import ru.laimcraft.vanilla.database.mysql.MySQLAccounts;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;

import java.util.regex.Pattern;

public class TPACommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if(!player.getName().equals("limeworld")) return true;
        if(args.length == 0 || args.length > 1) {
            sender.sendMessage(ChatColor.GREEN + "/tpa <Ник игрока>");
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("accept")) {
                String tp = Vanilla.tpa.get(player.getName());
                Vanilla.tpa.remove(player.getName());
                tp = tp;
                Bukkit.getPlayer(tp).teleport(player.getLocation());
                return true;
            }
            if(!checkPlayerName(args[0])) {
                player.sendMessage(ChatColor.DARK_RED + "Вы неправильно ввели ник игрока!");
                return true;}
            boolean teleport = false;
            for(Player player1 : Bukkit.getOnlinePlayers()) {
                if(player1.getName().equalsIgnoreCase(args[0])) {
                    teleport = true;
                    break;
                }
            }
            if(!teleport) {
                player.sendMessage(ChatColor.RED + "Данный игрок не в сети");
                return true;}
            Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "Игрок " + player.getName() + " отправил вам запрос на телепортацию");
            player.sendMessage(ChatColor.GREEN + "Запрос на телепортацию опправлен");
            if(Vanilla.tpa.get(args[0]) == null) {
                Vanilla.tpa.put(args[0], player.getName());
            } else {
                Vanilla.tpa.replace(args[0], player.getName());
            }
            return true;}
        return true;
    }

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
    return true;}
}
