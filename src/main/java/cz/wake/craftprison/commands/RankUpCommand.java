package cz.wake.craftprison.commands;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankUpCommand implements CommandExecutor {

    PrisonManager pm = new PrisonManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("rankup"))) {
                try {
                    if (pm.getPlayers().containsKey(player)) {
                        double playerMoney = Main.getEconomy().getBalance(player);
                        Rank actualRank = pm.getPlayerRank(player);
                        if (!(actualRank == Rank.getLast())) {
                            Rank nextRank = actualRank.getNext();
                            if (nextRank.getPrice() <= playerMoney) {
                                PrisonManager.RankupVerifyMenu.RANKUP.open(player);
                            } else {
                                player.sendMessage("§c§l(!) §cNemas dostatek penez na rankup!");
                            }
                        } else {
                            player.sendMessage("§c§l(!) §cNo dal to nejde! Zkus /reset a zacni odznova!");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Wejku, ty papriko! Console nema rank, console ma vse! :herold:");
        }

        return false;
    }
}
