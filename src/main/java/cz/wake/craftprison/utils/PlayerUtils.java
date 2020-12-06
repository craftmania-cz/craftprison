package cz.wake.craftprison.utils;

import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.text.DecimalFormat;
import java.util.Random;

public class PlayerUtils {

    public static String TEXT_BOXES = "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬";

    public static void addPermission(final Player p, String permission) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set " + permission + " prison");
    }

    public static void removePermission(final Player p, String permission) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset " + permission + " prison");
    }

    public static void sendRankMessage(final Player player) {
        PrisonManager pm = new PrisonManager();
        if(pm.getPlayerRank(player) == Rank.getLast()) {
            player.sendMessage("§a" + TEXT_BOXES);
            player.sendMessage("");
            player.sendMessage("§6Úspěšně jsi dokončil ranky v aktuální Prestige.");
            player.sendMessage("§7Nyní můžeš pokračovat navýšením Prestige pomocí §f/prestige");
            player.sendMessage("");
            player.sendMessage("§a" + TEXT_BOXES);
            return;
        }
        player.sendMessage("§6" + TEXT_BOXES);
        player.sendMessage("");
        player.sendMessage("§7Aktuální rank: §f" + pm.getPlayerRank(player).getName() + " (" + pm.getPlayerRank(player).getWeight() + ") " + "§8| §fDalší rank: " + pm.getPlayerNextRank(player).getName() + " (" + pm.getPlayerNextRank(player).getWeight() + ") ");
        //player.sendMessage("§7Dokonceni ranku: §f" + ActionBarProgress.getPercent(Main.getEconomy().getBalance(player), pm.getPlayerRank(player).getNext().getPrice()) + "% §8| §7Rankup castka: §f" + formatMoney(pm.getPlayerRank(player).getNext().getPrice()) +"§a$");
        player.sendMessage("§7Dokončení prestige: §f" + ActionBarProgress.getPercent(pm.getPlayerRank(player).getWeight(), Rank.getLast().getWeight()) + "%");
        player.sendMessage("");
        player.sendMessage("§eNa další ranku obdržíš: \n§f - " + pm.getPlayerNextRank(player).getEnchantToken() + "x EnchantToken\n - " + pm.getPlayerNextRank(player).getReward());
        player.sendMessage("");
        player.sendMessage("§6" + TEXT_BOXES);
    }

    public static void sendRankUpMessage(final Player player) {
        PrisonManager pm = new PrisonManager();
        player.sendMessage("§d" + TEXT_BOXES);
        player.sendMessage("");
        player.sendMessage("§e§lNavýšil jsi svůj rank!");
        player.sendMessage("§6Aktuální rank: §f" + pm.getPlayerRank(player));
        player.sendMessage("");
        player.sendMessage("§d" + TEXT_BOXES);
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

    public static void randomFireworks(final Location l) {
        final Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
        final FireworkMeta fwm = fw.getFireworkMeta();
        final int rt = new Random().nextInt(3) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) {
            type = FireworkEffect.Type.BALL;
        }
        if (rt == 2) {
            type = FireworkEffect.Type.BALL_LARGE;
        }
        if (rt == 3) {
            type = FireworkEffect.Type.BURST;
        }
        if (rt == 4) {
            type = FireworkEffect.Type.STAR;
        }
        int r = new Random().nextInt(256);
        int b = new Random().nextInt(256);
        int g = new Random().nextInt(256);
        final Color c1 = Color.fromRGB(r, g, b);
        r = new Random().nextInt(256);
        b = new Random().nextInt(256);
        g = new Random().nextInt(256);
        final Color c2 = Color.fromRGB(r, g, b);
        final FireworkEffect effect = FireworkEffect.builder().flicker(new Random().nextBoolean()).withColor(c1).withFade(c2).with(type).trail(new Random().nextBoolean()).build();
        fwm.addEffect(effect);
        final int rp = new Random().nextInt(2) + 1;
        fwm.setPower(rp);
        fw.setFireworkMeta(fwm);
    }

}
