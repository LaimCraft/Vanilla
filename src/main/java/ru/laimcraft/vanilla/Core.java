package ru.laimcraft.vanilla;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.laimcraft.vanilla.components.BlockInventory;
import ru.laimcraft.vanilla.components.CraftManager.CraftMaterialAllowed;
import ru.laimcraft.vanilla.components.CraftManager.MagicCraftingTable;
import ru.laimcraft.vanilla.components.CraftManager.Crafts;
import ru.laimcraft.vanilla.components.player.PlayerStatus;
import ru.laimcraft.vanilla.database.mysql.MySQLAccounts;
import ru.laimcraft.vanilla.database.mysql.MySQLChestAccess;
import ru.laimcraft.vanilla.database.mysql.MySQLChests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Core extends JavaPlugin {
    public static HashMap<String, PlayerStatus> players = new HashMap<>();
    public static HashMap<String, MagicCraftingTable> MagicCraftingTablePlayers = new HashMap<>();
    public static CraftMaterialAllowed craftMaterialAllowed = new CraftMaterialAllowed();
    public static Crafts crafts = new Crafts();
    public List<String> vanishPlayers = new ArrayList<>();
    public List<String> chestModePlayers = new ArrayList<>();
    public static ArrayList<String> AuthPlayers = new ArrayList<>();
    public MySQLAccounts accounts = new MySQLAccounts(this);
    public MySQLChestAccess chestAccess = new MySQLChestAccess(this);
    public MySQLChests chests = new MySQLChests(this);
    public BlockInventory blockInventory = new BlockInventory();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventsHandler(this), this);
        CommandsLoader commands = new CommandsLoader(this);
        CraftManager craftManager = new CraftManager();
        //ScoreboardUpdateModule scoreboardUpdateModule = new ScoreboardUpdateModule(this);
    }

    @Override
    public void onDisable() {

    }
}
