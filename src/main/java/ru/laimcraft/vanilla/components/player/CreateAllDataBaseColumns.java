package ru.laimcraft.vanilla.components.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Core;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayerFarmer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateAllDataBaseColumns {
    private Core core;
    private Player player;
    public CreateAllDataBaseColumns(Core core, Player player) {
        this.core = core;
        this.player = player;
        vanillaPlayer(new MySQLVanillaPlayer().get(player.getName(), "player"));
        vanillaFarmer(new MySQLVanillaPlayerFarmer().getPlayer(player.getName()));
    }

    private void vanillaPlayer(ResultSet resultSet) {
        try(resultSet) {
            if(resultSet == null) {
                new MySQLVanillaPlayer().create(player.getName()); return;}
            String login = resultSet.getString(1);
            if(login == null || login.isEmpty()) {
                new MySQLVanillaPlayer().create(player.getName());}
            if(login.equalsIgnoreCase("ex")) {
                exceptionMessage("Exception 101017381321");
                kickPlayer(player);}
            if(login.equalsIgnoreCase(player.getName())) return;
        } catch (SQLException ex) {
            exceptionMessage(ex.toString());
            kickPlayer(player);}}

    private void vanillaFarmer(ResultSet resultSet) {
        try(resultSet) {
            if(resultSet == null) {
                new MySQLVanillaPlayer().create(player.getName()); return;}
            String login = resultSet.getString(1);
            if(login == null || login.isEmpty()) {
                new MySQLVanillaPlayerFarmer().create(player.getName());}
            if(login.equalsIgnoreCase("ex")) {
                exceptionMessage("Exception 1010423341");
                kickPlayer(player);}
            if(login.equalsIgnoreCase(player.getName())) return;
        } catch (SQLException ex) {
            exceptionMessage(ex.toString());
            kickPlayer(player);}
    }

    private void exceptionMessage(String ex) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Exception for playerJoinEvents" +
                "[vanillaPlayer] exception-> " + ex);
    }
    private void kickPlayer(Player player) {
        player.kickPlayer(ChatColor.DARK_RED + "Ошибка базы данных обратитесь к администрации за помощью \n" +
                ChatColor.AQUA + "наш дискорд сервер: https://discord.gg/FNJ8ksScmU");
    }
}
