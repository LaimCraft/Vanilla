package ru.laimcraft.vanilla.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Core;

import java.util.regex.Pattern;

public class CoinsCommand implements CommandExecutor {
    private Core core;
    public CoinsCommand(Core core) {this.core=core;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Вы не игрок!");
        return true;}
        switch (args.length) {
            case 0:
                sender.sendMessage(ChatColor.GREEN + "Баланс: " + core.accounts.getBalance(sender.getName()) + " coins");
                break;
            case 3:
                if(args[0] == null || args[0].isEmpty()) return true;
                if(args[0].equalsIgnoreCase("pay")) {
                    if(args[1] == null || args[1].isEmpty()) return true;
                    if(!checkPlayerName(args[1])) {
                        sender.sendMessage(ChatColor.RED + "Вы неправильно ввели имя пользователя которому хотите передать деньги");
                    return true;}
                    String login = core.accounts.getLoginByLogin(sender.getName());
                    if(login == null || login.isEmpty()) {
                        sender.sendMessage(ChatColor.DARK_RED + "Игрока которому вы пытаетесь передать деньги не существует!");
                    return true;}
                    if(!checkInteger(args[2])) {
                        sender.sendMessage(ChatColor.RED + "Вы ввели неверно сумму перевода!");
                    return true;}

                    int integer = Integer.parseInt(args[2]);
                    if(integer <= 0) {
                        sender.sendMessage(ChatColor.RED + "Сумма не может быть меньше 1 единицы!");
                    return true;}
                    if(sender.getName().equalsIgnoreCase(args[1])) {
                        sender.sendMessage(ChatColor.RED + "Нельзя переводить себе!");
                    return true;}
                    if(core.accounts.getBalance(sender.getName()) < integer) {
                        sender.sendMessage(ChatColor.RED + "У вас недостаточно средств!");
                    return true;}
                    core.accounts.pay(sender.getName(), args[1], integer);
                    sender.sendMessage(ChatColor.DARK_GREEN + "Вы успешно перевели игроку " + args[1] + " " + integer + " coins");
                    if(Bukkit.getOnlinePlayers().contains(args[1])) {
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.DARK_GREEN + "Вам перевел игрок " + sender.getName() + " " + integer + " coins");}
                return true;}
                break;
            default:
                sender.sendMessage(ChatColor.GOLD + "Введите /coin -> проверить баланс");
                sender.sendMessage(ChatColor.GOLD + "Введите /coin pay <player> <amount> -> отправить деньги другому игроку");
                break;
        }
    return true;}

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
    return true;}

    private boolean checkInteger(String Integer) {
        if(Integer == null || Integer.isEmpty()) return false;
        if(Integer.length() > 6) return false;
        if(!Pattern.matches("^[0-9]+$", Integer)) return false;
        if(!Character.isDigit(Integer.charAt(0))) return false;
        return true;}
}
