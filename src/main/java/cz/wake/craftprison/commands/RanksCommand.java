package cz.wake.craftprison.commands;

import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PrisonManager.RanksMenu.RANKS.open(player);
        }
        return false;
    }
}
