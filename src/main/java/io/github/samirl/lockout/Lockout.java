package io.github.samirl.lockout;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.mysql.jdbc.log.Log;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Lockout extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Logger log = getServer().getLogger();
	}
	@Override
	public void onDisable() {
		
	}
	private WorldGuardPlugin getWorldGuard(){
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        try {
				throw new Exception();
			} catch (Exception e) {
				getLogger().severe("WorldGuard not detected, shutting down.");
				getServer().getPluginManager().disablePlugin(this);
			}
	    }

	    return (WorldGuardPlugin) plugin;
	}

}
