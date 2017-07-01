package io.github.thatsmusic99.headsplus.events;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.thatsmusic99.headsplus.HeadsPlus;
import io.github.thatsmusic99.headsplus.config.HeadsPlusConfig;
import io.github.thatsmusic99.headsplus.config.HeadsPlusConfigHeads;
import io.github.thatsmusic99.headsplus.config.HeadsPlusCrafting;

public class JoinEvent implements Listener { 
	
	public static boolean reloaded = false;
    private static File configF = new File(HeadsPlus.getInstance().getDataFolder(), "config.yml");
	
	@SuppressWarnings("unused")
	private static FileConfiguration messages;
	private static File messagesF = new File(HeadsPlus.getInstance().getDataFolder(), "messages.yml");;
	
	@SuppressWarnings("unused")
	private static FileConfiguration heads;
	private static File headsF = new File(HeadsPlus.getInstance().getDataFolder(), "heads.yml");;
	
	@SuppressWarnings("unused")
	private static FileConfiguration crafting;
	private static File craftingF = new File(HeadsPlus.getInstance().getDataFolder(), "crafting.yml");;
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!reloaded) {
		    if (HeadsPlus.getInstance().getConfig().getBoolean("autoReloadOnFirstJoin")) {
			       try {

				       if  (!(configF.exists())) {
					       HeadsPlus.getInstance().log.info("[HeadsPlus] Config not found, creating!");
					       HeadsPlus.getInstance().saveConfig();
					       
				       } else {
					       HeadsPlus.getInstance().log.info("[HeadsPlus] Found config, loading!");
					       HeadsPlus.getInstance().reloadConfig();
					       HeadsPlus.getInstance().log.info("[HeadsPlus] Config reloaded!");
				      }  
				      if (!(messagesF.exists())) {
				    	  HeadsPlus.getInstance().log.info("[HeadsPlus] Messages not found, creating!");
				    	  HeadsPlusConfig.reloadMessages();
				    	  messages = YamlConfiguration.loadConfiguration(messagesF);
				    	  HeadsPlus.getInstance().log.info("[HeadsPlus] Messages created!");
				      } else {
				    	  HeadsPlusConfig.reloadMessages();
				      }
				      if (!(headsF.exists())) {
				    	  HeadsPlus.getInstance().log.info("[HeadsPlus] Heads not found, creating!");
				    	  HeadsPlusConfigHeads.reloadHeads();
				    	  heads = YamlConfiguration.loadConfiguration(headsF);
				    	  HeadsPlus.getInstance().log.info("[HeadsPlus] Heads created!");
				      } else {
				    	  HeadsPlusConfigHeads.reloadHeads();
				      }
				      if (!(craftingF.exists())) {
				    	  if (HeadsPlus.getInstance().getConfig().getBoolean("craftHeads")) {
				    		  HeadsPlus.getInstance().log.info("[HeadsPlus] Crafting not found, creating!");
				    	      HeadsPlusCrafting.reloadCrafting();
				    	      crafting = YamlConfiguration.loadConfiguration(craftingF);
				    	      HeadsPlus.getInstance().log.info("[HeadsPlus] Crafting created!");
				    	  }
				      } else {
				    	  HeadsPlusCrafting.reloadCrafting();
				      }
				      reloaded = true;
				      
			       } catch (Exception ex) {
				       HeadsPlus.getInstance().log.severe("[HeadsPlus] Failed to reload config.");
				       ex.printStackTrace();
			       }
	        	
	        }
		}
	}
}
