package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("rank")
@Description("Zobrazení postupu v aktuálním ranku")
public class RankCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lRank příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void showRankProgress(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerUtils.sendRankMessage(player);
        } else {
            sender.sendMessage(ChatColor.RED + "Wejku, ty papriko! Console nema rank, console ma vse! :herold:");
        }
    }
}
