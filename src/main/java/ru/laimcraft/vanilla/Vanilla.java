package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.laimcraft.vanilla.admin.commands.*;
import ru.laimcraft.vanilla.custom.enchantments.Pickaxe3x3;
import ru.laimcraft.vanilla.mysql.MySQLAccounts;
import ru.laimcraft.vanilla.mysql.MySQLChestAccess;
import ru.laimcraft.vanilla.mysql.MySQLChests;
import ru.laimcraft.vanilla.world.WorldLoader;

import java.util.ArrayList;
import java.util.List;

public final class Vanilla extends JavaPlugin {

    public static MySQLAccounts accounts = new MySQLAccounts();
    public static BlockInventory blockInventory = new BlockInventory();
    public static List<String> chestModePlayers = new ArrayList<>();
    public static MySQLChestAccess chestAccess = new MySQLChestAccess();
    public static OnlineListener onlineListener = new OnlineListener();
    public static MySQLChests chests = new MySQLChests();
    public static Vanilla instance;

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        instance = this;
        new DataBase();
        new ChestAccess();
        new TabAndChatColorManager();
        new WorldLoader();
        new CustomItems();
        //new Proverka();

        Bukkit.getPluginManager().registerEvents(new Pickaxe3x3(), this);

        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new gmCommand());
        getCommand("stop").setExecutor(new StopCommand());
        getCommand("ch").setExecutor(new CHCommand());
        HomeCommand homeCommand = new HomeCommand();
        getCommand("home").setExecutor(homeCommand);
        getCommand("sethome").setExecutor(homeCommand);
        getCommand("adminmode").setExecutor(new AdminModeCommand());
    }

    @Override
    public void onDisable() {

    }
}
