package cz.wake.craftprison.objects;

import org.bukkit.entity.Player;

public class CraftPlayer implements IMiner {

    private Player p;
    private Rank rank;
    private int minedBlocks;
    private int prisCoins;

    public CraftPlayer(Player p) {
        this.p = p;
        this.rank = rank; // z SQL
        this.minedBlocks = minedBlocks; // z SQL
        this.prisCoins = prisCoins; // z SQL
    }

    public void loadToCache() {
        //TODO: PrisonManager list
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
}
