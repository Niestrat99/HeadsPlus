package io.github.thatsmusic99.headsplus.nms;

import com.mojang.authlib.GameProfile;
import io.github.thatsmusic99.headsplus.util.MaterialTranslator;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.SkullMeta;

public interface NMSManager {

    ItemStack addNBTTag(Object item);

    boolean isSellable(Object item);

    SearchGUI getSearchGUI(Player p, SearchGUI.AnvilClickEventHandler a);

    default SkullMeta setSkullOwner(String s, SkullMeta m) {
        m.setOwner(s);
        return m;
    }

    String getSkullOwnerName(SkullMeta m);

    ShapelessRecipe getRecipe(ItemStack i, String name);

    OfflinePlayer getOfflinePlayer(String name);

    Player getPlayer(String name);

    GameProfile getGameProfile(ItemStack s);

    ItemStack getItemInHand(Player p);

    ItemStack setType(String s, ItemStack i);

    String getType(ItemStack i);

    default ItemStack getSkullMaterial(int amount) {
        return new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
    }

    default ItemStack getColouredBlock(MaterialTranslator.BlockType b, int data) {
        if (b.equals(MaterialTranslator.BlockType.TERRACOTTA)) {
            return new ItemStack(Material.STAINED_CLAY, 1, (byte) data);
        } else if (b.equals(MaterialTranslator.BlockType.STAINED_GLASS_PANE)) {
            return new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) data);
        }
        return null;
    }
}