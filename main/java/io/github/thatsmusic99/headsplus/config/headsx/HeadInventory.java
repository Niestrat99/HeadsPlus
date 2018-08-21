package io.github.thatsmusic99.headsplus.config.headsx;


import io.github.thatsmusic99.headsplus.config.headsx.inventories.*;

import java.util.ArrayList;
import java.util.List;

public abstract class HeadInventory {

    public abstract String getDefaultTitle();

    public abstract Icon[] getDefaultItems();

    public abstract String getDefaultId();

    public static HeadInventory getInventoryByName(String s) {
        for (HeadInventory h : getInventories()) {
            if (s.equalsIgnoreCase(h.getName())) {
                return h;
            }
        }
        return null;
    }

    public static List<HeadInventory> getInventories() {
        List<HeadInventory> inventories = new ArrayList<>();
        inventories.add(new ChallengesMenu());
        inventories.add(new FavouritesMenu());
        inventories.add(new HeadMenu());
        inventories.add(new HeadSection());
        inventories.add(new SellheadMenu());
        return inventories;
    }

    public abstract String getName();

}
