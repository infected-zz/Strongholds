package me.desmin88.strongholds;


import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.alta189.sqlLibrary.SQLite.sqlCore;

public class Strongholds extends JavaPlugin {
	private Logger log = Logger.getLogger("Minecraft");
	public static String pref = "[Strongholds] ";
	public sqlCore Core = null;
	private PluginDescriptionFile pdf;
	private Configuration cfg;
	public static String table = "CREATE TABLE strongholds ( name VARCHAR(255), leader VARCHAR(255), players TEXT, level INT, x INT, y INT, z INT, x2 INT, y2 INT, z2 INT, world VARCHAR(255), PRIMARY KEY(`name`));";
	@Override
	public void onDisable() {
		System.out.println("[" + pdf.getName() + "]"  + " by " + pdf.getAuthors().get(0) + " version " + pdf.getVersion() + " disabled.");
	}

	@Override
	public void onEnable() {
		pdf = this.getDescription();
		getDataFolder().mkdir();
		
		Core = new sqlCore(this.log, this.pref, "Strongholds", getDataFolder().getPath()); // Make an instance of a sql core for use
		System.out.println(pref + "Initializing SQL core for connections");
		Core.initialize(); //We initialize to begin connections
		//Code to basically establish a connection to the database.
		try {
			if(Core.checkConnection()) { // Checking if we have made a valid connection
				System.out.println(pref + "Valid SQL connection made!");
				if(!Core.checkTable(table)) { //Table not made. 
					System.out.println(pref + "First run detected, making table for data entry.");
					Core.createTable(table); // Table not made, we now make it for data entry
				}
			}
			else{ // We didn't connect, possibly wrong values in the config?!
				System.out.println(pref + "Error checking for SQL connection, aborting .");
			}
		} catch (Exception e) { // Odd exceptions, hmm. 
			System.out.println(pref + "Error checking for SQL connection, aborting.");
			e.printStackTrace();
		}
		PluginManager pm = this.getServer().getPluginManager();
		//getCommand("sh").setExecutor(new SHCommands());
		System.out.println("[" + pdf.getName() + "]"  + " by " + pdf.getAuthors().get(0) + " version " + pdf.getVersion() + " enabled.");
	
	}
}
