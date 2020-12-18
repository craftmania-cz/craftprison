package cz.wake.craftprison.hooks;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderRegistry extends PlaceholderExpansion {

    private final PrisonManager prisonManager = new PrisonManager();

    @Override
    public @NotNull String getIdentifier() {
        return "craftprison";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CraftMania.cz";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    public String onPlaceholderRequest(Player player, @NotNull String identifier){

        if (player == null) {
            return "";
        }

        CraftPlayer craftPlayer = prisonManager.getCraftPlayer(player);

        if (craftPlayer == null) {
            return "";
        }

        // %craftprison_player_rank%
        if (identifier.equals("player_rank")) {
            return String.valueOf(craftPlayer.getRank().getName());
        }

        // %craftprison_player_rank_progress%
        if (identifier.equals("players_rank_progress")) {
            return String.valueOf(ActionBarProgress.getPercent(Main.getInstance().getEconomy().getBalance(player), craftPlayer.getRank().getNext().getPriceByPrestige(craftPlayer.getPrestige())));
        }

        // %craftprison_player_prestige%
        if (identifier.equals("player_prestige")) {
            return String.valueOf(craftPlayer.getPrestige());
        }

        return "";
    }
}
