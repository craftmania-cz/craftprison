package cz.wake.craftprison.modules;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class PrisonManager {

    public static HashMap<Player, CraftPlayer> players = new HashMap<>();
    public static HashSet<String> wgRegions = new HashSet<>();
    private static Rank rank;

    /*
        Pouzivat pouze pri nacitani dat z SQL!
     */
    public static void loadPlayer(final Player p) {

        CraftPlayer cp = null;

        if (!Main.getInstance().getMySQL().hasData(p)) {
            // Vytvoreni default dat
            Main.getInstance().getMySQL().insertDefaultData(p);
            cp = new CraftPlayer(p, Rank.A, 1);

            //Player counter
            int count = Main.getInstance().getConfig().getInt("player-counter") + 1;
            Bukkit.getOnlinePlayers().forEach(op -> op.sendMessage("§e" + p.getName() + " §7se pripojil na Prison (#" + count + ")"));
            Main.getInstance().getConfig().set("player-counter", count);
            Main.getInstance().saveConfig();

            //Krumpac
            p.getInventory().addItem(PickaxeUpgrade.getDefaultPickaxe(p.getName()));
        } else {
            cp = Main.getInstance().getMySQL().getCraftPlayerFromSQL(p);
        }

        // Prevence proti NPE z SQL
        if (cp == null) {
            cp = new CraftPlayer(p, Rank.A, 1);
        }

        players.put(p, cp);
    }

    public static HashMap<Player, CraftPlayer> getCraftPlayersCache() {
        return players;
    }

    public static HashSet<String> getWgRegions() {
        return wgRegions;
    }

    public static void addWorldGuardRegion(String region) {
        wgRegions.add(region);
    }

    public static void registerWgMines() {
        for (Rank r : Rank.getRankList()) {
            // Register
            wgRegions.add(r.getName().toLowerCase());

            // Info
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftPrison] " + ChatColor.WHITE + " Dul: " + r.getName() + " byl registrovan jako dul.");
        }
    }

    public CraftPlayer getCraftPlayer(Player p) {
        for (CraftPlayer cp : players.values()) {
            if (cp.getPlayer() == p) {
                return cp;
            }
        }
        return null;
    }

    public Rank getPlayerRank(final Player p) {
        return players.get(p).getRank();
    }

    public Rank getNextRank(final Player p) throws NullPointerException {
        return getPlayerRank(p).getNext();
    }

    public long getNextRankPrice(final Player p) {
        return getNextRank(p).getBasePrice();
    }

    public long getNextRankPriceBasedPrestige(final Player p) {
        return getNextRank(p).getBasePrice();
    }

    public long getActualRankPrice(final Player p) {
        return getPlayerRank(p).getBasePrice();
    }

    public Rank getRankObject() {
        return rank;
    }

    public HashMap<Player, CraftPlayer> getPlayers() {
        return players;
    }
}
