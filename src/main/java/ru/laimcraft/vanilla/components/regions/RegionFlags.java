package ru.laimcraft.vanilla.components.regions;

import ru.laimcraft.vanilla.database.mysql.MySQLRegions;

import java.util.HashMap;
import java.util.List;

public class RegionFlags {
    private final String region;
    private final HashMap<RegionFlag, RegionFlagResult> regionFlags;
    public RegionFlags(String region) {
        this.region = region;
        this.regionFlags = MySQLRegions.getRegionFlags(region);
    }

    public String getRegion() {
        return region;
    }

    public RegionFlagResult getRegionFlagResult(RegionFlag regionFlag) {
        return regionFlags.get(regionFlag);
    }
}
