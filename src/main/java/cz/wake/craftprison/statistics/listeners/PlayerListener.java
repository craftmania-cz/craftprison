package cz.wake.craftprison.statistics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements Listener {

    public PlayerListener() {
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //blocksBreak.put((CraftPlayer)event.getPlayer(), 1);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        //deaths.put((CraftPlayer)event.getEntity().getPlayer(), 1);
    }

}
