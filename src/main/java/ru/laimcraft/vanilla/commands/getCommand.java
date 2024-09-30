package ru.laimcraft.vanilla.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.Vanilla;
import ru.laimcraft.vanilla.components.CraftManager.Items;

import java.util.regex.Pattern;

public class getCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if(!player.getName().equals("limeworld")) return true;
        if(args.length == 0) return true;
        switch (args[0].toLowerCase()) {
            case "magicapple":
                player.getInventory().addItem(Items.getMagicApple());
                return true;
            case "magiccraftingtable":
                player.getInventory().addItem(Items.getMagicCraftingTable());
            default:
                return true;
        }
    }

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        if(Character.isDigit(player.charAt(0))) return false;
    return true;}
}
