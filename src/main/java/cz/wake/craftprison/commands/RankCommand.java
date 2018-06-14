package cz.wake.craftprison.commands;

import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("rank"))) {
                PlayerUtils.sendRankMessage(player);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Wejku, ty papriko! Console nema rank, console ma vse! :herold:");
        }

        return false;
    }
}
