package cz.wake.craftprison.modules;

import cz.craftmania.craftcore.spigot.messages.ActionBar;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBarProgress {

    static PrisonManager pm = new PrisonManager();

    public static void send() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getLocation().getWorld().getName().equals("doly") || p.getLocation().getWorld().getName().equals("prison_spawn")) {
                CraftPlayer cp = pm.getCraftPlayer(p);
                if (cp.getRank() != Rank.getLast()) {
                    //new ActionBar(getText(Main.getEconomy().getBalance(p), cp.getRank().getNext().getPrice()), -1).send(p);
                }
            }
        }
    }

    public static double getPercent(double currentMoney, double neededMoney) {
        double percent = Math.round(currentMoney / neededMoney * 10000.0) / 100.0;
        return percent > 100.0 ? 100.0 : percent;
    }

    private static String getText(double currentMoney, double neededMoney) {
        int parts = 10;
        double percent = getPercent(currentMoney, neededMoney);

        int part = (int) (parts / 100.0 * percent);
        StringBuilder sb = new StringBuilder();
        sb.append("§a§l").append(percent).append("%").append(" ");
        for (int i = 0; i < part; ++i) {
            sb.append("§a\u2588");
        }
        for (int i = 0; i < parts - part; ++i) {
            sb.append("§c\u2588");
        }
        return sb.toString();
    }
}
