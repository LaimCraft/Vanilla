package ru.laimcraft.vanilla.Components;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Core;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Utils {
    private Core core;
    public Utils(Core core) {this.core=core;}

    public String getSHA512(String input){
        String toReturn = null; try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));} catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(e.toString());}return toReturn;}

    public void tabColorUpdate(Player player) {
        if(core.vanishPlayers.contains(player.getName())) return;
        String world = player.getLocation().getWorld().getName();
        if(world.equalsIgnoreCase("world")) {
            player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
        return;}
        if(world.equalsIgnoreCase("world_nether")) {
            player.setPlayerListName(ChatColor.RED + player.getName());
        return;}
        if(world.equalsIgnoreCase("world_the_end")) {
            player.setPlayerListName(ChatColor.GOLD + player.getName());
        return;}
    }
}
