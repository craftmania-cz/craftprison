package cz.wake.craftprison.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MiningListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();

        if (p.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (e.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
            ItemStack item = e.getPlayer().getItemInHand();
            item.setDurability((short) 0);
        }
    }
}
