package ru.laimcraft.vanilla.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Core;

import java.net.URL;

public class LoginCommand implements CommandExecutor {
    private Core core;
    public LoginCommand(Core core) {this.core=core;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if((args.length == 0) || ((args[0] == null) || (args[0].isEmpty()))) {
            player.sendMessage(ChatColor.YELLOW + "Для авторизации введите в чат /login <Пароль>");
        return true;}
        if(args[0].length() > 48) {
            player.sendMessage(ChatColor.RED + "Ваш пароль слишком длинный!");
            player.sendMessage(ChatColor.RED + "в нём не должно быть больше 48 символов!");
        return true;}
        String login = core.accounts.getLoginByLogin(player.getName());
        if(login == null) {
            player.sendMessage(ChatColor.DARK_RED + "Вы ещё не зарегистрировались!");
        return true;}
        if(login.equals("ex")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Системная ошибка в модуле авторизации [MySQLLoginCommand_01]");
            player.kickPlayer(ChatColor.RED + "Произошла системная ошибка, администрация уже уведомлена о ней \n Но вы можете так же сообщить о ней" +
                    "в нашем Discord канале: discord.gg/U7D8vzK6XS");
        return true;}
        if(!login.equals(player.getName().toLowerCase())) {
            player.sendMessage(ChatColor.DARK_RED + "Вы ещё не зарегистрировались!");
        return true;}
        if(core.AuthPlayers.contains(player.getName().toLowerCase())) {
            player.sendMessage(ChatColor.RED + "Вы уже авторизовались!");
        return true;}
        if(core.utils.getSHA512(args[0]).equals(core.accounts.getPasswordByLogin(player.getName()))) {
            player.sendMessage(ChatColor.GREEN + "Вы успешно авторизовались!");
            core.AuthPlayers.add(player.getName().toLowerCase());
            core.accounts.authDateUpdate(player.getName());
            for(Player playerSend : Bukkit.getOnlinePlayers()) {
                if(playerSend.getName().equalsIgnoreCase(player.getName())) continue;
                playerSend.sendMessage(ChatColor.DARK_GREEN + player.getPlayer().getName());}
            core.utils.tabColorUpdate(player);
        return true;}
        player.sendMessage(ChatColor.DARK_RED + "Вы ввели неверный пароль!");
        return true;
    }
}
