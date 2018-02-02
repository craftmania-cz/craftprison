package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Main main;

    public PlayerListener(Main main) {
        this.main = main;
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        // Nacteni dat z SQL
        PrisonManager.loadPlayer(p);
    }

    @EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        Main.getInstance().getMySQL().setAllFromCache(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onKick(PlayerKickEvent event) {
        Main.getInstance().getMySQL().setAllFromCache(event.getPlayer());
    }
}
