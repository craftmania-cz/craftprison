package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.pickaxe.CustomPickaxe;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("pickaxe|krumpac")
@Description("Získání defaultního krumpáče")
public class PickaxeCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lPickaxe příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void getPickaxe(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("craftprison.player.pickaxe.hard-cooldown")) {
                player.sendMessage("§c§l[!] §cMusíš počkat déle, aby jsi mohl opět získat základní krumpáč.");
                return;
            }
            player.getInventory().addItem(PickaxeUpgrade.getDefaultPickaxe(player.getName()));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission settemp craftprison.player.pickaxe.hard-cooldown 2h prison");
            player.sendMessage("§a§l[x] §aObdržel jsi základní krumpáč.");
        } else {
            sender.sendMessage("§c§l[!] §cK získání krumpáče musíš být hráč");
        }
    }

    @Subcommand("debug")
    @CommandPermission("craftprison.pickaxe.debug")
    public void getDebugPickaxe(CommandSender sender) {
        Player player = (Player) sender;
        player.getInventory().addItem(PickaxeUpgrade.getDebugPickaxe(player.getName()));
        player.sendMessage("§a§l[x] §aObdržel jsi debug krumpáč.");
    }
}
