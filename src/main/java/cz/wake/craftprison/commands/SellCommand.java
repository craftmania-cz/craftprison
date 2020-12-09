package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.utils.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("sellall|prodat")
@Description("Prodej všechny své bloky do shopu v současném ranku")
public class SellCommand extends BaseCommand {

    final PrisonManager pm = new PrisonManager();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lSellall příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void sellAll(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.performCommand("sellall " + pm.getPlayerRank(player).getName().toLowerCase());
        } else {
            Logger.danger("Tento příkaz může použít jen hráč.");
        }
    }
}
