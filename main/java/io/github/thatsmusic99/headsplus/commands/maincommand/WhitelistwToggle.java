package io.github.thatsmusic99.headsplus.commands.maincommand;

import io.github.thatsmusic99.headsplus.HeadsPlus;
import io.github.thatsmusic99.headsplus.commands.HeadsPlusCommand;
import io.github.thatsmusic99.headsplus.config.HeadsPlusConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class WhitelistwToggle {

    private static final FileConfiguration config = HeadsPlus.getInstance().getConfig();

    public static void togglewlwNoArgs(CommandSender sender) {
        if (sender.hasPermission("headsplus.maincommand.whitelistw.toggle")) {
            try {
                if (config.getBoolean("whitelistwOn")) {
                    config.set("whitelistwOn", false);
                    config.options().copyDefaults(true);
                    HeadsPlus.getInstance().saveConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-off"))));
                } else if (!config.getBoolean("whitelistwOn")) {
                    config.set("whitelistwOn", true);
                    config.options().copyDefaults(true);
                    HeadsPlus.getInstance().saveConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-on"))));
                }
            } catch (Exception e) {
                HeadsPlus.getInstance().log.severe("[HeadsPlus] Failed to toggle world whitelist!");
                e.printStackTrace();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-fail"))));
            }
        } else {
            sender.sendMessage(HeadsPlusCommand.noPerms);
        }
    }
    public static void toggleWorld(CommandSender sender, String str) {
        if (sender.hasPermission("headsplus.maincommand.whitelistw.toggle")) {
            try {
                if (str.equalsIgnoreCase("on")) {
                    if (!config.getBoolean("whitelistwOn")) {
                        config.set("whitelistwOn", true);
                        config.options().copyDefaults(true);
                        HeadsPlus.getInstance().saveConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-on"))));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-a-on"))));
                    }

                } else if (str.equalsIgnoreCase("off")) {
                    if (config.getBoolean("whitelistwOn")) {
                        config.set("whitelistwOn", false);
                        config.options().copyDefaults(true);
                        HeadsPlus.getInstance().saveConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-off"))));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-a-off"))));
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Usage: " + ChatColor.RED + "/headsplus whitelistw [On|Off]");
                }
            } catch (Exception e) {
                HeadsPlus.getInstance().log.severe("[HeadsPlus] Failed to toggle world whitelist!");
                e.printStackTrace();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', HeadsPlus.getInstance().translateMessages(HeadsPlusConfig.getMessages().getString("wlw-fail"))));
            }
        } else {
            sender.sendMessage(HeadsPlusCommand.noPerms);
        }
    }
}