package io.github.thatsmusic99.headsplus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thatsmusic99.headsplus.commands.Head;
import io.github.thatsmusic99.headsplus.commands.HeadsPlusCommand;
import io.github.thatsmusic99.headsplus.events.HeadInteractEvent;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class HeadsPlus extends JavaPlugin {	
	public Logger log = Logger.getLogger("Minecraft");
	private static HeadsPlus instance;
	public PluginDescriptionFile pluginYml = getDescription();
	public String author = pluginYml.getAuthors().toString();
	public String version = pluginYml.getVersion();
	
    public static FileConfiguration config;
	public File configF;
	public static FileConfiguration heads;
	public File headsF;
		
	
	@Override
	public void onEnable() {
		try { 
			instance = this;
			setUpMConfig(null);
			getServer().getPluginManager().registerEvents(new HeadInteractEvent(), this);
		    this.getCommand("headsplus").setExecutor(new HeadsPlusCommand());
		    this.getCommand("head").setExecutor(new Head());
		    log.info("[HeadsPlus] HeadsPlus has been enabled.");
		} catch (Exception e) {
			log.severe("[HeadsPlus] Error enabling HeadsPlus!");
			e.printStackTrace();
		}
    }
	@Override
	public void onDisable() {
		log.info("[HeadsPlus] HeadsPlus has been disabled.");
	}
	public static HeadsPlus getInstance() {
		return instance;
		
	}

	@SuppressWarnings("deprecation")
	public void addRecipes() {
		ItemStack zHead = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta zMeta = (SkullMeta) zHead.getItemMeta();
		zMeta.setOwner("MHF_Zombie");
		zMeta.setDisplayName("Zombie Head");
		zHead.setItemMeta(zMeta);
		
		
		ShapelessRecipe zombieRecipe = new ShapelessRecipe(zHead)
	             .addIngredient(Material.ROTTEN_FLESH)
	             .addIngredient(Material.SKULL_ITEM, 1);
		
		Bukkit.addRecipe(zombieRecipe);

		
		
	}

	public void setUpMConfig(Plugin p) {
			configF = new File(p.getDataFolder(), "config.yml");
			config = p.getConfig();
			if(!p.getDataFolder().exists()) {
				try {
					p.getDataFolder().createNewFile();
				}
				catch (IOException e) {
					log.severe("[HeadsPlus] Couldn't create data folder!");
					e.printStackTrace();
				}
			}
			headsF = new File(p.getDataFolder(), "heads.yml");
			if (!headsF.exists()) {
				try {
				    headsF.createNewFile();
				} catch (IOException e) {
					log.severe("[HeadsPlus] Couldn't create heads config!");
					e.printStackTrace();
				}
			}
		}
    public void reloadMConfig() {
    	config = YamlConfiguration.loadConfiguration(configF);
    }
	public void reloadHConfig() {
		heads = YamlConfiguration.loadConfiguration(headsF);
	}

	public FileConfiguration getHConfig() {
		return heads;
	}




	// TODO need to grab those config things and shove them into a file of their own.
    // TODO fix config loading.
	// TODO Add crafting recipe? :)

	

	
}