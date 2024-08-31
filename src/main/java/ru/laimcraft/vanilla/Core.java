package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.laimcraft.vanilla.Components.Utils;
import ru.laimcraft.vanilla.DataBase.MySQL.MySQLAccounts;

import java.util.ArrayList;

public final class Core extends JavaPlugin {
    public ArrayList<String> AuthPlayers = new ArrayList<>();
    public MySQLAccounts accounts = new MySQLAccounts(this);
    public Utils utils = new Utils(this);
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ru.laimcraft.rp.EventsHandler(this), this);
        CommandsLoader commands = new CommandsLoader(this);
    }

    @Override
    public void onDisable() {

    }
}
