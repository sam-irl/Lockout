package io.github.samirl.lockout.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import com.sk

import io.github.samirl.lockout.command.CommandLockout;

public class LockoutMoveEvent implements Listener {
	CommandLockout cmd = new CommandLockout();
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent e) {
		if(cmd.cmdreg.contains(e.getPlayer().getLocation())
	}

}
