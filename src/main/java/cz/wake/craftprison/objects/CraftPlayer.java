package cz.wake.craftprison.objects;

import org.bukkit.entity.Player;

public class CraftPlayer implements IMiner {

    private Player p;
    private Rank rank;
    private int minedBlocks;
    private int prisCoins;

    public CraftPlayer() {};

    public CraftPlayer(Player p) {
        this.p = p;
    }

    public CraftPlayer(Player p, Rank r, int minedBlocks, int prisCoins){
        this.p = p;
        this.rank = r;
        this.minedBlocks = minedBlocks;
        this.prisCoins = prisCoins;
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

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setMinedBlocks(int minedBlocks) {
        this.minedBlocks = minedBlocks;
    }

    public void setPrisCoins(int prisCoins) {
        this.prisCoins = prisCoins;
    }
}
