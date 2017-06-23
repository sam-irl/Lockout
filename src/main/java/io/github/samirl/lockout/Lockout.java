package io.github.samirl.lockout;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.mysql.jdbc.log.Log;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

import io.github.samirl.lockout.command.CommandLockout;

public class Lockout extends JavaPlugin {
	public static final Flag NEED_ITEM = new StateFlag("need-item", false);
	
	@Override
	public void onLoad() {
		FlagRegistry registry = getWorldGuard().getFlagRegistry();
		try {
			registry.register(NEED_ITEM);
		} catch (FlagConflictException e) {
			getLogger().severe("Fatal error: flag 'need-item' is in use by another plugin. Lockout shutting down.");
			getLogger().severe("STACKTRACE:");
			e.printStackTrace();
			getLogger().severe("Fatal error: flag 'need-item' is in use by another plugin. Lockout shutting down.");
		}
	}
	@Override
	public void onEnable() {
		Logger log = getServer().getLogger();
		this.getCommand("lockout").setExecutor(new CommandLockout());
	}
	@Override
	public void onDisable() {
		
	}
	private WorldGuardPlugin getWorldGuard() {
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
