package ru.laimcraft.vanilla.database.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.components.regions.RegionChunk;
import ru.laimcraft.vanilla.components.regions.RegionFlag;
import ru.laimcraft.vanilla.components.regions.RegionFlagResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MySQLRegions {

    public static String getRegionOwner(String region) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT `owner` FROM `vanilla`.`regions` WHERE region = '"+region+"';");
            while (rs.next()) {
                return rs.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public static List<String> getRegionMembers(String region) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT `members` FROM `vanilla`.`regions` WHERE region = '"+region+"';");
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String[] members = rs.getString(1).split(":");
                for(String member : members) {
                    list.add(member);}
            }return list;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}

    public static RegionFlagResult getRegionFlag(String region, RegionFlag regionFlag) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT `"+regionFlag.getId()+"` FROM `vanilla`.`regions` WHERE region = '"+region+"';");
            while (rs.next()) {
                return RegionFlagResult.valueOf(rs.getString(1));
            }return RegionFlagResult.NULL;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return RegionFlagResult.Ex;}}

    public static HashMap<RegionFlag, RegionFlagResult>  getRegionFlags(String region) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT 'County', 'SpawnMobs' FROM `vanilla`.`regions` WHERE region = '"+region+"';");
            HashMap<RegionFlag, RegionFlagResult> regionFlags = new HashMap<>();
            while (rs.next()) {
                regionFlags.put(RegionFlag.valueOf("Country"), RegionFlagResult.valueOf(rs.getString(1)));
                regionFlags.put(RegionFlag.valueOf("SpawnMobs"), RegionFlagResult.valueOf(rs.getString(2)));
                return regionFlags;
            }return regionFlags;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}

    public static boolean create(String region, String owner) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`regions` (`region`, `owner`) VALUES (" +
                    "'"+region+"', '"+owner+"');");
            return true;
        } catch (SQLException ex) {
            if(getRegionOwner(region) != null || !getRegionOwner(region).equals("ex")) return true;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    /*public static String getRegion(String world, int x, int z) {
        String position = world + ":" + x + ":" + z;
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT `region` FROM `vanilla`.`chunks` WHERE position = '"+position+"';");
            while (resultSet.next()) {
                return resultSet.getString(1);
            }return null;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return "ex";}}

    public List<RegionChunk> getChunks(String region) {
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT position FROM `vanilla`.`chunks` WHERE region = '"+region+"';");
            List<RegionChunk> regionChunks = new ArrayList<>();
            while (rs.next()) {
                String[] data = rs.getString(1).split(":");
                regionChunks.add(
                        new RegionChunk(region, data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }return regionChunks;
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return null;}}

    public static boolean create(String region, String world, int x, int z) {
        String position = world + ":" + x + ":" + z;
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            connection.createStatement().executeUpdate("INSERT INTO `vanilla`.`chunks` (`position`, `region`) VALUES (" +
                    "'"+position+"', '"+region+"');");
            return true;
        } catch (SQLException ex) {
            if(getRegion(world, x, z).equals(region)) return true;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

    public static void delete(String world, int x, int z) {
        String position = world + ":" + x + ":" + z;
        try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)) {
            connection.createStatement().execute("DELETE FROM `vanilla`.`chunks` WHERE (`position` = '"+position+"');");
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
        }}



*/
    /*public boolean create(String login, String password) {
        try (Connection connection = DriverManager.getConnection(settings.host, settings.user, settings.password)) {
            Date date = new Date();
            connection.createStatement().executeUpdate("INSERT INTO `laimcraft`.`accounts` (`login`, `password`, `regdate`, `authdate`) VALUES (" +
                    "'"+login+"', '"+password+"', '"+date.getTime()+"', '"+date.getTime()+"');");
            return true;
    } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MySQL ERROR: " + ex);
            return false;}}

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
