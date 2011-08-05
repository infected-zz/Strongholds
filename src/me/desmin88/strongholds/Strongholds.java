package me.desmin88.strongholds;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import me.desmin88.strongholds.commands.SHCommands;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.alta189.sqlLibrary.MySQL.mysqlCore;

public class Strongholds extends JavaPlugin {
	private Logger log = Logger.getLogger("Minecraft");
	public static String pref = "[Strongholds] ";
	public mysqlCore sqlCore = null;
	private String dbHost = null;
	private String dbUser = null;
	private String dbPass = null;
	private String dbDatabase = null;
	private PluginDescriptionFile pdf;
	
	@Override
	public void onDisable() {
		System.out.println("[" + pdf.getName() + "]"  + " by " + pdf.getAuthors().get(0) + " version " + pdf.getVersion() + " disabled.");
	}

	@Override
	public void onEnable() {
		pdf = this.getDescription();
		
		//TODO code for getting config.yml settings
		
		sqlCore = new mysqlCore(this.log, this.pref, this.dbHost, this.dbDatabase, this.dbUser, this.dbPass); // Make an instance of a sql core for use
		System.out.println(pref + "Initializing MySQL core for connections");
		sqlCore.initialize(); //We initialize to begin connections
		//Code to basically establish a connection to the database.
		try {
			if(sqlCore.checkConnection()) { // Checking if we have made a valid connection
				System.out.println(pref + "Valid MySQL connection made!");
				if(!sqlCore.checkTable("")) { //Table not made. 
					System.out.println(pref + "First run detected, making table for data entry.");
					String query = ""; // To be filled in later
					sqlCore.createTable(query); // Table not made, we now make it for data entry
				}
			}
			else{ // We didn't connect, possibly wrong values in the config?!
				System.out.println(pref + "Error checking for MySQL connection, aborting .");
			}
		} catch (Exception e) { // Odd exceptions, hmm. 
			System.out.println(pref + "Error checking for MySQL connection, aborting.");
			e.printStackTrace();
		}
		PluginManager pm = this.getServer().getPluginManager();
		getCommand("sh").setExecutor(new SHCommands());
		System.out.println("[" + pdf.getName() + "]"  + " by " + pdf.getAuthors().get(0) + " version " + pdf.getVersion() + " enabled.");
	
	}
}
