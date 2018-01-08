package cz.wake.craftprison.listener;

import cz.wake.craftprison.modules.RankManager;
import cz.wake.craftprison.objects.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        RankManager.addToRankCache(p, Rank.TUTORIAL_A);
    }
}
