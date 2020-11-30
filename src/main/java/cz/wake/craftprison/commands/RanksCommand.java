package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.craftprison.menu.RanksMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("ranks")
@Description("Seznam všech dostupných ranků a jejich postup.")
public class RanksCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lRanks příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void showRanks(CommandSender sender){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            SmartInventory.builder().id("ranks").provider(new RanksMenu()).size(5, 9).title("Přehled ranků").build().open(player);
        } else {
            sender.sendMessage("Pouze hráč může používat tento příkaz.");
        }
    }
}
