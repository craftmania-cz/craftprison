package cz.wake.craftprison.api;

import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class PrisonAPI {

    final PrisonManager prisonManager = new PrisonManager();

    /**
     * Vrací objekt hráče z cache.
     * Objekt může být null, pokud hráč je offline nebo nastala chyba.
     *
     * @param player    Požadovaný hráč
     * @return          {@link CraftPlayer}
     */
    @Nullable
    public CraftPlayer getPlayer(@NotNull final Player player) {
        return this.prisonManager.getPlayers().get(player);
    }

    /**
     * Vrací hashmap všech hráčů v cache.
     *
     * @return {@link HashMap}
     */
    @NotNull
    public HashMap<Player, CraftPlayer> getAllPlayers() {
        return this.prisonManager.getPlayers();
    }

    /**
     * Vrací seznam ranků, které jsou stejné pro všechny Prestiges.
     *
     * @return {@link Rank[]}
     */
    @NotNull
    public Rank[] getRanks() {
        return Rank.getRankList();
    }
}
