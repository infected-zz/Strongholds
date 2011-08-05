package me.desmin88.strongholds;

import java.io.File;
import java.io.IOException;

import org.bukkit.util.config.Configuration;


public class SettingsManager {
	private Strongholds plugin;
	private String dbHost = null;
	private String dbUser = null;
	private String dbPass = null;
	private String dbDatabase = null;
	private File cfgfile = null;
	private Configuration cfg = null;
	protected SettingsManager(Strongholds instance)  {
		this.plugin = instance;
		this.cfgfile = new File(plugin.getDataFolder(), "config.yml");
		//New instance, we need to get the values.
		if(!plugin.getDataFolder().exists())  {
			try {
			plugin.getDataFolder().mkdir(); //Make datafolder if it doesn't exist.
			cfgfile.createNewFile(); 
		} 
			catch(Exception e) {
				e.printStackTrace();
			} // Make config.yml if it doesn't exist.
			cfg = plugin.getConfiguration();
			cfg.setProperty("MySQL.dbHost", "");
			cfg.setProperty("MySQL.dbUser", "");
			cfg.setProperty("MySQL.dbPass", "");
			cfg.setProperty("MySQL.dbDatabase", "");
			System.out.println(Strongholds.pref + "Blank config.yml created, needs to be configured, shutting down");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
		else {
			//The yml exists!
			dbHost = cfg.getString("MySQL.dbHost");
			dbUser = cfg.getString("MySQL.dbUser");
			dbPass = cfg.getString("MySQL.dPass");
			dbDatabase = cfg.getString("MySQL.dbDatabase");
			
			
		}
	
	
	
	}
}
