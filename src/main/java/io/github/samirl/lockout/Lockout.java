package io.github.samirl.lockout;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import io.github.samirl.lockout.command.CommandLockout;
import io.github.samirl.lockout.event.LockoutMoveEvent;

public class Lockout extends JavaPlugin {
	public FileConfiguration config = getConfig();
	public CommandLockout cmd = new CommandLockout();
	public RegionContainer container = getWorldGuard().getRegionContainer();
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
		getServer().getPluginManager().registerEvents(new LockoutMoveEvent(), this);
		config.addDefault("keyid", "Material.GOLD_NUGGET");
		config.addDefault("lockedregions", null);
		//this goes after the config.addDefaults, stupid
		config.options().copyDefaults(true);
		saveConfig();
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
