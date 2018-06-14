package cz.wake.craftprison.utils;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class PlayerUtils {

    public static void addPermission(final Player p, String permission) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set " + permission);
    }

    public static void removePermission(final Player p, String permission) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset " + permission);
    }

    public static void sendRankUpMessage(final Player p, final Rank r) {
        p.sendMessage("§eGratulujeme k Rankupu do ranku §b" + r.getName());
    }

    public static void sendRankMessage(final Player p) {
        PrisonManager pm = new PrisonManager();
        p.sendMessage("§6\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
        p.sendMessage("");
        p.sendMessage("§7Aktualni rank: " + pm.getColoredPlayerRank(p) + " §8| §7Dalsi rank: " + pm.getColoredNextPlayerRank(p));
        p.sendMessage("§7Dokonceni ranku: §f" + ActionBarProgress.getPercent(Main.getEconomy().getBalance(p), pm.getPlayerRank(p).getNext().getPrice()) + "% §8| §7Rankup castka: §f" + formatMoney(pm.getPlayerRank(p).getNext().getPrice()) +"§a$");
        p.sendMessage("§7Celkove dokonceni: §f" + String.valueOf(ActionBarProgress.getPercent(pm.getPlayerRank(p).getWeight(), Rank.getLast().getWeight())) + "% §8| §7Obtiznost: §eEasy");
        p.sendMessage("");
        p.sendMessage("§7Pri rankup obdrzis: §fNighVision v dolech, 1x PrisCoin");
        p.sendMessage("");
        p.sendMessage("§6\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
    }

    public static String formatMoney(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (d >= 1.0E18) {
            return df.format(d / 1.0E18) + "Q";
        }
        if (d >= 1.0E15) {
            return df.format(d / 1.0E15) + "T";
        }
        if (d >= 1.0E12) {
            return df.format(d / 1.0E12) + "B";
        }
        if (d >= 1000000000) {
            return df.format(d / 1000000000) + "G";
        }
        if (d >= 1000000) {
            return df.format(d / 1000000) + "M";
        }
        if (d >= 1000) {
            return df.format(d / 1000) + "k";
        }

        return df.format(d);
    }
}
