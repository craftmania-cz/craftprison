package cz.wake.craftprison.commands;

import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SellCommand implements CommandExecutor {

    PrisonManager pm = new PrisonManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("prodat"))) {
                player.performCommand("sellall " + pm.getPlayerRank(player).getName().toLowerCase());
            }
        }
        return true;
    }
}
