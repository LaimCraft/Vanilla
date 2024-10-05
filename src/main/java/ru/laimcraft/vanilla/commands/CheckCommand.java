package ru.laimcraft.vanilla.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.Items;

import java.util.regex.Pattern;

public class CheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if(!player.getName().equals("limeworld")) return true;
        if(player.getInventory().getItem(EquipmentSlot.HAND) == Items.getMagicAppleMaxMP()) {
            player.sendMessage(ChatColor.GREEN + "yes");
        return true;}
        return true;
    }

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
    return true;}
}
