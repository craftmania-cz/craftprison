package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("mine|dul")
@Description("Teleport na aktuální důl pro tvůj rank nebo teleport na jiný důl")
public class MineCommand extends BaseCommand {

    final PrisonManager pm = new PrisonManager();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lMine příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void mineTeleport(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.performCommand("warp " + pm.getPlayerRank(player).getName().toLowerCase()); //TODO: Dynamický teleport dle ranku
        }
    }
}
