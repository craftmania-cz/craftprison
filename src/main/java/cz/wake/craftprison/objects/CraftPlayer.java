package cz.wake.craftprison.objects;

import at.pcgamingfreaks.Minepacks.Bukkit.API.Backpack;
import cz.wake.craftprison.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CraftPlayer implements IMiner {

    private Player player;
    private Rank rank;
    private int prestige;

    public CraftPlayer() {
    }

    public CraftPlayer(@NotNull Player player) {
        this.player = player;
    }

    /**
     * Základní objekt každého hráče, co je připojený na Prison serveru
     */
    public CraftPlayer(@NotNull Player player, @NotNull Rank rank, @NotNull int prestige) {
        this.player = player;
        this.rank = rank;
        this.prestige = prestige;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Rank hráče, vždy je hodnota mezi A-Z podle {@link Rank}
     * @return {@link Rank}
     */
    @Override
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Nastaví rank hráči v cache a uloží do SQL.
     * @param rank {@link Rank}
     */
    public void setRank(@NotNull Rank rank) {
        this.rank = rank;
        Main.getInstance().getMySQL().playerSaveRank(this.player, rank);
    }

    /**
     * Vrací číslo prestige, v jaké aktuálně hráče je
     * @return Číslo od 1+
     */
    public int getPrestige() {
        return prestige;
    }

    /**
     * Nastaví prestige hráči v cache a uloží do SQL
     * @param prestige Číslo prestige
     */
    public void setPrestige(int prestige) {
        this.prestige = prestige;
        Main.getInstance().getMySQL().playerSavePrestige(player, prestige);
    }

    /**
     * Vrací vlastní backpack hráče, tedy jeho inventář, do kterého lze ukládat, brát nebo lontrolovat itemy.
     *
     * @param player Požadovaný hráč
     * @return {@link Inventory}
     */
    public Backpack getPlayerBackpackInventory(Player player) {
        Backpack bp = Main.getInstance().getMinepacksPlugin().getBackpackCachedOnly(player);
        if (bp == null) return null;
        return bp;
    }
}
