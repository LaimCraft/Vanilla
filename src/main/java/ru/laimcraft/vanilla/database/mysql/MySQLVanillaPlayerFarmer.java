package ru.laimcraft.vanilla.database.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.database.ResultSetGetPlayerFarmer;

import java.sql.*;

public class MySQLVanillaPlayerFarmer {
    private MySQLSettings settings = new MySQLSettings();
    public MySQLVanillaPlayerFarmer() {}

    public boolean create(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`farmer` (`player`) VALUES ('"+login+"');");
            return true;
    } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    public ResultSetGetPlayerFarmer getPlayer(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT 'player' FROM `vanilla`.`farmer` WHERE player = '"+login+"' LIMIT 1;");
            while (resultSet.next()) {
                return new ResultSetGetPlayerFarmer(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
            } return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}

    public void addFarmerLevel(String login, int amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`farmer` SET `Level` = Level + ? WHERE player = ?;");
            ps.setInt(1, amount);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void addFarmerXP(String login, int amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`farmer` SET `XP` = XP + ? WHERE player = ?;");
            ps.setLong(1, amount);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}
/*
    public String getLoginByLogin(String Login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `login` FROM `laimcraft`.`accounts` WHERE login = '"+Login+"';");
            while (resultSet.next()) {
                return resultSet.getString(1).toLowerCase();
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public int getBalance(String Login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `balance` FROM `laimcraft`.`accounts` WHERE login = '"+Login+"';");
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }return -1;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return -1;}}

    public boolean pay(String login, String pay, int amount) {
        removeBalance(login, amount);
        addBalance(pay, amount);
    return true;}

    public void addBalance(String login, int amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `laimcraft`.`accounts` SET `balance` = balance + ? WHERE login = ?;");
            ps.setLong(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void removeBalance(String login, int amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `laimcraft`.`accounts` SET `balance` = balance - ? WHERE login = ?;");
            ps.setLong(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public String getUsernameByLogin(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `login` FROM `laimcraft`.`accounts` WHERE login = '"+login+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public String getPasswordByLogin(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `password` FROM `laimcraft`.`accounts` WHERE login = '"+login+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public boolean authDateUpdate(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            Date date = new Date();
            PreparedStatement ps = connection.prepareStatement("UPDATE `laimcraft`.`accounts` SET `authdate` = ? WHERE login = ?;");
            ps.setLong(1, date.getTime());
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());
            return false;}}*/
}
