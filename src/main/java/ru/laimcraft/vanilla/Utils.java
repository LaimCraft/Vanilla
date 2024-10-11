package ru.laimcraft.vanilla;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Pattern;

public class Utils {
    public static String getSHA512(String input){
        String toReturn = null; try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));} catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(e.toString());}return toReturn;}

    public static boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        //if(Character.isDigit(player.charAt(0))) return false;
        return true;}

    public static boolean checkRegionName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 1 || player.length() > 24) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
        return true;}

    public static boolean checkRegionNameOtClick(Player player, String regionName) {
        if(regionName == null || regionName.isEmpty()) {
            player.sendMessage(ChatColor.RED + "Видимо вы забыли написать название региона или произошла системная ошибка");
            return false;
        }
        if(regionName.length() < 3 || regionName.length() > 24) {
            player.sendMessage(ChatColor.RED + "Название региона не может быть меньше 3 символов и больше 24 символов!");
            return false;
        }
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", regionName)) {
            player.sendMessage(ChatColor.RED + "Вы ввели запрещённые символы в название региона");
            player.sendMessage(ChatColor.GREEN + "Разрещённые символы: " + ChatColor.DARK_GREEN + "a-z, A-Z, 0-9, _");
            return false;
        }
        //if(Character.isDigit(regionName.charAt(0))) return false;
        return true;}

    public static boolean checkInteger(String Integer) {
        if(Integer == null || Integer.isEmpty()) return false;
        if(Integer.length() > 6) return false;
        if(!Pattern.matches("^[0-9]+$", Integer)) return false;
        if(!Character.isDigit(Integer.charAt(0))) return false;
        return true;}

    public static void sendTransferMessage(Plugin plugin, Player player, String serverName) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("YAm3Q5pioe4q");
        output.writeUTF("transfer");
        output.writeUTF(serverName);
        output.writeUTF(player.getName());
        player.sendPluginMessage(plugin, "server:transfer", output.toByteArray());
    }

    public static void vanillaTabColorUpdate(Player player) {
        String world = player.getLocation().getWorld().getName();

        if(world.equalsIgnoreCase("world")) {
            player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
            return;}
        if(world.equalsIgnoreCase("world_nether")) {
            player.setPlayerListName(ChatColor.RED + player.getName());
            return;}
        if(world.equalsIgnoreCase("world_the_end")) {
            player.setPlayerListName(ChatColor.GOLD + player.getName());
            return;}}

    public static String vanillaGetTabColor(Player player) {
        String world = player.getLocation().getWorld().getName();

        if(world.equalsIgnoreCase("world")) {
            return ChatColor.DARK_GREEN + "";}
        if(world.equalsIgnoreCase("world_nether")) {
            return ChatColor.RED + "";}
        if(world.equalsIgnoreCase("world_the_end")) {
            return ChatColor.GOLD + "";}
        return ChatColor.WHITE + "";}


    public static Location getLocation(World world, int x, int y, int z) {
        Location location = new Location(
                world,
                585,
                63,
                39
        );
        return location;
    }
    public static Location getLocation(String worldName, int x, int y, int z) {
        Location location = new Location(
                Bukkit.getWorld(worldName),
                585,
                63,
                39
        );
        return location;
    }

    public static Location getLocationUp(Location location) {
        Location loc = new Location(
                location.getWorld(),
                location.getX(),
                location.getY() + 1,
                location.getZ()
        );
        return loc;
    }
    public static Location getLocationDown(Location location) {
        Location loc = new Location(
                location.getWorld(),
                location.getX(),
                location.getY() - 1,
                location.getZ()
        );
        return loc;
    }
}