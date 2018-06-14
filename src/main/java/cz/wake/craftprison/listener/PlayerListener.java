package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.Board;
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
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // Preventivni vypnuti join zprav
        e.setJoinMessage(null);

        // Nacteni dat z SQL
        PrisonManager.loadPlayer(p);

        new Board(p);
    }

    @EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Main.getInstance().getMySQL().setAllFromCache(p);

        // Preventivni vypnuti leave zprav
        e.setQuitMessage(null);

        if (Board.boards.containsKey(p)) {
            Board b = Board.boards.get(p);
            b.unregister();
            Board.boards.remove(p);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        Main.getInstance().getMySQL().setAllFromCache(p);

        // Preventivni vypnuti leave zprav
        e.setLeaveMessage(null);

        if (Board.boards.containsKey(p)) {
            Board b = Board.boards.get(p);
            b.unregister();
            Board.boards.remove(p);
        }
    }
}
