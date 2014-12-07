package kloudy.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BookMeta;

public class PlayerInteract implements Listener
{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		
		if(player.getItemInHand().getType() == Material.BOOK_AND_QUILL && event.getAction() == Action.LEFT_CLICK_BLOCK && player.hasPermission("acb.set"))
		{
			Block block = event.getClickedBlock();
			
			if(block.getType() == Material.COMMAND && player.getItemInHand().getItemMeta() instanceof BookMeta)
			{	
				CommandBlock commandBlock = (CommandBlock)block.getState();
				BookMeta book = (BookMeta)event.getPlayer().getItemInHand().getItemMeta();
				
				String commandFromBook = new String();
				
				for(int i = 1; i <= book.getPageCount(); i++)
				{
					commandFromBook += book.getPage(i);
				}
				
				commandBlock.setCommand(commandFromBook);
				commandBlock.update();
				
				player.sendMessage(ChatColor.YELLOW + "Command set");
			}
		}
	}
}
