package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("tokengive")
@Description("Obchází nefunkční zprávy při dávání tokenenchantu")
public class TokenGiveCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lTokenGive příkaz nápověda:");
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players [hodnota]")
    @Syntax("[nick] [částka] - Teleport na určitý rank")
    @CommandPermission("craftprison.commands.tokens.give")
    public void giveTokens (CommandSender sender, String player, long tokens) {

        if (tokens <= 0) {
            sender.sendMessage("§c§l[!] §cNelze dávat nulové hodnoty nebo menší jak 0!");
            return;
        }

        Player requestedPlayer = Bukkit.getPlayer(player);
        if (requestedPlayer == null) {
            sender.sendMessage("§c§l[!] §cHráč není online.");
            return;
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tokens add " + requestedPlayer.getName() + " " + tokens);
        requestedPlayer.sendMessage("§e§l[T] §eBylo ti přidáno " + tokens + " EnchantTokenů.");
        System.out.println("§ePřidal jsi hráči " + requestedPlayer.getName() + " - " + tokens + " ET");
    }

}
