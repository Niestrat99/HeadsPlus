package io.github.thatsmusic99.headsplus.config.headsx;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.github.thatsmusic99.headsplus.HeadsPlus;
import io.github.thatsmusic99.headsplus.commands.maincommand.DebugPrint;
import io.github.thatsmusic99.headsplus.config.ConfigSettings;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class HeadsPlusConfigHeadsX extends ConfigSettings {

    public boolean s = false;
    private double cVersion = 2.0;

    public HeadsPlusConfigHeadsX() {
        this.conName = "headsx";
        headsxEnable();
    }


    private void loadHeadsX() {
        getConfig().options().header("HeadsPlus by Thatsmusic99 " +
                "\n WARNING: This is an advanced section of the plugin. If you do not know what you a doing with it, please do not use it due to risk of crashing your own and other's games. " +
                "\n For more information visit the GitHub wiki for HeadsX.yml: https://github.com/Thatsmusic99/HeadsPlus/wiki/headsx.yml");

        for (HeadsXSections h : HeadsXSections.values()) {
            getConfig().addDefault("sections." + h.let + ".display-name", h.dn);
            getConfig().addDefault("sections." + h.let + ".texture", h.tx);
        }
        for (HeadsXEnums e : HeadsXEnums.values()) {
            getConfig().addDefault("heads." + e.name + ".database", true);
            getConfig().addDefault("heads." + e.name + ".encode", false);
            getConfig().addDefault("heads." + e.name + ".displayname", e.dn);
            getConfig().addDefault("heads." + e.name + ".texture", e.tex);
            getConfig().addDefault("heads." + e.name + ".price", "default");
            getConfig().addDefault("heads." + e.name + ".section", e.sec);
        }


        getConfig().options().copyDefaults(true);
        save();
    }


    @Override
    public void reloadC(boolean a) {
        if (configF == null) {
            configF = new File(HeadsPlus.getInstance().getDataFolder(), "headsx.yml");
        }
        config = YamlConfiguration.loadConfiguration(configF);
        getConfig().addDefault("options.version", cVersion);
        getConfig().addDefault("options.default-price", 10.00);
       // getHeadsX().addDefault("options.advent-calender", true);
       // if (getHeadsX().getBoolean("options.advent-calender")) {
        //    for (AdventCManager acm : AdventCManager.values()) {
        //        getHeadsX().addDefault("advent." + acm.name(), new ArrayList<>());
        //    }
       // }
        if (configF.length() <= 500) {
            loadHeadsX();
        }
        if (getConfig().getDouble("options.version") < cVersion) {
            for (String str : getConfig().getConfigurationSection("heads").getKeys(false)) {
                getConfig().addDefault("heads." + str + ".price", "default");
            }
            getConfig().set("options.version", cVersion);
            for (String str : getConfig().getConfigurationSection("heads").getKeys(false)) {
                for (HeadsXEnums e : HeadsXEnums.values()) {
                    if (e.name.equalsIgnoreCase(str)) {
                        getConfig().addDefault("heads." + e.name + ".section", e.sec);
                    }
                }
            }
            for (HeadsXEnums e : HeadsXEnums.values()) {
                if (e.v == cVersion) {
                    getConfig().addDefault("heads." + e.name + ".database", true);
                    getConfig().addDefault("heads." + e.name + ".encode", false);
                    getConfig().addDefault("heads." + e.name + ".displayname", e.dn);
                    getConfig().addDefault("heads." + e.name + ".texture", e.tex);
                    getConfig().addDefault("heads." + e.name + ".price", "default");
                    getConfig().addDefault("heads." + e.name + ".section", e.sec);
                }
            }
            for (HeadsXSections h : HeadsXSections.values()) {
                if (h.d == cVersion) {
                    getConfig().addDefault("sections." + h.let + ".display-name", h.dn);
                    getConfig().addDefault("sections." + h.let + ".texture", h.tx);
                }
            }
            if (getConfig().get("sections.logos.texture").equals("HP#youtube")) {
                getConfig().addDefault("heads.youtube.database", true);
                getConfig().addDefault("heads.youtube.encode", false);
                getConfig().addDefault("heads.youtube.displayname", "&8[&cYouTube&8]");
                getConfig().addDefault("heads.youtube.texture", HeadsXEnums.YOUTUBE.tex);
                getConfig().addDefault("heads.youtube.price", "default");
            }
            getConfig().options().copyDefaults(true);
        }
        save();
        s = false;
    }
    private void headsxEnable() {
        reloadC(false);
       // if (s) {
      //      loadHeadsX();
      //  }
        s = false;
    }
    public boolean isHPXSkull(String str) {
        return str.startsWith("HP#");
    }

    public ItemStack getSkull(String s) throws NoSuchFieldException, IllegalAccessException {
        String st = s.split("#")[1];
        ItemStack i = HeadsPlus.getInstance().getNMS().getSkullMaterial(1);
        SkullMeta sm = (SkullMeta) i.getItemMeta();
        GameProfile gm = new GameProfile(UUID.randomUUID(), "HPXHead");
        if (getConfig().getBoolean("heads." + st + ".encode")) {
            byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", getConfig().getString(st + ".texture")).getBytes());
            gm.getProperties().put("textures", new Property("texture", Arrays.toString(encodedData)));
        } else {
            gm.getProperties().put("textures", new Property("texture", getConfig().getString("heads." + st + ".texture")));
        }

        Field profileField;
        profileField = sm.getClass().getDeclaredField("profile");

        profileField.setAccessible(true);
        profileField.set(sm, gm);
        sm.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("heads." + st + ".displayname")));
        i.setItemMeta(sm);
        return i;
    }
    public String getTextures(String s) {
        String[] st = s.split("#");
        try {
            return getConfig().getString("heads." + st[1] + ".texture");
        } catch (Exception ex) {
            new DebugPrint(ex, "Startup (headsx.yml)", false, null);
            return "";
        }
    }
}
