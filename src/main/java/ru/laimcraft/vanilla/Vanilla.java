package ru.laimcraft.vanilla;

import org.bukkit.plugin.java.JavaPlugin;
import ru.laimcraft.vanilla.commands.ChestCommand;
import ru.laimcraft.vanilla.commands.TPACommand;
import ru.laimcraft.vanilla.commands.getCommand;
import ru.laimcraft.vanilla.components.BlockInventory;
import ru.laimcraft.vanilla.components.CraftManager.CraftMaterialAllowed;
import ru.laimcraft.vanilla.components.CraftManager.Crafts;
import ru.laimcraft.vanilla.components.CraftManager.MagicCraftingTable;
import ru.laimcraft.vanilla.components.MagicItemsTime;
import ru.laimcraft.vanilla.components.player.PlayerStatus;
import ru.laimcraft.vanilla.database.mysql.MySQLAccounts;
import ru.laimcraft.vanilla.database.mysql.MySQLChestAccess;
import ru.laimcraft.vanilla.database.mysql.MySQLChests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class Vanilla extends JavaPlugin {
    public static Vanilla instance;
    public static HashMap<String, String> tpa = new HashMap<>();
    public static HashMap<String, PlayerStatus> players = new HashMap<>();
    public static HashMap<String, MagicCraftingTable> MagicCraftingTablePlayers = new HashMap<>();
    public static CraftMaterialAllowed craftMaterialAllowed = new CraftMaterialAllowed();
    public static Crafts crafts = new Crafts();
    //public static List<String> vanishPlayers = new ArrayList<>();
    public static List<String> chestModePlayers = new ArrayList<>();
    //public static ArrayList<String> AuthPlayers = new ArrayList<>();
    public static MySQLAccounts accounts = new MySQLAccounts();
    public static MySQLChestAccess chestAccess = new MySQLChestAccess();
    public static MySQLChests chests = new MySQLChests();
    public static BlockInventory blockInventory = new BlockInventory();
    @Override
    public void onEnable() {
        instance = this;
        EventsHandler eventsHandler = new EventsHandler();
        Objects.requireNonNull(getCommand("chest")).setExecutor(new ChestCommand());
        Objects.requireNonNull(getCommand("get")).setExecutor(new getCommand());
        getCommand("utpa").setExecutor(new TPACommand());
        //getCommand("tp").setExecutor(new TPCommand());
    }

    @Override
    public void onDisable() {

    }
}
