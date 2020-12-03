package cz.wake.craftprison.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.menu.RankupVerifyMenu;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static co.aikar.commands.ACFBukkitUtil.sendMsg;

@CommandAlias("rankup")
@Description("Navýšení ranku v aktuální Prestige.")
public class RankUpCommand extends BaseCommand {

    final PrisonManager pm = new PrisonManager();

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sendMsg(sender, "§e§lRankUp příkaz nápověda:");
        help.showHelp();
    }

    @Default
    public void doRankUp(CommandSender sender){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                if (pm.getPlayers().containsKey(player)) {
                    double playerMoney = Main.getInstance().getEconomy().getBalance(player);
                    final Rank actualRank = pm.getPlayerRank(player);
                    if (!(actualRank == Rank.getLast())) {
                        Rank nextRank = actualRank.getNext();
                        if (nextRank.getPrice() < playerMoney) {
                            SmartInventory.builder().id("rankup-menu").provider(new RankupVerifyMenu())
                                    .size(5, 9).title("Rank up").build().open(player);
                        } else {
                            player.sendMessage("§c§l[!] §cNemáš dostatek peněz k navýšení ranku!");
                        }
                    } else {
                        player.sendMessage("§c§l[!] §cDalší písmenko v abecedě již není, nyní musíš zkusit §f/prestige");
                    }
                } else { // Když hráč není v cache, nepravděpodobné
                    player.sendMessage("");
                    player.sendMessage("§c§lCHYBA V ŽABIČKOVÉM SYSTÉMU!!!");
                    player.sendMessage("§fPokud čteš tuto zprávu, jedná se o chybu - kontaktu vedení.");
                    player.sendMessage("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Wejku, ty papriko! Console nema rank, console ma vse! :herold:");
        }
    }
}
