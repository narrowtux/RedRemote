package com.narrowtux.RedRemote;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class RedRemote extends JavaPlugin {
	BlockListener bl = new BlockListener();
	@Override
	public void onDisable() {
		System.out.println("RedRemote has been disabled.");
	}

	@Override
	public void onEnable() {
		System.out.println("RedRemote has been enabled.");
		getServer().getPluginManager().registerEvent(Type.REDSTONE_CHANGE, bl, Priority.Monitor, this);
	}

}
