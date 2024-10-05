package ru.laimcraft.vanilla.components.regions;

import ru.laimcraft.vanilla.database.mysql.MySQLChunks;

public class RegionChunk {
    private final String region;
    private final String world;
    private final int x;
    private final int z;
    public RegionChunk(String region, String world, int x, int z) {
        this.region = region;
        this.world = world;
        this.x = x;
        this.z = z;
    }

    public String getRegion(String world, int x, int z) {
        return region;
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }
}
