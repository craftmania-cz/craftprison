package cz.wake.craftprison.commands;

import cz.wake.craftprison.statistics.menu.StatisticsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsTopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("statstop")) {
            StatisticsMenu.openTop(player);
        }
        return false;
    }
}
