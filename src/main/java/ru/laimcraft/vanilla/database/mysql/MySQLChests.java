package ru.laimcraft.vanilla.database.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.Core;

import java.sql.*;

public class MySQLChests {
    private MySQLSettings settings = new MySQLSettings();
    private Core core;
    public MySQLChests(Core core) {this.core=core;}

    public boolean create(String position, String owner) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`chests` (`position`, `owner`) VALUES (" +
                    "'"+position+"', '"+owner+"');");
            return true;
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    public String getChestOwner(String position) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `owner` FROM `vanilla`.`chests` WHERE position = '"+position+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public boolean removeChest(String position) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            connection.createStatement().execute("DELETE FROM `vanilla`.`chests` WHERE (`position` = '"+position+"');");
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}
}
