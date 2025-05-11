package ru.laimcraft.vanilla.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLChests {
    private Settings settings = new Settings();

    public boolean create(String position, String owner) {
        try (Connection connection = DataBase.getConnection()) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`chests` (`position`, `owner`) VALUES (" +
                    "'"+position+"', '"+owner+"');");
            return true;
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    public String getChestOwner(String position) {
        try (Connection connection = DataBase.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `owner` FROM `vanilla`.`chests` WHERE position = '"+position+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public boolean removeChest(String position) {
        try (Connection connection = DataBase.getConnection()) {
            connection.createStatement().execute("DELETE FROM `vanilla`.`chests` WHERE (`position` = '"+position+"');");
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}
}
