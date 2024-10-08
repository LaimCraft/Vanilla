package ru.laimcraft.vanilla.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Vanilla;

import java.util.regex.Pattern;

public class ChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("mode")) {
                if(Vanilla.chestModePlayers.contains(player.getName())) {
                    Vanilla.chestModePlayers.remove(player.getName());
                return true;}
                Vanilla.chestModePlayers.add(player.getName());
            return true;}
            if(args[0].equalsIgnoreCase("list")) {
                String[] players = Vanilla.chestAccess.getPlayersArray(player.getName());
                if(players == null) {
                    sender.sendMessage(ChatColor.GOLD + "Вы не давали другим игрокам доступ к сундукам");
                return true;}
                sender.sendMessage(ChatColor.GREEN + "Список игроков которые имеют доступ к вашим сундукам");
                for(String playerSend : players) {
                    sender.sendMessage(ChatColor.YELLOW + "  " + playerSend);
                }
            return true;}
        return true;}
        if(args.length != 2) {
            sender.sendMessage(ChatColor.GOLD + "Для ввода команды используйте /chest <add/remove> <player>");
        return true;}
        if(args[0].equalsIgnoreCase("add")) {
            if(checkPlayerName(args[1])) {
                String login = Vanilla.accounts.getLoginByLogin(args[1]);
                if(login == null || login.isEmpty()) {
                    player.sendMessage(ChatColor.DARK_RED + "Данный игрок никогда не регистрировался на нашем проекте!");
                return true;}
                String owner = Vanilla.chestAccess.getOwner(player.getName());
                if(owner == null || owner.isEmpty()) {
                    Vanilla.chestAccess.create(player.getName(), args[1]);
                    player.sendMessage(ChatColor.GREEN + "Вы дали игроку " + args[1] + " доступ к своим сундукам!");
                return true;}
                Vanilla.chestAccess.addPlayer(player.getName(), args[1]);
                player.sendMessage(ChatColor.GREEN + "Вы дали игроку " + args[1] + " доступ к своим сундукам!");
            return true;}
            player.sendMessage(ChatColor.RED + "Вы ввели неправильно имя пользователя!");
        return true;}
        if(args[0].equalsIgnoreCase("remove")) {
            if(checkPlayerName(args[1])) {
                String login = Vanilla.accounts.getLoginByLogin(args[1]);
                if(login == null || login.isEmpty()) {
                    player.sendMessage(ChatColor.DARK_RED + "Данный игрок никогда не регистрировался на нашем проекте!");
                    return true;}
                boolean chestAccess = Vanilla.chestAccess.removePlayer(player.getName(), args[1]);
                player.sendMessage(ChatColor.RED + "Вы забрали у игрока " + args[1] + " доступ к вашим сундукам!");
                return true;}
            player.sendMessage(ChatColor.RED + "Вы ввели неправильно имя пользователя!");
            return true;}
        sender.sendMessage(ChatColor.GOLD + "Для ввода команды используйте /chest <add/remove> <player>");
        return true;
    }

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
    return true;}
}
