package ru.laimcraft.vanilla.admin;

public enum TimeLevel {
    SECOND(1, "s", "second", "sec"),
    MINUTE(60, "m", "minute", "min");

    private int time;
    private String[] strings;

    private TimeLevel(int time, String... strings) {
        this.time = time;
        this.strings = strings;
    }

    public int getTime() {
        return time;
    }

    public String[] getStrings() {
        return strings;
    }
}
