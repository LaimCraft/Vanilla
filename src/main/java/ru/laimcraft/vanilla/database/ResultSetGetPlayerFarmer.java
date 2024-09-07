package ru.laimcraft.vanilla.database;

public class ResultSetGetPlayerFarmer {
    private String playerName;
    private int level;
    private int xp;
    public ResultSetGetPlayerFarmer(String playerName, int level, int xp) {
        this.playerName = playerName;
        this.level = level;
        this.xp = xp;}

    public String getPlayerName() {
        return playerName;
    }

    public int getLevel() {
        return level;
    }

    public int getXP() {
        return xp;
    }
}
