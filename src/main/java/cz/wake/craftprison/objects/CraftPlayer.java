package cz.wake.craftprison.objects;

import org.bukkit.entity.Player;

public class CraftPlayer implements IMiner {

    private Player p;
    private Rank rank;
    private int minedBlocks;
    private int prisCoins;
    private int kills;
    private int deaths;

    public CraftPlayer(){}

    public CraftPlayer(Player p) {
        this.p = p;
    }

    public CraftPlayer(Player p, Rank r, int prisCoins, int minedBlocks, int kills, int deaths) {
        this.p = p;
        this.rank = r;
        this.minedBlocks = minedBlocks;
        this.prisCoins = prisCoins;
        this.deaths = deaths;
        this.kills = kills;
    }

    @Override
    public Player getPlayer() {
        return this.p;
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int getPrisCoins() {
        return this.prisCoins;
    }

    @Override
    public int getMinedBlocks() {
        return this.minedBlocks;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setMinedBlocks(int minedBlocks) {
        this.minedBlocks = minedBlocks;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setPrisCoins(int prisCoins) {
        this.prisCoins = prisCoins;
    }

    public void addMinedBlock() {
        this.minedBlocks += 1;
    }

    public void addKill() {
        this.kills += 1;
    }

    public void addDeath() {
        this.deaths += 1;
    }

}
