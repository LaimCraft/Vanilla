package ru.laimcraft.vanilla.components.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.database.ResultSetGetPlayerFarmer;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayerFarmer;

public class CreateAllDataBaseColumns {
    private Player player;
    public CreateAllDataBaseColumns(Player player) {
        this.player = player;
        vanillaPlayer(new MySQLVanillaPlayer().getPlayerName(player.getName()));
        vanillaFarmer(new MySQLVanillaPlayerFarmer().getPlayer(player.getName()));
    }

    private void vanillaPlayer(String login) {
        if(login == null || login.isEmpty()) {
            new MySQLVanillaPlayer().create(player.getName()); return;}
        if(login.equalsIgnoreCase("ex")) {
            exceptionMessage("Exception 101017381321");
            kickPlayer(player);return;}
        if(login.equalsIgnoreCase(player.getName())) return;
    }

    private void vanillaFarmer(ResultSetGetPlayerFarmer resultSetGetPlayerFarmer) {
        if(resultSetGetPlayerFarmer == null) {
            new MySQLVanillaPlayerFarmer().create(player.getName());
            return;}
        String login = resultSetGetPlayerFarmer.getPlayerName();
        if(login == null || login.isEmpty()) {
            new MySQLVanillaPlayerFarmer().create(player.getName()); return;}
        if(login.equalsIgnoreCase("ex")) {
            exceptionMessage("Exception 10104212125");
            kickPlayer(player);return;}
        if(login.equalsIgnoreCase(player.getName())) return;}

    private void exceptionMessage(String ex) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Exception for playerJoinEvents" +
                "[vanillaPlayer] exception-> " + ex);
    }
    private void kickPlayer(Player player) {
        player.kickPlayer(ChatColor.DARK_RED + "Ошибка базы данных обратитесь к администрации за помощью \n" +
                ChatColor.AQUA + "наш дискорд сервер: https://discord.gg/FNJ8ksScmU");
    }
}
