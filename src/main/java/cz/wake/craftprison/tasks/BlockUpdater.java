package cz.wake.craftprison.tasks;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.events.BlockUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BlockUpdater implements Runnable {

    // An empty set used to temporarily store blockCheck as a clone for reading it
    // safely in an async thread
    private Set<UUID> blockCheckCopy = new HashSet<>();

    @Override
    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                blockCheckCopy = (HashSet<UUID>) Main.getInstance().getFixData().blockCheck.clone(); // clone the blockCheck HashSet for reading purposes
            }
        }.runTask(Main.getInstance());
        for (UUID id : blockCheckCopy) {
            BlockUpdateEvent event = new BlockUpdateEvent(Bukkit.getPlayer(id), BlockUpdateEvent.BlockUpdateReason.FAST_BREAKING /* Simply the reason for the listener call. In this case
             * this listener is being called because someone is breaking
             * blocks fast, hence the name FAST_BREAKING */);
            /* Creates an instance of a custom listener class and is ready to call. */

            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getPluginManager().callEvent(event);
                    Player player = Bukkit.getPlayer(id);
                    if (!event.isCancelled() && player != null) { // makes sure the called event hasn't been cancelled
                        updateBlocks(Bukkit.getPlayer(id), Main.getInstance().getFixData().RADIUS); // updates blocks
                    }
                }

            }.runTask(Main.getInstance());
        }
    }

    /** Updates the players with blocks that surround them using packets.
     *
     * @param player
     *            Online player
     * @param radius
     *            Update radius */

    public void updateBlocks (Player player,int radius){

        new BukkitRunnable() {
            public void run() {
                Location source = player.getLocation().getBlock().getLocation();
                Location center = new Location(source.getWorld(), source.getX() - radius, source.getY() - radius, source.getZ() - radius);
                Location temp = center.clone();
                new BukkitRunnable() {
                    @SuppressWarnings("deprecation")
                    public void run() {

                        for (int x = (int) center.getX(); x <= center.getX() + (radius * 2); x++) {
                            for (int y = (int) center.getY(); y <= center.getY() + (radius * 2); y++) {
                                for (int z = (int) center.getZ(); z <= center.getZ() + (radius * 2); z++) {
                                    temp.setX(x);
                                    temp.setY(y);
                                    temp.setZ(z);
                                    Block block = temp.getBlock();
                                    player.sendBlockChange(block.getLocation(), block.getType(), block.getData());
                                }
                            }
                        }
                    }
                }.runTaskAsynchronously(Main.getInstance());

            }
        }.runTask(Main.getInstance());
    }
}
