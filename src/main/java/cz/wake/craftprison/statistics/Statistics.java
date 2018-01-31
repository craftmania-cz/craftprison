package cz.wake.craftprison.statistics;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.statistics.listeners.PlayerStatsListener;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Statistics {

    private PlayerStatsListener playerStatsListener;
    private Main main;

    private HashMap<Player, Integer> blocksBroken;
    private HashMap<Player, Integer> deaths;
    private HashMap<Player, Integer> kills;

    public Statistics(Main main) {
        this.main = main;

        blocksBroken = new HashMap<>();
        deaths = new HashMap<>();
        kills = new HashMap<>();
    }

    /*
        Blocks broken
     */
    public int getBlocksBroken(Player player) {
        if (blocksBroken.containsKey(player)) {
            return blocksBroken.get(player);
        }
        return 0;
    }

    public void setBlocksBroken(Player player, int value) {
        blocksBroken.put(player, main.getStatistics().getBlocksBroken(player) + value);
    }

    /*
        Deaths
     */
    public int getDeaths(Player player) {
        if (deaths.containsKey(player)) {
            return deaths.get(player);
        }
        return 0;
    }

    public void setDeaths(Player player, int value) {
        deaths.put(player, main.getStatistics().getDeaths(player) + value);
    }

    /*
       Kills
    */
    public int getKills(Player player) {
        if (kills.containsKey(player)) {
            return kills.get(player);
        }
        return 0;
    }

    public void setKills(Player player, int value) {
        kills.put(player, main.getStatistics().getKills(player) + value);
    }

    public void clearAllMaps() {
        blocksBroken.clear();
        kills.clear();
        blocksBroken.clear();
    }
}



