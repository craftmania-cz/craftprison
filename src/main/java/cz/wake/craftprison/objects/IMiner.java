package cz.wake.craftprison.objects;

import org.bukkit.entity.Player;

public interface IMiner {

    Player getPlayer();

    Rank getRank();

    int getPrisCoins();

    int getMinedBlocks();

    boolean isAT();


}
