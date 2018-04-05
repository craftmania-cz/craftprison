package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.events.BlockUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BlockGlitchFixListeners implements Listener {

    private Main plugin;

    public BlockGlitchFixListeners(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onTeleportInterrupt(PlayerTeleportEvent e) {
        /* Although doesn't often happen, it "could" happen theoretically where the
         * plugin doesn't update the blocks fast enough and a player encounters a
         * glitched block If the player is teleported for an unknown reason a small
         * block update is made just to be "safe", although not necessarily necessary,
         * it helps further secure the impossibility of a player once again being stuck
         * in a block. */
        Player player = e.getPlayer();
        PlayerTeleportEvent.TeleportCause cause = e.getCause();
        if (cause == PlayerTeleportEvent.TeleportCause.UNKNOWN) { // Checks to see if the cause for the teleport is unkown (This also can be true
            // not just when a player is stuck in a block but plugins that do not mark a
            // reason for a player being teleported)
            BlockUpdateEvent event = new BlockUpdateEvent(player, BlockUpdateEvent.BlockUpdateReason.SAFETY_UPDATE);
            Bukkit.getPluginManager().callEvent(event); // Calls custom event
            if (!event.isCancelled()) {
                plugin.fixDataApp.updateBlocks(player, 2); // Updates a small "safe" radius of 2 blocks
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        // No need to continue checking to see if player is breaking blocks rapidly if
        // player suddenly decides
        // to place some blocks down :P
        plugin.fixData.blockCheck.remove(player.getUniqueId());
        plugin.fixData.lastBreakTime.remove(player.getUniqueId());
        plugin.fixData.lastBreakTimeSlow.remove(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        // Frees up some memory and unnecessary player checks if the player is no longer
        // online (Optimization)
        plugin.fixData.blockCheck.remove(player.getUniqueId());
        plugin.fixData.lastBreakTime.remove(player.getUniqueId());
        plugin.fixData.lastBreakTimeSlow.remove(player.getUniqueId());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!plugin.fixData.blockCheck.contains(player.getUniqueId()) && plugin.fixData.lastBreakTime.containsKey(player.getUniqueId())) {
            if (System.currentTimeMillis() - plugin.fixData.lastBreakTime.get(player.getUniqueId()) <= plugin.fixData.COOLDOWN_CHECKER) { // Checks to see if player is breaking blocks
                // faster then the set COOLDOWN Check limit. If
                // this is the case, the plugin categorizes the
                // player as a player who is breaking blocks
                // very fast
                plugin.fixData.blockCheck.add(player.getUniqueId()); // mark player for block updates
            }
        }
        plugin.fixData.lastBreakTime.put(player.getUniqueId(), System.currentTimeMillis()); // Temporarily stores the player with the time they last broke a block
    }

    @EventHandler
    public void onBlockUpdate(BlockUpdateEvent e) {
        new BukkitRunnable() {
            public void run() {
                for (UUID id : plugin.fixData.blockCheck) { // Gets a list of players (based off of their UUID) to check
                    if (System.currentTimeMillis() - plugin.fixData.lastBreakTime.get(id) >= plugin.fixData.COOLDOWN_CHECKER) { // checks to see the last time the player broke a block
                        if (!plugin.fixData.lastBreakTimeSlow.containsKey(id)) {
                            plugin.fixData.lastBreakTimeSlow.put(id, System.currentTimeMillis()); // marks player for further review later if they continue to break blocks slowly
                        } else if (System.currentTimeMillis() - plugin.fixData.lastBreakTimeSlow.get(id) >= plugin.fixData.COOLDOWN_CHECKER_REMOVAL) {
                            plugin.fixData.lastBreakTimeSlow.remove(id); // free up some memory
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    plugin.fixData.blockCheck.remove(id);
                                }
                            }.runTask(plugin);
                        }
                    } else if (plugin.fixData.lastBreakTimeSlow.containsKey(id)) { // Checks to see if player was marked for further review to see if they continue
                        // to break blocks slowly
                        plugin.fixData.lastBreakTimeSlow.remove(id); // Removes player from further review since they have once again began breaking
                        // blocks faster then the COOLDOWN Check limit.
                    }
                }
            }
        }.runTaskAsynchronously(plugin);
    }

}
