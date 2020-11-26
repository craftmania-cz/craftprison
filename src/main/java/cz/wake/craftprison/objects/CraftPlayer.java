package cz.wake.craftprison.objects;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CraftPlayer implements IMiner {

    private Player player;
    private Rank rank;
    private int minedBlocks;
    private int prisCoins;
    private int kills;
    private int deaths;

    public CraftPlayer() {
    }

    public CraftPlayer(@NotNull Player player) {
        this.player = player;
    }

    public CraftPlayer(@NotNull Player player,@NotNull Rank rank) {
        this.player = player;
        this.rank = rank;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
