package cz.wake.craftprison.modules;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.exceptions.PlayerNotInCacheException;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

public class PrisonManager {

    public static HashMap<Player, CraftPlayer> players = new HashMap<>();
    public static HashSet<String> wgRegions = new HashSet<>();

    /*
        Pouzivat pouze pri nacitani dat z SQL!
     */
    public static void loadPlayer(final Player p) {

        CraftPlayer cp = null;

        if (!Main.getInstance().getMySQL().hasData(p)) {
            // Vytvoreni default dat
            Main.getInstance().getMySQL().insertDefaultData(p);
            cp = new CraftPlayer(p, Rank.TUTORIAL_A, 0, 0);

            //Player counter
            int count = Main.getInstance().getConfig().getInt("player-counter") + 1;
            Bukkit.getOnlinePlayers().forEach(op -> op.sendMessage("§e" + p.getName() + " §7se pripojil na Prison (#" + count + ")"));
            Main.getInstance().getConfig().set("player-counter", count);
            Main.getInstance().saveConfig();
        } else {
            try {
                ResultSet set = Main.getInstance().getMySQL().getPool().getConnection().createStatement().executeQuery("SELECT * FROM players_data WHERE nick = '" + p.getName() + "';");
                while (set.next()) {
                    try {
                        // Nacteni z SQL
                        cp = new CraftPlayer(p, Rank.getByName(set.getString("rank")), set.getInt("priscoins"), set.getInt("minedblocks"));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                set.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Prevence proti NPE
        if (cp == null) {
            cp = new CraftPlayer(p, Rank.TUTORIAL_A, 0, 0);
        }

        players.put(p, cp);
    }

    public static HashMap<Player, CraftPlayer> getCraftPlayersCache() {
        return players;
    }

    public CraftPlayer getCraftPlayer(Player p) {
        for (CraftPlayer cp : players.values()) {
            if (cp.getPlayer() == p) {
                return cp;
            }
        }
        return null; //TODO Ochrana proti NPE (i kdyz to je blbost kvuli loadPlayer())
    }

    public Rank getPlayerRank(Player p) {
        return players.get(p).getRank();
    }

    public Rank getPlayerNextRank(Player p) {
        return getPlayerRank(p).getNext();
    }

    public int getNextRankPrice(Player p) {
        return getPlayerNextRank(p).getPrice();
    }

    public int getActualRankPrice(Player p) {
        return getPlayerRank(p).getPrice();
    }

    public void rankUp(final Player p) throws PlayerNotInCacheException {
        if (players.containsKey(p)) {
            CraftPlayer cp = players.get(p);
            double playerMoney = Main.getEconomy().getBalance(p);
            Rank actualRank = getPlayerRank(p);
            if (!(actualRank == Rank.GOOFY)) { // POSLEDNI RANK (ZATIM)
                Rank nextRank = actualRank.getNext();
                if (nextRank.getPrice() <= playerMoney) {
                    if (!(actualRank == Rank.TUTORIAL_A)) { // V zakladu hrac nema zadny rank pravo
                        PlayerUtils.removePermission(p, actualRank.getPermission());
                    }
                    PlayerUtils.addPermission(p, nextRank.getPermission());
                    PlayerUtils.sendRankUpMessage(p, nextRank);
                    cp.setRank(nextRank);
                    //todo: sql
                    //todo: efekty, menu overovani
                } else {
                    p.sendMessage("§cNemas dostatek penez na rankup!");
                }
            } else {
                p.sendMessage("§cNo dal to nejde! Zkus /reset a zacni odznova!");
            }
        } else {
            throw (new PlayerNotInCacheException("Hrac neni v cache!")); // Jenom pro efekt!
        }
    }

    public static HashSet<String> getWgRegions() {
        return wgRegions;
    }

    public static void addWorldGuardRegion(String region) {
        wgRegions.add(region);
    }

    public static void registerWgMines(){
        for(Rank r : Rank.getTypes()){
            wgRegions.add(r.getName().toLowerCase());
        }
    }
}
