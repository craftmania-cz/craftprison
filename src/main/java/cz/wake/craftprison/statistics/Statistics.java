package cz.wake.craftprison.statistics;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.statistics.listeners.PlayerListener;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class Statistics {

    private PlayerListener playerListener;
    private Main main;

    private HashMap<CraftPlayer, Integer> blocksBroken;
    private HashMap<CraftPlayer, Integer> deaths;
    private HashMap<CraftPlayer, Integer> kills;
    private HashMap<CraftPlayer, Integer> blocksPlaced;


    public Statistics() {
        blocksBroken = new HashMap<>();
        deaths = new HashMap<>();
        kills = new HashMap<>();
        blocksPlaced = new HashMap<>();

        playerListener = new PlayerListener();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), main);
    }

    /*
        Blocks broken
     */
    public Integer getBlocksBroken(CraftPlayer player) {
        if (blocksBroken.containsKey(player)) {
            return blocksBroken.get(player);
        }
        return 0;
    }

    public void setBlocksBroken(CraftPlayer player, int value) {
        blocksBroken.put(player, value);
    }

    /*
        Deaths
     */
    public Integer getDeaths(CraftPlayer player) {
        if (deaths.containsKey(player)) {
            return deaths.get(player);
        }
        return 0;
    }

    public void setDeaths(CraftPlayer player, int value) {
        deaths.put(player, value);
    }

     /*
        Kills
     */
    public Integer getKills(CraftPlayer player) {
        if (kills.containsKey(player)) {
            return kills.get(player);
        }
        return 0;
    }

    public void setKills(CraftPlayer player, int value) {
        kills.put(player, value);
    }

    /*
        Blocks placed
     */
    public Integer getBlocksPlaced(CraftPlayer player) {
        if (blocksPlaced.containsKey(player)) {
            return blocksPlaced.get(player);
        }
        return 0;
    }

    public void setBlocksPlaced(CraftPlayer player, int value) {
        blocksPlaced.put(player, value);
    }
}



