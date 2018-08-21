package io.github.thatsmusic99.headsplus.config.headsx.icons;

import io.github.thatsmusic99.headsplus.HeadsPlus;
import io.github.thatsmusic99.headsplus.config.headsx.Icon;
import io.github.thatsmusic99.headsplus.util.InventoryManager;
import io.github.thatsmusic99.headsplus.util.MaterialTranslator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Challenge implements Icon {
    @Override
    public String getIconName() {
        return "challenge";
    }

    @Override
    public void onClick(Player p, InventoryManager im, InventoryClickEvent e) {

    }

    @Override
    public Material getDefaultMaterial() {
        return HeadsPlus.getInstance().getNMS().getColouredBlock(MaterialTranslator.BlockType.TERRACOTTA, 15).getType();
    }

    public Material getCompleteMaterial() {
        return HeadsPlus.getInstance().getNMS().getColouredBlock(MaterialTranslator.BlockType.TERRACOTTA, 5).getType();
    }

    @Override
    public List<String> getDefaultLore() {
        return new ArrayList<>(Collections.singleton("{challenge-lore}"));
    }

    @Override
    public String getDefaultDisplayName() {
        return "{challenge-name}";
    }
}
