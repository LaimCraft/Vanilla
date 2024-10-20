package ru.laimcraft.vanilla;

import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.plugin.java.JavaPlugin;
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
    public static MySQLChests chests = new MySQLChests();
    public static Vanilla instance;
    @Override
    public void onEnable() {
        instance = this;
        new ChestAccess();
        new TabAndChatColorManager();
    }

    @Override
    public void onDisable() {

    }
}
