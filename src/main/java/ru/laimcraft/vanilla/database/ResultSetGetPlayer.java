package ru.laimcraft.vanilla.database;

public class ResultSetGetPlayer {
    private String playerName;
    private int level;
    private int xp;
    private double hp;
    private int MaxHP;
    private double mp;
    private int MaxMP;
    private int hunger;
    private int MaxHunger;
    private float saturation;
    private int MaxSaturation;
    public ResultSetGetPlayer(String playerName, int level, int xp, double hp, int MaxHP, double mp, int MaxMP, int hunger, int MaxHunger, float saturation, int MaxSaturation) {
        this.playerName = playerName;
        this.level = level;
        this.xp = xp;
        this.hp = hp;
        this.MaxHP = MaxHP;
        this.mp = mp;
        this.MaxMP = MaxMP;
        this.hunger = hunger;
        this.MaxHunger = MaxHunger;
        this.saturation = saturation;
        this.MaxSaturation = MaxSaturation;}

    public String getPlayerName() {
        return playerName;
    }

    public int getLevel() {
        return level;
    }

    public int getXP() {
        return xp;
    }

    public double getHP() {
        return hp;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public double getMP() {
        return mp;
    }

    public int getMaxMP() {
        return MaxMP;
    }

    public int getHunger() {
        return hunger;
    }

    public int getMaxHunger() {
        return MaxHunger;
    }

    public float getSaturation() {
        return saturation;
    }

    public int getMaxSaturation() {
        return MaxSaturation;
    }
}
