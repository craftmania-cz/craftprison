package cz.wake.craftprison.listener;

import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        // Nacteni dat z SQL
        PrisonManager.loadPlayer(p);
    }
}
