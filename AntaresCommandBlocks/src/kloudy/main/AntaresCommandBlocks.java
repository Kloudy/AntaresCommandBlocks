package kloudy.main;

import java.util.logging.Logger;

import kloudy.events.BlockBreak;
import kloudy.events.PlayerInteract;

import org.bukkit.plugin.java.JavaPlugin;

public class AntaresCommandBlocks extends JavaPlugin
{
	public static Logger logger;
	
	@Override
	public void onEnable() 
	{
		logger = getLogger();
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
