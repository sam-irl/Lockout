package io.github.samirl.lockout.command;

import io.github.samirl.lockout.Lockout;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class CommandLockout implements CommandExecutor {
	public ProtectedRegion cmdreg;
	Lockout main = new Lockout();
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Sorry, but Lockout may only be used by a player.");
			return false;
		}
		Player player = (Player) sender;
		RegionManager regions = main.container.get(player.getWorld());
		if(args[0].equalsIgnoreCase(null)) {
			sender.sendMessage("Please specify a region!");
			return false;
		}
		ProtectedRegion region = regions.getRegion(args[0]);
		if(!main.getConfig().getStringList("lockedregions").contains(args[0])) {
			main.getConfig().set("lockedregions", args[0]);
			sender.sendMessage(ChatColor.RED + "Locked region " + ChatColor.GOLD + args[0]);
		} else {
			main.getConfig().getStringList("lockedregions").remove(args[0]);
			sender.sendMessage(ChatColor.GREEN + "Unlocked region " + ChatColor.GOLD + args[0]);
		}
		
		return true;
	}

}
