package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.craftprison.menu.HelpMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("help")
@Description("Příkaz na pomoc")
public class HelpMenuCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lHelp příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void showHelpMenu(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            SmartInventory.builder().id("help_menu").size(4, 9).provider(new HelpMenu()).title("Pomoc").build().open(player);

        } else {
            sender.sendMessage("§cKonzole nemůže používat Help příkaz.");
        }
    }
}
