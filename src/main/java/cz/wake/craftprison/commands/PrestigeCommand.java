package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.craftprison.menu.PrestigeMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("prestige")
@Description("Příkaz na navýšení prestige")
public class PrestigeCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lPrestige příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void showPrestigeMenu(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            SmartInventory.builder().id("prestige_menu").size(5, 9).provider(new PrestigeMenu()).title("Prestige odměny").build().open(player);
        } else {
            sender.sendMessage("§cKonzole nemůže používat Prestige příkaz.");
        }
    }

}
