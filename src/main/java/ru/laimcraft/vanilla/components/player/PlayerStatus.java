package ru.laimcraft.vanilla.components.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.laimcraft.vanilla.components.player.skills.PlayerFarmer;
import ru.laimcraft.vanilla.database.ResultSetGetPlayer;
import ru.laimcraft.vanilla.database.mysql.MySQLVanillaPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStatus {
    private Player player;
    private String playerName;
    private boolean status = false;

    private PlayerFarmer playerFarmer = new PlayerFarmer();

    private int level;
    private int xp;
    private double hp;
    private int MaxHP;
    private int mp;
    private int MaxMP;
    private int hunger;
    private int MaxHunger;
    private float saturation;
    private int MaxSaturation;

    public PlayerStatus(String player) {
        if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(player))) return;
        this.status = true;
        this.player = Bukkit.getPlayer(player);
        this.playerName = this.player.getName(); update();}
    public PlayerStatus(Player player) {
        if(!Bukkit.getOnlinePlayers().contains(player)) return;
        this.status = true;
        this.player = player;
        this.playerName = this.player.getName(); update();}

    private void update(){
        ResultSetGetPlayer resultSetGetPlayer = new MySQLVanillaPlayer().getPlayer(playerName);
        this.playerName = resultSetGetPlayer.getPlayerName();
        this.level = resultSetGetPlayer.getLevel();
        this.xp = resultSetGetPlayer.getXP();
        this.hp = resultSetGetPlayer.getHP();
        this.MaxHP = resultSetGetPlayer.getMaxHP();
        this.mp = resultSetGetPlayer.getMP();
        this.MaxMP = resultSetGetPlayer.getMaxMP();
        this.hunger = resultSetGetPlayer.getHunger();
        this.MaxHunger = resultSetGetPlayer.getMaxHunger();
        this.saturation = resultSetGetPlayer.getSaturation();
        this.MaxSaturation = resultSetGetPlayer.getMaxSaturation();
        skillsLoader();
    }

    private void skillsLoader() {
        playerFarmer = new PlayerFarmer(this);
    }

    public Player getPlayer() {return player;}
    public String getPlayerName() {return playerName;}
    public boolean isStatus() {return status;}
    public PlayerFarmer getPlayerFarmer() {return playerFarmer;}
    public int getLevel() {return level;}
    public int getXP() {return xp;}
    public double getHP() {return hp;}
    public int getMaxHP() {return MaxHP;}
    public int getMP() {return mp;}
    public int getMaxMP() {return MaxMP;}
    public int getHunger() {return hunger;}
    public int getMaxHunger() {return MaxHunger;}
    public float getSaturation() {return saturation;}
    public int getMaxSaturation() {return MaxSaturation;}

    public void resetHP() {new MySQLVanillaPlayer().reset(playerName, "HP");}
    public void resetMP() {new MySQLVanillaPlayer().reset(playerName, "MP");}
    public void resetHunger() {new MySQLVanillaPlayer().reset(playerName, "Hunger");}
    public void resetSaturation() {new MySQLVanillaPlayer().reset(playerName, "Saturation");}


    public void addLevel(int level) {
        new MySQLVanillaPlayer().add(playerName, "Level", level);
        this.level = this.level + level;}

    public void addXP(int xp) {
        new MySQLVanillaPlayer().add(playerName, "XP", xp);
        this.xp = this.xp + xp;}

    public boolean removeXP(int xp) {
        if(this.xp < xp) return false;
        new MySQLVanillaPlayer().remove(playerName, "XP", xp);
        this.xp = this.xp - xp; return true;}

    public void addHP(double hp) {
        if((this.hp + hp) > this.MaxHP) {
            new MySQLVanillaPlayer().add(playerName, "HP", this.MaxHP);
            this.hp = MaxHP;
        return;}
        new MySQLVanillaPlayer().add(playerName, "HP", hp);
        this.hp = this.hp + hp;}

    public boolean removeHP(double hp) { // false = playerDeath
        if(this.hp < hp) return false;
        new MySQLVanillaPlayer().remove(playerName, "HP", hp);
        this.hp = this.hp - hp; return true;}

    public void addMaxHP(int MaxHP) {
        new MySQLVanillaPlayer().add(playerName, "MaxXP", MaxHP);
        this.MaxHP = this.MaxHP + MaxHP;}

    public void addMP(int mp) {
        if((this.mp + mp) > this.MaxMP) {
            new MySQLVanillaPlayer().add(playerName, "MP", this.MaxMP);
            this.mp = MaxMP;
            return;}
        new MySQLVanillaPlayer().add(playerName, "MP", mp);
        this.mp = this.mp + mp;}

    public boolean removeMP(int mp) { // false = no MP
        if(this.mp < mp) return false;
        new MySQLVanillaPlayer().remove(playerName, "MP", mp);
        this.mp = this.mp - mp; return true;}

    public void addMaxMP(int MaxMP) {
        new MySQLVanillaPlayer().add(playerName, "MaxMP", MaxMP);
        this.MaxMP = this.MaxMP + MaxMP;}

    public void addHunger(int hunger) {
        if((this.hunger + hunger) > this.MaxHunger) {
            new MySQLVanillaPlayer().add(playerName, "Hunger", this.MaxHunger);
            this.hunger = MaxHunger;
            return;}
        new MySQLVanillaPlayer().add(playerName, "Hunger", hunger);
        this.hunger = this.hunger + hunger;}

    public boolean removeHunger(int hunger) {
        if(this.hunger < hunger) {
            new MySQLVanillaPlayer().remove(playerName, "Hunger", 0);
            this.hunger = 0;
        return false;}
        new MySQLVanillaPlayer().remove(playerName, "Hunger", hunger);
        this.hunger = this.hunger - hunger; return true;}

    public void addMaxHunger(int MaxHunger) {
        new MySQLVanillaPlayer().add(playerName, "MaxHunger", MaxHunger);
        this.MaxHunger = this.MaxHunger + MaxHunger;}

    public void addSaturation(int saturation) {
        if((this.saturation + saturation) > this.MaxSaturation) {
            new MySQLVanillaPlayer().add(playerName, "Saturation", this.MaxSaturation);
            this.saturation = MaxSaturation;
            return;}
        new MySQLVanillaPlayer().add(playerName, "Saturation", saturation);
        this.saturation = this.saturation + saturation;}

    public boolean removeSaturation(int saturation) {
        if(this.saturation < saturation) {
            new MySQLVanillaPlayer().remove(playerName, "Saturation", 0);
            this.saturation = 0;
            return false;}
        new MySQLVanillaPlayer().remove(playerName, "Saturation", saturation);
        this.saturation = this.saturation - saturation; return true;}

    public void addMaxSaturation(int MaxSaturation) {
        new MySQLVanillaPlayer().add(playerName, "MaxSaturation", MaxSaturation);
        this.MaxSaturation = this.MaxSaturation + MaxSaturation;}





}
