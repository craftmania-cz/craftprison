package cz.wake.craftprison.objects;

import org.bukkit.OfflinePlayer;

public class StatsPlayer {

    private OfflinePlayer p;
    private int priscoins;
    private int minedblocks;
    private int kills;
    private int deaths;

    public StatsPlayer() {

    }

    public StatsPlayer(OfflinePlayer p) {
        this.p = p;
    }
    
    public StatsPlayer(OfflinePlayer p, int priscoins, int minedblocks, int kills, int deaths) {
        this.p = p;
        this.minedblocks = minedblocks;
        this.priscoins = priscoins;
        this.deaths = deaths;
        this.kills = kills;
    }
    
    public OfflinePlayer getPlayer() {
        return this.p;
    }
    

    public int getPrisCoins() {
        return this.priscoins;
    }

    public int getminedblocks() {
        return this.minedblocks;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }


    public void setminedblocks(int minedblocks) {
        this.minedblocks = minedblocks;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setPrisCoins(int prisCoins) {
        this.priscoins = prisCoins;
    }

    public void addMinedBlock() {
        this.minedblocks += 1;
    }

    public void addKill() {
        this.kills += 1;
    }

    public void addDeath() {
        this.deaths += 1;
    }
}
