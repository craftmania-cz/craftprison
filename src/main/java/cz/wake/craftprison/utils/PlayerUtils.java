package cz.wake.craftprison.utils;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.text.DecimalFormat;
import java.util.Random;

public class PlayerUtils {

    public static void addPermission(final Player p, String permission) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set " + permission);
    }

    public static void removePermission(final Player p, String permission) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset " + permission);
    }

    public static void sendRankMessage(final Player p) {
        PrisonManager pm = new PrisonManager();
        p.sendMessage("§6\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
        p.sendMessage("");
        p.sendMessage("§7Aktualni rank: " + pm.getColoredPlayerRank(p) + " §8| §7Dalsi rank: " + pm.getColoredNextPlayerRank(p));
        p.sendMessage("§7Dokonceni ranku: §f" + ActionBarProgress.getPercent(Main.getEconomy().getBalance(p), pm.getPlayerRank(p).getNext().getPrice()) + "% §8| §7Rankup castka: §f" + formatMoney(pm.getPlayerRank(p).getNext().getPrice()) +"§a$");
        p.sendMessage("§7Celkove dokonceni: §f" + String.valueOf(ActionBarProgress.getPercent(pm.getPlayerRank(p).getWeight(), Rank.getLast().getWeight())) + "% §8| §7Obtiznost: §eEasy");
        p.sendMessage("");
        p.sendMessage("§7Pri rankupu obdrzis: §fNighVision v dolech, 1x PrisCoin");
        p.sendMessage("");
        p.sendMessage("§6\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
    }

    public static void sendRankUpMessage(final Player p, final Rank r) {
        PrisonManager pm = new PrisonManager();
        p.sendMessage("§d\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
        p.sendMessage("");
        p.sendMessage("§e§lGratulujeme k rankupu do dalsiho ranku!");
        p.sendMessage("§7Aktualni rank: " + pm.getColoredPlayerRank(p) + " §8| §7Dalsi rank: " + pm.getColoredNextPlayerRank(p));
        p.sendMessage("");
        p.sendMessage("§d\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
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
