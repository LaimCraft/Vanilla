package ru.laimcraft.vanilla.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import ru.laimcraft.vanilla.Vanilla;

import java.util.HashMap;
import java.util.UUID;

public class Proverka implements CommandExecutor, Listener {

    public Proverka() {
        Bukkit.getPluginManager().registerEvents(this, Vanilla.instance);
        Vanilla.instance.getCommand("proverka").setExecutor(this);
    }

    public HashMap<UUID, Integer> players = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof final Player player)) return true;
        if(args.length != 2) {
            if(args.length != 1) {
                player.sendMessage(ChatColor.YELLOW + "Введите /proverka <ник> <время бана в днях>");
                return true;
            }
            Player player2 = Bukkit.getPlayer(args[0]);
            if(player2 == null) {
                player.sendMessage("Игрока не в сети");
                return true;
            }
            if(players.containsKey(player2.getUniqueId())) {
                player.sendMessage(ChatColor.GOLD + "Игрок больше не на проверке");
                player2.sendMessage(ChatColor.DARK_GREEN + "Вы прошли проверку");

                players.remove(player2.getUniqueId());
                return true;
            }
            player.sendMessage(ChatColor.RED + "Игрок не находится на проверке");
            return true;}
        Player player2 = Bukkit.getPlayer(args[0]);
        if(player2 == null) {
            player.sendMessage(ChatColor.RED + "Игрока с таким ником нет на сервере");
            return true;
        }
        int banTime = 0;
        try {
            banTime = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Вы ввели неправильно время бана");
        }
        if(players.containsKey(player2.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Игрок уже на проверке введите /proverka <ник> что бы её снять");
            return true;
        }
        players.put(player2.getUniqueId(), banTime);

        sendInfoMessages(player, player2, banTime);
        return true;
    }

    private void sendInfoMessages(Player playerAdmin, Player player, int banTime) {
        player.sendMessage(ChatColor.RED + "Вас вызвали на проверку");
        player.sendMessage(ChatColor.RED + "не выходите из игры иначе получите бан (сроком "+banTime+" дней)");
        player.sendMessage(ChatColor.DARK_AQUA + "Вас вызвал на проверку администратор " + playerAdmin.getName());
        player.sendMessage(ChatColor.GREEN + "Discord сервер для прохождения проверки на читы: https://discord.gg/U7D8vzK6XS");
        player.sendMessage(ChatColor.AQUA + "Если у вас нет дс можно через телеграм но сообщите об этом администратору");
        player.sendMessage(ChatColor.AQUA + "Если у вас нет дс можно через телеграм но сообщите об этом администратору");
    }

    @EventHandler
    private void quit(PlayerQuitEvent event) {
        if(players.containsKey(event.getPlayer().getUniqueId())) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    new StringBuilder()
                            .append("tempban ")
                            .append(event.getPlayer().getName())
                            .append(" ")
                            .append(players.get(event.getPlayer().getUniqueId()))
                            .append("d Выход из игры во время проверки на читы")
                            .toString()
            );
            players.remove(event.getPlayer().getUniqueId());
            return;
        }
    }

    @EventHandler
    private void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof final Player player)) return;
        if(!players.containsKey(player.getUniqueId())) return;
        event.setCancelled(true);
    }

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        if(!players.containsKey(event.getPlayer().getUniqueId())) return;
        event.setCancelled(true);
    }
}
