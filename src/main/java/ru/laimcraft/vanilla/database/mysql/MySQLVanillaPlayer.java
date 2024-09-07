package ru.laimcraft.vanilla.database.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.database.ResultSetGetPlayer;

import java.sql.*;

public class MySQLVanillaPlayer {
    private MySQLSettings settings = new MySQLSettings();
    public MySQLVanillaPlayer() {}

    public boolean create(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`players` (`player`) VALUES ('"+login+"');");
            return true;
    } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}


    public ResultSetGetPlayer getPlayer(String login) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM `vanilla`.`players` WHERE player = '"+login+"' LIMIT 1;");
            while (resultSet.next()) {
                return new ResultSetGetPlayer(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getFloat(10),
                        resultSet.getInt(11)
                );
            } return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}

    /*public ResultSet get(String login, String parameter) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT '"+parameter+"' FROM `vanilla`.`players` WHERE player = '"+login+"' LIMIT 1;");
            return resultSet;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}*/

    public String getPlayerName(String login) {
        String parameter = "player";
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT '"+parameter+"' FROM `vanilla`.`players` WHERE player = '"+login+"' LIMIT 1;");
            while (resultSet.next()) {
                return resultSet.getString(1);
            } return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}

    public void reset(String login, String parameter) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = Max"+parameter+" WHERE player = ?;");
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void add(String login, String parameter, int amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = "+parameter+" + ? WHERE player = ?;");
            ps.setInt(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void remove(String login, String parameter, int amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = "+parameter+" - ? WHERE player = ?;");
            ps.setInt(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void add(String login, String parameter, float amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = "+parameter+" + ? WHERE player = ?;");
            ps.setFloat(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void remove(String login, String parameter, float amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = "+parameter+" - ? WHERE player = ?;");
            ps.setFloat(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void add(String login, String parameter, double amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = "+parameter+" + ? WHERE player = ?;");
            ps.setDouble(1, amount);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("LaimCraft -> MySQL Error: " + ex.toString());}}

    public void remove(String login, String parameter, double amount) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`players` SET `"+parameter+"` = "+parameter+" - ? WHERE player = ?;");
            ps.setDouble(1, amount);
            ps.setString(2, login);
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
