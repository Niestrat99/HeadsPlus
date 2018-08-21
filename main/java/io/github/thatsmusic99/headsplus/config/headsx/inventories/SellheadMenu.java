package io.github.thatsmusic99.headsplus.config.headsx.inventories;

import io.github.thatsmusic99.headsplus.config.headsx.HeadInventory;
import io.github.thatsmusic99.headsplus.config.headsx.Icon;

public class SellheadMenu extends HeadInventory {
    @Override
    protected String getDefaultTitle() {
        return "HeadsPlus Sellhead menu";
    }

    @Override
    public Icon[] getDefaultItems() {
        return new Icon[0];
    }

    @Override
    public String getDefaultId() {
        return "sellheadmenu";
    }
}