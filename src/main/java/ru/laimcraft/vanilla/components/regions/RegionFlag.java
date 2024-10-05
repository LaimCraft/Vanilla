package ru.laimcraft.vanilla.components.regions;

public enum RegionFlag {
    Country("Country"),
    SpawnMobs("SpawnMobs");

    private final String id;

    private RegionFlag(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
