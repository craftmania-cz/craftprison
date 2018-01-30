package cz.wake.craftprison.commands;

import cz.wake.craftprison.exceptions.PlayerNotInCacheException;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankUpCommand implements CommandExecutor {

    PrisonManager pm = new PrisonManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("rankup"))) {
                try {
                    pm.rankUp(player);
                } catch (PlayerNotInCacheException e) {
                    e.printStackTrace();
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Wejku, ty papriko! Console nema rank, console ma vse! :herold:");
        }

        return false;
    }
}
