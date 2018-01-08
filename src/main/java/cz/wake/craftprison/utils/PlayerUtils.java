package cz.wake.craftprison.utils;

import cz.wake.craftprison.modules.RankManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void addPermission(final Player p, String permission){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set " + permission);
    }

    public static void removePermission(final Player p, String permission){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset " + permission);
    }

    public static void sendRankUpMessage(final Player p, final Rank r){
        p.sendMessage("§eGratulujeme k Rankupu do ranku §b" + r.getName());
    }

    public static void sendRankMessage(final Player p){
        RankManager manager = new RankManager();
        p.sendMessage("§a§l======================================");
        p.sendMessage("§eTvuj aktualni rank: " + manager.getPlayerRank(p).getName());
        p.sendMessage("§eTvuj dalsi rank: " + manager.getPlayerRank(p).getNext().getName());
        p.sendMessage("§a§l======================================");
    }
}
