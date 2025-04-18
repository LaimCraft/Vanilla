package ru.laimcraft.vanilla.admin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import ru.laimcraft.vanilla.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HomeCommand implements CommandExecutor, Listener {

    public static final String home = "home";
    public static List<UUID> adminModePlayers = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof final Player player)) return true;

        final String home = (args.length > 0) ? args[0] : HomeCommand.home;
        if(s.equals(HomeCommand.home)) {
            switch (home) {
                case "list":
                    player.sendMessage(String.format("%sСкоро....", ChatColor.GOLD));
                    return true;
                case "delete":
                    player.sendMessage(String.format("%sСкоро.... пока что есть лишь перезапись точки дома с названием что вы ввели", ChatColor.GOLD));
                    return true;
                case "help":
                    player.sendMessage(String.format("%sДля создания точки дома введите /sethome <Название>", ChatColor.GOLD));
                    player.sendMessage(String.format("%Для удаления точки дома введите /home delete <Название>", ChatColor.GOLD));
                    player.sendMessage(String.format("%Для того что бы узнать сколько у вас точек дома введите /home list", ChatColor.GOLD));
                    player.sendMessage(String.format("%Для изменения существующей точки дома просто создайте его заново с тем же именем", ChatColor.GOLD));
                    player.sendMessage(String.format("%Учтите что если вы не водили название точки дома оно будет по умолчанию: home", ChatColor.GOLD));
                    return true;
                default:
                    HomeManager.get(player.getName(), home).ifPresentOrElse(player::teleport, () -> {
                        player.sendMessage(String.format("%sУ вас нет дома с этим названием", ChatColor.RED));
                    });
                    return true;
            }
        } else {
            HomeManager.get(player.getName(), home).ifPresentOrElse((location -> {
                if(HomeManager.change(player.getName(), home, player.getLocation())) {
                    player.sendMessage(String.format("%sЭта точка дома уже существовала по этому её координаты были изменены", ChatColor.GREEN));
                } else {
                    player.sendMessage(String.format("%sПроизошла неизвестная ошибка возможно точка дома была изменена а возможно что то пошло не так", ChatColor.RED));
                    player.sendMessage(String.format("%sПожалуйста обратитесь к Администрации за помощью", ChatColor.RED));
                }
            }), () -> {
                if(HomeManager.create(player.getName(), home, player.getLocation())) {
                    player.sendMessage(String.format("%sНовая точка дома успешно создана!", ChatColor.GREEN));
                } else {
                    player.sendMessage(String.format("%sПроизошла неизвестная ошибка возможно точка дома была создана а возможно что то пошло не так", ChatColor.RED));
                    player.sendMessage(String.format("%sПожалуйста обратитесь к Администрации за помощью", ChatColor.RED));
                }
            });
        }
        return true;
    }

    public static class HomeManager {

        public static boolean change(String username, String name, Location location) {
            try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)){
                PreparedStatement ps = connection.prepareStatement("UPDATE `vanilla`.`home` SET `world` = ?, `x` = ?, `y` = ?, `z` = ?, `yaw` = ?, `pitch` = ? WHERE (`login` = ?) and (`name` = ?);");
                ps.setString(1, location.getWorld().getName());
                ps.setDouble(2, location.getX());
                ps.setDouble(3, location.getY());
                ps.setDouble(4, location.getZ());
                ps.setFloat(5, location.getYaw());
                ps.setFloat(6, location.getPitch());
                ps.setString(7, username);
                ps.setString(8, name);

                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
                return false;
            }
        }

        public static boolean create(String username, String name, Location location) {
            try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)){
                PreparedStatement ps = connection.prepareStatement("INSERT INTO `vanilla`.`home` (`login`, `name`, `world`, `x`, `y`, `z`, `yaw`, `pitch`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
                ps.setString(1, username);
                ps.setString(2, name);
                ps.setString(3, location.getWorld().getName());
                ps.setDouble(4, location.getX());
                ps.setDouble(5, location.getY());
                ps.setDouble(6, location.getZ());
                ps.setFloat(7, location.getYaw());
                ps.setFloat(8, location.getPitch());

                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
                return false;
            }
        }

        public static Optional<Location> get(String username, String name) {
            try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)){
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM `vanilla`.`home` WHERE `login` = ? and `name` = ?");
                ps.setString(1, username);
                ps.setString(2, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String world = rs.getString("world");
                    if(world == null || world.isEmpty()) return Optional.empty();
                    return Optional.of(new Location(
                            Bukkit.getWorld(world),
                            rs.getDouble("x"),
                            rs.getDouble("y"),
                            rs.getDouble("z"),
                            rs.getFloat("yaw"),
                            rs.getFloat("pitch")
                    ));
                } return Optional.empty();
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
                return Optional.empty();
            }
        }

    }
}
