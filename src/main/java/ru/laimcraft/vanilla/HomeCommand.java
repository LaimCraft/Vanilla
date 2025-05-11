package ru.laimcraft.vanilla;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

public class HomeCommand {

    public static final String home = "home";
    public static List<UUID> adminModePlayers = new ArrayList<>();
    public static List<String> banNames = new ArrayList<>(4);
    public static int maxHomeCount = 10;

    static {
        banNames.add("list");
        banNames.add("delete");
        banNames.add("player");
        banNames.add("help");
    }

    public static void onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof final Player player)) return;

        final String home = (args.length > 0) ? args[0] : HomeCommand.home;
        if(s.equals(HomeCommand.home)) {
            switch (home.toLowerCase()) {
                case "list":
                    var homes = HomeManager.getAll(player.getName());
                    if(homes.size() == -1) {
                        player.sendMessage(String.format("%sПроизошла неизвестная ошибка", ChatColor.RED));
                        player.sendMessage(String.format("%sПожалуйста обратитесь к Администрации за помощью", ChatColor.RED));
                        return;
                    } else if(homes.isEmpty()) {
                        player.sendMessage(String.format("%sУ вас нет ни одной точки дома", ChatColor.GOLD));
                        return;
                    } else {
                        player.sendMessage(String.format("%sСписок точек дома", ChatColor.DARK_GREEN));
                        int i = 1;
                        for(Map.Entry<String, Location> entry : homes.entrySet()) {
                            Location location = entry.getValue();
                            player.sendMessage(String.format("%s%s. %s x:%s y:%s z:%s  мир %s%s",
                                    ChatColor.GREEN,
                                    i++,
                                    entry.getKey(),
                                    location.getBlockX(),
                                    location.getBlockY(),
                                    location.getBlockZ(),
                                    Utils.vanillaGetTabColor(location.getWorld().getName()),
                                    location.getWorld().getName()));
                        }
                    }
                    return;
                case "delete":
                    String homeName = args[1];
                    if (homeName == null || homeName.isEmpty()) {
                        player.sendMessage(String.format("%sПожалуйста введите название дома который хотите удалить", ChatColor.RED));
                        return;
                    }
                    if(HomeManager.get(player.getName(), homeName).isEmpty()) {
                        player.sendMessage(String.format("%sУ вас нет точки дома с этим именем", ChatColor.RED));
                        return;
                    }
                    if(HomeManager.delete(player.getName(), homeName)) {
                        player.sendMessage(String.format("%sВы успешно удалили точку дома %s", ChatColor.GREEN, homeName));
                        return;
                    } else {
                        player.sendMessage(String.format("%sПри попытке удалить точку дома произошла неизвестная ошибка", ChatColor.RED));
                        player.sendMessage(String.format("%sПожалуйста обратитесь к Администрации за помощью", ChatColor.RED));
                        return;
                    }
                case "help":
                    player.sendMessage(String.format("%sДля создания точки дома введите /sethome <Название>", ChatColor.GOLD));
                    player.sendMessage(String.format("%sДля удаления точки дома введите /home delete <Название>", ChatColor.GOLD));
                    player.sendMessage(String.format("%sДля того что бы узнать сколько у вас точек дома введите /home list", ChatColor.GOLD));
                    player.sendMessage(String.format("%sДля изменения существующей точки дома просто создайте его заново с тем же именем", ChatColor.GOLD));
                    player.sendMessage(String.format("%sУчтите что если вы не водили название точки дома оно будет по умолчанию: home", ChatColor.GOLD));
                    return;
                default:
                    HomeManager.get(player.getName(), home).ifPresentOrElse(player::teleport, () -> {
                        player.sendMessage(String.format("%sУ вас нет дома с этим названием", ChatColor.RED));
                    });
                    return;
            }
        } else {
            if(banNames.contains(home.toLowerCase())) {
                player.sendMessage(String.format("%sЭто зарезервированное название под команды home выберите другое", ChatColor.YELLOW));
                return;
            }

            int homeCount = HomeManager.count(player.getName());
            if(homeCount == -1) {
                player.sendMessage(String.format("%sПроизошла неизвестная ошибка", ChatColor.RED));
                player.sendMessage(String.format("%sПожалуйста обратитесь к Администрации за помощью", ChatColor.RED));
                return;
            } if (homeCount >= maxHomeCount) {
                //home Название которое ввёл игрок
                Optional<Location> oldHomeLocation = HomeManager.get(player.getName(), home);
                if(oldHomeLocation.isEmpty()) {
                    player.sendMessage(String.format("%sУ вас уже максимальное кол-во точек дома", ChatColor.RED));
                    return;
                }
            }

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
        return;
    }

    public static void onCommand(CommandContext<CommandSourceStack> context) {
        
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

        public static boolean delete(String username, String name) {
            try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)){
                PreparedStatement ps = connection.prepareStatement("DELETE FROM `vanilla`.`home` WHERE `login` = ? and `name` = ?;");
                ps.setString(1, username);
                ps.setString(2, name);

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

        public static HashMap<String, Location> getAll(String username) {
            HashMap<String, Location> locations = new HashMap<>(1, 1.0F);
            try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)){
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM `vanilla`.`home` WHERE `login` = ?"); //Пропустить это OFFSET 10 после LIMIT
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String world = rs.getString("world");
                    if(world == null || world.isEmpty()) continue;
                    Location location = new Location(
                            Bukkit.getWorld(world),
                            rs.getDouble("x"),
                            rs.getDouble("y"),
                            rs.getDouble("z"),
                            rs.getFloat("yaw"),
                            rs.getFloat("pitch")
                    );

                    locations.put(rs.getString("name"), location);
                } return locations;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
                return locations;
            }
        }

        public static int count(String username) {
            try (Connection connection = DriverManager.getConnection(Settings.host, Settings.user, Settings.password)){
                PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM `vanilla`.`home` WHERE `login` = ?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                } return -1;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
                return -1;
            }
        }

    }
}
