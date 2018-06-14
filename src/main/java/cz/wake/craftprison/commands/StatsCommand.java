package cz.wake.craftprison.commands;

import cz.wake.craftprison.statistics.menu.StatisticsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("stats")) {
            if (args.length == 0) {
                StatisticsMenu.open(p);
                return true;
            } else if (args.length == 1) {
                StatisticsMenu.openOffline(p, args[0]);
                return true;
            } else {
                p.sendMessage("Spatne argumenty: /stats <nick>");
            }
        }
        return false;
    }
}
