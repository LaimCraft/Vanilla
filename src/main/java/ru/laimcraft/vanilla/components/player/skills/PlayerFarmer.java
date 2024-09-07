package ru.laimcraft.vanilla.components.player.skills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.components.player.PlayerStatus;
import ru.laimcraft.vanilla.database.ResultSetGetPlayerFarmer;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayerFarmer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerFarmer {
    PlayerStatus playerStatus;
    private int level;
    private int xp;
    private boolean load = false;
    public PlayerFarmer() {}
    public PlayerFarmer(PlayerStatus player) {this.playerStatus = player;}
    private void update(){
        ResultSetGetPlayerFarmer resultSetGetPlayerFarmer = new MySQLVanillaPlayerFarmer().getPlayer(playerStatus.getPlayerName());
        this.level = resultSetGetPlayerFarmer.getLevel();
        this.xp = resultSetGetPlayerFarmer.getXP();
        this.load = true;}

    public PlayerStatus getPlayerStatus() {return playerStatus;}
    public int getLevel() {return level;}
    public int getXP() {return xp;}
    public void addLevel(int level) {
        new MySQLVanillaPlayerFarmer().addFarmerLevel(playerStatus.getPlayerName(), level);
        this.level = this.level + level;
    }

    public void addXP(int xp) {
        new MySQLVanillaPlayerFarmer().addFarmerXP(playerStatus.getPlayerName(), xp);
        this.xp = this.xp + xp;
    }
}
