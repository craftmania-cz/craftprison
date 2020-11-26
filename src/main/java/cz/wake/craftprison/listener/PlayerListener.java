package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.utils.PermFixes;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Main main;
    private PermFixes pf = new PermFixes();

    public PlayerListener(Main main) {
        this.main = main;
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // Preventivni vypnuti join zprav
        e.setJoinMessage(null);

        // Nacteni dat z SQL
        PrisonManager.loadPlayer(p);

        // Opravy chyb a chybejicich prav
        pf.fixFor(p);
    }

    @EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        // Preventivni vypnuti leave zprav
        e.setQuitMessage(null);
    }

    @EventHandler(ignoreCancelled = true)
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();

        // Preventivni vypnuti leave zprav
        e.setLeaveMessage(null);
    }
}
