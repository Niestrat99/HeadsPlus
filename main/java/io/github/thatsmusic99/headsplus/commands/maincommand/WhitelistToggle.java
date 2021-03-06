package io.github.thatsmusic99.headsplus.commands.maincommand;

import io.github.thatsmusic99.headsplus.HeadsPlus;
import io.github.thatsmusic99.headsplus.commands.IHeadsPlusCommand;
import io.github.thatsmusic99.headsplus.config.HeadsPlusMainConfig;
import io.github.thatsmusic99.headsplus.config.HeadsPlusMessagesConfig;
import io.github.thatsmusic99.headsplus.locale.LocaleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class WhitelistToggle implements IHeadsPlusCommand {

    private final HeadsPlusMainConfig config = HeadsPlus.getInstance().getConfiguration();
    private final ConfigurationSection c = config.getWhitelist("default");
    private final HeadsPlusMessagesConfig hpc = HeadsPlus.getInstance().getMessagesConfig();

    @Override
    public String getCmdName() {
        return "whitelist";
    }

    @Override
    public String getPermission() {
        return "headsplus.maincommand.whitelist.toggle";
    }

    @Override
    public String getCmdDescription() {
        return LocaleManager.getLocale().descWhitelistToggle();
    }

    @Override
    public String getSubCommand() {
        return "Whitelist";
    }

    @Override
    public String getUsage() {
        return "/hp whitelist [On|Off]";
    }

    @Override
    public HashMap<Boolean, String> isCorrectUsage(String[] args, CommandSender sender) {
        HashMap<Boolean, String> h = new HashMap<>();
        h.put(true, "");
        return h;
    }

    @Override
    public boolean isMainCommand() {
        return true;
    }

    @Override
    public boolean fire(String[] args, CommandSender sender) {
        try {
            if (args.length == 1) {
                if (c.getBoolean("enabled")) {
                    c.set("enabled", false);
                    config.save();
                    sender.sendMessage(hpc.getString("wl-off"));
                } else if (!c.getBoolean("enabled")) {
                    c.set("enabled", true);
                    config.save();
                    sender.sendMessage(hpc.getString("wl-on"));
                }
            } else {
                String str = args[1];
                if (str.equalsIgnoreCase("on")) {
                    if (!c.getBoolean("enabled")) {
                        c.set("enabled", true);
                        config.save();
                        sender.sendMessage(hpc.getString("wl-on"));
                    } else {
                        sender.sendMessage(hpc.getString("wl-a-on"));
                    }
                } else if (str.equalsIgnoreCase("off")) {
                    if (c.getBoolean("enabled")) {
                        c.set("enabled", false);
                        config.save();
                        sender.sendMessage(hpc.getString("wl-off"));
                    } else {
                        sender.sendMessage(hpc.getString("wl-a-off"));
                    }
                } else if (!(str.equalsIgnoreCase("on"))) {
                    sender.sendMessage(ChatColor.DARK_RED + "Usage: " + ChatColor.RED + getUsage());
                }
            }
        } catch (Exception e) {
            new DebugPrint(e, "Subcommand (whitelist)", true, sender);
        }

        return false;
    }
}
