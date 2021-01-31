package cz.wake.craftprison.utils;

import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PermFixes {

    PrisonManager pm = new PrisonManager();

    public void fixFor(final Player player) {

        CraftPlayer craftPlayer = pm.getCraftPlayer(player);
        if (craftPlayer.getPrestige() >= 5 && !player.hasPermission("craftkeeper.mine.P5")) {
            player.sendMessage("§e§l[!] §eByl ti odemknut důl P5. §f/mine P5");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set craftkeeper.mine.P5 prison");
        }

        if (craftPlayer.getPrestige() >= 8 && !player.hasPermission("craftkeeper.mine.P8")) {
            player.sendMessage("§e§l[!] §eByl ti odemknut písečný důl P8. §f/mine P8");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set craftkeeper.mine.P8 prison");
        }

        if (craftPlayer.getPrestige() >= 12 && !player.hasPermission("craftkeeper.mine.P12")) {
            player.sendMessage("§e§l[!] §eByl ti odemknut důl P12. §f/mine P12");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set craftkeeper.mine.P12 prison");
        }

    }
}
