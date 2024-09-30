package ru.laimcraft.vanilla.database.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLBlocks {

    public static boolean create(String position, String blockType, String owner) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`blocks` (`position`, `BlockType`, `owner`) VALUES (" +
                    "'"+position+"','"+blockType+"', '"+owner+"');");
            return true;
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    public static String getBlockOwner(String position) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `owner` FROM `vanilla`.`blocks` WHERE position = '"+position+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public static String getBlockType(String position) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `BlockType` FROM `vanilla`.`blocks` WHERE position = '"+position+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public static boolean removeBlock(String position) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            connection.createStatement().execute("DELETE FROM `vanilla`.`blocks` WHERE (`position` = '"+position+"');");
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}
}
