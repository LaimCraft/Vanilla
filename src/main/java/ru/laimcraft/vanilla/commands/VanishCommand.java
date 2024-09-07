package ru.laimcraft.vanilla.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.laimcraft.utils.Utils;
import ru.laimcraft.vanilla.Core;

import java.util.regex.Pattern;

public class VanishCommand implements CommandExecutor {
    private Core core;
    public VanishCommand(Core core) {this.core=core;}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(1 == 1) return true;
        if(!(sender instanceof Player player)) return true;
        if(core.vanishPlayers.contains(player.getName())) {
            core.vanishPlayers.remove(player.getName());
            Utils.vanillaTabColorUpdate(player);
            for(Player playerSend : Bukkit.getOnlinePlayers()) {
                if(playerSend.getName().equalsIgnoreCase(player.getName())) continue;
                playerSend.sendMessage(ChatColor.DARK_GREEN + player.getPlayer().getName());}
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        return true;}
        core.vanishPlayers.add(player.getName());
        player.setPlayerListName("");
        for(Player playerSend : Bukkit.getOnlinePlayers()) {
            if(playerSend.getName().equalsIgnoreCase(player.getName())) continue;
            playerSend.sendMessage(ChatColor.DARK_RED + player.getPlayer().getName());}
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, PotionEffect.INFINITE_DURATION, 255, true));
    return true;}

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
    return true;}
}
