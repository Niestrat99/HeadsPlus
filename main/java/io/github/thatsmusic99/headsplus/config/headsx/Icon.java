package io.github.thatsmusic99.headsplus.config.headsx;

import io.github.thatsmusic99.headsplus.config.headsx.icons.*;
import io.github.thatsmusic99.headsplus.util.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public interface Icon {

    String getIconName();

    void onClick(Player p, InventoryManager im, InventoryClickEvent e);

    Material getDefaultMaterial();

    List<String> getDefaultLore();

    String getDefaultDisplayName();

    default Icon getReplacementIcon() {
        return new Glass();
    }

    static Icon getIconFromName(String s) {
        for (Icon i : getIcons()) {
            if (i.getIconName().equalsIgnoreCase(s)) {
                return i;
            }
        }
        return null;
    }

    static List<Icon> getIcons() {
        List<Icon> icons = new ArrayList<>();
        icons.add(new Back());
        icons.add(new Challenge());
        icons.add(new Close());
        icons.add(new Favourites());
        icons.add(new Glass());
        icons.add(new Head());
        icons.add(new Menu());
        icons.add(new Next());
        icons.add(new Search());
        icons.add(new Stats());
        return icons;
    }
}