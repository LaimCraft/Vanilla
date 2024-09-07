package ru.laimcraft.vanilla.components.player.skills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.laimcraft.vanilla.components.player.PlayerStatus;
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
        ResultSet resultSet = new MySQLVanillaPlayerFarmer().getPlayer(playerStatus.getPlayerName());
        try(resultSet) {
            this.level = resultSet.getInt(2);
            this.xp = resultSet.getInt(3);
            this.load = true;
        } catch (SQLException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQLException PlayerStatus Method -> " + ex.toString());
            load = false;}}

    public PlayerStatus getPlayerStatus() {return playerStatus;}
    public int getLevel() {return level;}
    public int getXp() {return xp;}
    public void addLevel(int level) {
        new MySQLVanillaPlayerFarmer().addFarmerLevel(playerStatus.getPlayerName(), level);
        this.level = this.level + level;
    }

    public void addXP(int xp) {
        new MySQLVanillaPlayerFarmer().addFarmerXP(playerStatus.getPlayerName(), xp);
        this.xp = this.xp + xp;
    }
}
