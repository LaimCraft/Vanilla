package ru.laimcraft.vanilla;

import org.bukkit.plugin.java.JavaPlugin;
import ru.laimcraft.vanilla.admin.Proverka;
import ru.laimcraft.vanilla.admin.commands.*;
import ru.laimcraft.vanilla.mysql.MySQLAccounts;
import ru.laimcraft.vanilla.mysql.MySQLChestAccess;
import ru.laimcraft.vanilla.mysql.MySQLChests;

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
    public void onEnable() {
        instance = this;
        new ChestAccess();
        new TabAndChatColorManager();
        //new Proverka();

        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new gmCommand());
        getCommand("stop").setExecutor(new StopCommand());
        getCommand("ch").setExecutor(new CHCommand());
        HomeCommand homeCommand = new HomeCommand();
        getCommand("home").setExecutor(homeCommand);
        getCommand("sethome").setExecutor(homeCommand);
    }

    @Override
    public void onDisable() {

    }
}
