package ru.laimcraft.vanilla.database.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.Core;

import java.sql.*;

public class MySQLChestAccess {
    private MySQLSettings settings = new MySQLSettings();
    private Core core;
    public MySQLChestAccess(Core core) {this.core=core;}

    public boolean create(String owner, String player) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`chestaccess` (`owner`, `players`) VALUES (" +
                    "'"+owner+"', '"+player+"');");
            return true;
    } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    public String getOwner(String owner) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `owner` FROM `vanilla`.`chestaccess` WHERE owner = '"+owner+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public String getPlayers(String owner) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `players` FROM `vanilla`.`chestaccess` WHERE owner = '"+owner+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public String[] getPlayersArray(String owner) {
        String getPlayers = getPlayers(owner);
        if(getPlayers == null) return null;
        return getPlayers.split(":");
    }

    public boolean addPlayer(String owner, String player) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`chestaccess` SET `players` = ? WHERE owner = ?;");
            ps.setString(1, getPlayers(owner) + ":" + player);
            ps.setString(2, owner);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());
            return false;}}

    public boolean removePlayer(String owner, String player) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            String[] players = getPlayersArray(owner);
            if(players == null) return true;
            String newPlayers = null;
            for(String PLAYER : players) {
                if(PLAYER.equalsIgnoreCase(player)) continue;
                //if(!(newPlayers == null || newPlayers.isEmpty())) newPlayers = PLAYER; Ошибка
                if(newPlayers == null) {newPlayers = PLAYER; continue;}
                newPlayers = newPlayers + ":" + PLAYER;}
            if(newPlayers == null) {
                removeOwner(owner);
            return true;}


            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`chestaccess` SET `players` = ? WHERE owner = ?;");
            ps.setString(1, newPlayers);
            ps.setString(2, owner);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());
            return false;}}

    public boolean removeOwner(String owner) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            connection.createStatement().execute("DELETE FROM `vanilla`.`chestaccess` WHERE (`owner` = '"+owner+"');");
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}
}
