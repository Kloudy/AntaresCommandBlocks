package kloudy.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener
{
	@EventHandler
	public void blockBreak(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		
		if(player.getItemInHand().getType() == Material.BOOK_AND_QUILL && event.getBlock().getType() == Material.COMMAND && player.hasPermission("acb.set"))
		{
			event.setCancelled(true);
		}

		if(player.getItemInHand().getType() == Material.BOOK_AND_QUILL && event.getBlock().getType() == Material.COBBLESTONE && player.hasPermission("acb.set"))
		{
			event.setCancelled(true);
		}
	}
}
