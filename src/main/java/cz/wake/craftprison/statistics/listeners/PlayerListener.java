package cz.wake.craftprison.statistics.listeners;

import cz.wake.craftprison.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements Listener {

    private Main main;

    public PlayerListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        main.getStatistics().setBlocksBroken(event.getPlayer(), 1);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
    }

}
