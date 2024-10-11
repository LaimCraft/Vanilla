package ru.laimcraft.vanilla.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.components.CraftManager.Items;

import java.util.regex.Pattern;

public class GetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if(!player.getName().equals("limeworld")) return true;
        if(args.length == 0) return true;
        switch (args[0].toLowerCase()) {
            case "magicapple":
                player.getInventory().addItem(Items.getMagicAppleMaxMP());
                return true;
            case "magiccraftingtable":
                player.getInventory().addItem(Items.getMagicCraftingTable());
                return true;
            case "elytra":
                player.getInventory().addItem(Items.getElytra());
                return true;
            case "magicelytra":
                player.getInventory().addItem(Items.getMagicElytra());
                return true;
            case "magicsweetberries":
                player.getInventory().addItem(Items.getMagicSweetBerriesMaxMP());
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
