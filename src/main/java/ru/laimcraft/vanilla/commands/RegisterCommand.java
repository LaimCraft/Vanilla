package ru.laimcraft.vanilla.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.utils.Utils;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.components.player.CreateAllDataBaseColumns;
import ru.laimcraft.vanilla.components.player.PlayerStatus;

public class RegisterCommand implements CommandExecutor {
    private Core core;
    public RegisterCommand(Core core) {this.core=core;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if((args.length == 0) || ((args[0] == null) || (args[0].isEmpty()))) {
            player.sendMessage(ChatColor.YELLOW + "Для регистрации введите в чат /register <Пароль>");
            return true;}
        if(args[0].length() > 48) {
            player.sendMessage(ChatColor.RED + "Ваш пароль слишком длинный!");
            player.sendMessage(ChatColor.RED + "в нём не должно быть больше 48 символов!");
            return true;}
        String login = core.accounts.getLoginByLogin(player.getName());
        if(login != null) {
            if(login.equals("ex")) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Системная ошибка в модуле авторизации [MySQLRegisterCommand_01]");
                player.kickPlayer(ChatColor.RED + "Произошла системная ошибка, администрация уже уведомлена о ней \n Но вы можете так же сообщить о ней" +
                        "в нашем Discord канале: discord.gg/U7D8vzK6XS");
                return true;}
            return true;}
        // REGISTER -> ....
        if(core.accounts.create(player.getName(), Utils.getSHA512(args[0]))) {
            player.sendMessage(ChatColor.DARK_GREEN + "Вы успешно зарегистрировались!");
            //player.sendMessage(ChatColor.GREEN + "Для получения документов пройдите в мэрию");
            core.AuthPlayers.add(player.getName().toLowerCase());
        for(Player send : Bukkit.getOnlinePlayers()) {
            if(send.getName().equals(player.getName())) continue;
            send.sendMessage(ChatColor.DARK_GREEN + "Игрок " + player.getName() + " зарегистрировался!");
            CreateAllDataBaseColumns createAllDataBaseColumns = new CreateAllDataBaseColumns(core, player);
            core.players.put(player.getName().toLowerCase(), new PlayerStatus(player));}
        Utils.vanillaTabColorUpdate(player);
        return true;}
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Системная ошибка в модуле авторизации [MySQLAccounts_01]");
        player.kickPlayer(ChatColor.DARK_RED + "Произошла системная ошибка, администрация уже уведомлена о ней \n Но вы можете так же сообщить о ней" +
                "в нашем Discord канале: discord.gg/U7D8vzK6XS");
        return true;
    }
}
