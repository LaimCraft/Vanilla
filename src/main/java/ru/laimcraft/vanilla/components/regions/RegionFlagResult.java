package ru.laimcraft.vanilla.components.regions;

public enum RegionFlagResult {
    Yes("yes"),
    No("no"),
    Ex("ex"),
    NULL(null);

    private final String result;

    private RegionFlagResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static RegionFlagResult get(String result) {
        if(result == null) return RegionFlagResult.NULL;
        if(result.equals("yes")) return RegionFlagResult.Yes;
        if(result.equals("no")) return RegionFlagResult.No;
        if(result.equals("ex")) return RegionFlagResult.Ex;
        return RegionFlagResult.Ex;
    }
}
