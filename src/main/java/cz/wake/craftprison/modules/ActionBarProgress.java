package cz.wake.craftprison.modules;

import cz.craftmania.craftcore.spigot.xseries.messages.ActionBar;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBarProgress {

    static PrisonManager pm = new PrisonManager();

    public static void send() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().getWorld().getName().equals("mines") || player.getLocation().getWorld().getName().equals("spawn")) {
                CraftPlayer craftPlayer = pm.getCraftPlayer(player);
                if (craftPlayer.getRank() != Rank.getLast()) {
                    ActionBar.sendActionBar(player, getText(Main.getInstance().getEconomy().getBalance(player), craftPlayer.getRank().getNext().getPriceByPrestige(craftPlayer.getPrestige())));
                } else { //TODO: Počítat peníze pro prestige up
                    ActionBar.sendActionBar(player, "§b§lPrestige dokončena! §f/prestige");
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

        if (percent >= 100) {
            return "§a\u2588\u2588\u2588\u2588 §f/rankup §a\u2588\u2588\u2588\u2588";
        }

        int part = (int) (parts / 100.0 * percent);
        StringBuilder sb = new StringBuilder();
        sb.append("§a").append(percent).append("%").append(" ");
        for (int i = 0; i < part; ++i) {
            sb.append("§a\u2588");
        }
        for (int i = 0; i < parts - part; ++i) {
            sb.append("§c\u2588");
        }
        return sb.toString();
    }
}
