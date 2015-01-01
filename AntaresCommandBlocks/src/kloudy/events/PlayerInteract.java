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
import org.bukkit.inventory.ItemStack;
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
			BookMeta book = (BookMeta)player.getItemInHand().getItemMeta();
			
			//insert command from book into CommandBlock
			if(block.getType() == Material.COMMAND)
			{	
				CommandBlock commandBlock = (CommandBlock)block.getState();

				insertCommand(player, commandBlock, book);
			}
			
			//player can left click on a QuartzBlock with book to turn it into a CommandBlock that contains the command in the book
			if(block.getType() == Material.QUARTZ_BLOCK)
			{
				block.setType(Material.COMMAND);
				CommandBlock commandBlock = (CommandBlock)block.getState();
				
				insertCommand(player, commandBlock, book);
			}
			
			//player can quickly clear book by left clicking CobbleStone while holding the book
			if(block.getType() == Material.COBBLESTONE)
			{
				player.setItemInHand(new ItemStack(Material.BOOK_AND_QUILL));
				
				player.sendMessage(ChatColor.YELLOW + "Book cleared");
			}
		}
	}
	
	private void insertCommand(Player player, CommandBlock commandBlock, BookMeta book)
	{
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
