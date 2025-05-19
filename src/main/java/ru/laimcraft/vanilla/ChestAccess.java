package ru.laimcraft.vanilla;

import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import ru.laimcraft.vanilla.admin.commands.AdminModeCommand;

import java.util.regex.Pattern;

public class ChestAccess implements Listener, CommandExecutor {
    public ChestAccess() {
        Bukkit.getPluginManager().registerEvents(this, Vanilla.instance);
        Vanilla.instance.getCommand("chest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player player)) return true;
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("mode")) {
                if(Vanilla.chestModePlayers.contains(player.getName())) {
                    Vanilla.chestModePlayers.remove(player.getName());
                    return true;}
                Vanilla.chestModePlayers.add(player.getName());
                return true;}
            if(args[0].equalsIgnoreCase("list")) {
                String[] players = Vanilla.chestAccess.getPlayersArray(player.getName());
                if(players == null) {
                    sender.sendMessage(ChatColor.GOLD + "Вы не давали другим игрокам доступ к сундукам");
                    return true;}
                sender.sendMessage(ChatColor.GREEN + "Список игроков которые имеют доступ к вашим сундукам");
                for(String playerSend : players) {
                    sender.sendMessage(ChatColor.YELLOW + "  " + playerSend);
                }
                return true;}
            return true;}
        if(args.length != 2) {
            sender.sendMessage(ChatColor.GOLD + "Для ввода команды используйте /chest <add/remove> <player>");
            return true;}
        if(args[0].equalsIgnoreCase("add")) {
            if(checkPlayerName(args[1])) {
                String login = Vanilla.accounts.getLoginByLogin(args[1]);
                if(login == null || login.isEmpty()) {
                    player.sendMessage(ChatColor.DARK_RED + "Данный игрок никогда не регистрировался на нашем проекте!");
                    return true;}
                String owner = Vanilla.chestAccess.getOwner(player.getName());
                if(owner == null || owner.isEmpty()) {
                    Vanilla.chestAccess.create(player.getName(), args[1]);
                    player.sendMessage(ChatColor.GREEN + "Вы дали игроку " + args[1] + " доступ к своим сундукам!");
                    return true;}
                Vanilla.chestAccess.addPlayer(player.getName(), args[1]);
                player.sendMessage(ChatColor.GREEN + "Вы дали игроку " + args[1] + " доступ к своим сундукам!");
                return true;}
            player.sendMessage(ChatColor.RED + "Вы ввели неправильно имя пользователя!");
            return true;}
        if(args[0].equalsIgnoreCase("remove")) {
            if(checkPlayerName(args[1])) {
                String login = Vanilla.accounts.getLoginByLogin(args[1]);
                if(login == null || login.isEmpty()) {
                    player.sendMessage(ChatColor.DARK_RED + "Данный игрок никогда не регистрировался на нашем проекте!");
                    return true;}
                boolean chestAccess = Vanilla.chestAccess.removePlayer(player.getName(), args[1]);
                player.sendMessage(ChatColor.RED + "Вы забрали у игрока " + args[1] + " доступ к вашим сундукам!");
                return true;}
            player.sendMessage(ChatColor.RED + "Вы ввели неправильно имя пользователя!");
            return true;}
        sender.sendMessage(ChatColor.GOLD + "Для ввода команды используйте /chest <add/remove> <player>");
        return true;
    }

    private boolean checkPlayerName(String player) {
        if(player == null || player.isEmpty()) return false;
        if(player.length() < 3 || player.length() > 16) return false;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$", player)) return false;
        return true;}

    @EventHandler
    private void onBlockBreakEvent(BlockBreakEvent event) {
        chestRemove(event);
    }

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event) {
        Inventory destination = event.getDestination();
        Inventory source = event.getSource();
        Location location = source.getLocation();
        if (location == null) return;
        Material material = location.getBlock().getType();
        if (material == Material.HOPPER) return;
        if (!Vanilla.blockInventory.getInventoryBlocks().contains(material)) return;
        if (destination.getHolder() instanceof HopperMinecart minecart) event.setCancelled(true);
    }

    private void chestRemove(BlockBreakEvent event) {
        if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            String owner = Vanilla.chests.getChestOwner(event.getBlock().getWorld().getName() + ":" +
                    event.getBlock().getX() + ":" + event.getBlock().getY() + ":" + event.getBlock().getZ());
            if(owner == null || owner.isEmpty()) return;
            if(owner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
            if(!owner.equalsIgnoreCase(event.getPlayer().getName())) {event.setCancelled(true); return;}
            Vanilla.chests.removeChest(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":" + event.getBlock().getZ());}
    }

    @EventHandler
    private void onBlockPlaceEvent(BlockPlaceEvent event) {
        if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
            if(!hopperPlace(event)) {
                event.setCancelled(true);
                return;}
            Vanilla.chests.create(event.getBlock().getWorld().getName() + ":" + event.getBlock().getX() + ":"
                    + event.getBlock().getY() + ":"  + event.getBlock().getZ(), event.getPlayer().getName());}
    }

    private boolean hopperPlace(BlockPlaceEvent event) {
        if(event.getBlock().getType() == Material.HOPPER) {
            Location location = new Location(
                    event.getBlock().getWorld(),
                    event.getBlock().getX(),
                    event.getBlock().getY() + 1,
                    event.getBlock().getZ()
            );
            Block block = event.getBlock().getWorld().getBlockAt(location);
            if(Vanilla.blockInventory.getInventoryBlocks().contains(event.getBlock().getType())) {
                String owner =
                        Vanilla.chests.getChestOwner
                                (block.getWorld().getName() + ":" + block.getX() + ":" + block.getY() + ":" + block.getZ());
                if(owner == null) return true;
                if(owner.equals(event.getPlayer().getName())) return true;

                String[] players = Vanilla.chestAccess.getPlayersArray(owner);
                if(players == null) return false;

                for(String player : players) {
                    if(player.equals(event.getPlayer().getName())) return true;
                }
                return false;
            }
            return true;
        }
        return true;
    }
    //ТНТ КРИПЕР КРИСТАЛЛ ЭНДА
    @EventHandler
    protected void onBlockExplode(EntityExplodeEvent event) {
        for(Block block : event.blockList()) {
            if(!Vanilla.blockInventory.getInventoryBlocks().contains(block.getType())) continue;
            String owner = Vanilla.chests.getChestOwner(
                    block.getWorld().getName()
                            + ":" +
                            block.getX()
                            + ":" +
                            block.getY()
                            + ":" +
                            block.getZ()
            );
            if(owner == null) continue;
            event.blockList().remove(block);
        }
    }

    //КРОВАТЬ
    @EventHandler
    protected void onBlockExplode(BlockExplodeEvent event) {
        for(Block block : event.blockList()) {
            if(!Vanilla.blockInventory.getInventoryBlocks().contains(block.getType())) continue;
            String owner = Vanilla.chests.getChestOwner(
                    block.getWorld().getName()
                            + ":" +
                            block.getX()
                            + ":" +
                            block.getY()
                            + ":" +
                            block.getZ()
            );
            if(owner == null) continue;
            event.blockList().remove(block);
        }
    }

    @EventHandler
    private void onInventoryOpenEvents(InventoryOpenEvent event) {
        if(event.getInventory().getLocation() == null) return;
        Block block = event.getInventory().getLocation().getBlock();
        if(AdminModeCommand.adm && event.getPlayer().getName().equals("limeworld")) return;
        String chestOwner = Vanilla.chests.getChestOwner(block.getWorld().getName() + ":" + block.getX() + ":" + block.getY() + ":" + block.getZ());
        if(chestOwner == null || chestOwner.isEmpty()) return;
        if(chestOwner.equalsIgnoreCase("ex")) {event.setCancelled(true);return;}
        if(chestOwner.equalsIgnoreCase(event.getPlayer().getName())) {
            if(Vanilla.chestModePlayers.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "+");
            }
            return;}
        boolean es = false;
        String[] players = Vanilla.chestAccess.getPlayersArray(chestOwner);
        if(players == null) {event.setCancelled(true); return;}
        for(String s : players) {if(s.equalsIgnoreCase(event.getPlayer().getName())) es = true;}
        if(es) return;
        event.setCancelled(true);
    }

    @EventHandler
    private void PistonCheck(BlockPistonExtendEvent event) {
        for(Block block : event.getBlocks()) {
            if(!Vanilla.blockInventory.getInventoryBlocks().contains(block.getType())) continue;
            String owner = Vanilla.chests.getChestOwner(
                    block.getWorld().getName()
                            + ":" +
                            block.getX()
                            + ":" +
                            block.getY()
                            + ":" +
                            block.getZ()
            );
            if(owner == null) continue;
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    private void PistonCheck(BlockPistonRetractEvent event) {
        for(Block block : event.getBlocks()) {
            if(!Vanilla.blockInventory.getInventoryBlocks().contains(block.getType())) continue;
            String owner = Vanilla.chests.getChestOwner(
                    block.getWorld().getName()
                            + ":" +
                            block.getX()
                            + ":" +
                            block.getY()
                            + ":" +
                            block.getZ()
            );
            if(owner == null) continue;
            event.setCancelled(true);
            return;
        }
    }
}
