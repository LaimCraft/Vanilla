package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.laimcraft.vanilla.Components.BlockInventory;
import ru.laimcraft.vanilla.Components.Utils;
import ru.laimcraft.vanilla.DataBase.MySQL.MySQLAccounts;
import ru.laimcraft.vanilla.DataBase.MySQL.MySQLChestAccess;
import ru.laimcraft.vanilla.DataBase.MySQL.MySQLChests;

import java.net.Inet4Address;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class Core extends JavaPlugin {
    public List<String> vanishPlayers = new ArrayList<>();
    public List<String> chestModePlayers = new ArrayList<>();
    public static ArrayList<String> AuthPlayers = new ArrayList<>();
    public MySQLAccounts accounts = new MySQLAccounts(this);
    public MySQLChestAccess chestAccess = new MySQLChestAccess(this);
    public MySQLChests chests = new MySQLChests(this);
    public Utils utils = new Utils(this);
    public BlockInventory blockInventory = new BlockInventory();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ru.laimcraft.rp.EventsHandler(this), this);
        CommandsLoader commands = new CommandsLoader(this);

    }

    @Override
    public void onDisable() {

    }
}
