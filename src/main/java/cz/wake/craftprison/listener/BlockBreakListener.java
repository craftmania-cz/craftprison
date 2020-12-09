package cz.wake.craftprison.listener;

import at.pcgamingfreaks.Minepacks.Bukkit.API.Backpack;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.events.InventoryFullEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(final BlockBreakEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        if (player.getInventory().firstEmpty() >= 0) return;

        List<ItemStack> blockDrops = new ArrayList<>(event.getBlock().getDrops());
        if (blockDrops.isEmpty()) return;

        List<ItemStack> items = new ArrayList<>();
        blockDrops.forEach(drop -> {
            for (ItemStack item : player.getInventory().getContents()) {
                if(item != null && item.getType().equals(drop.getType()) && item.getAmount() + drop.getAmount() <= item.getMaxStackSize()) return;
            }
            if (Main.getInstance().getPlayerBackpackInventory(player).addItem(drop) == null) { //TODO: Vyžaduje hard test!!!!
                return;
            }
            items.add(drop);
        });

        if (items.isEmpty()) return;

        final InventoryFullEvent inventoryFullEvent = new InventoryFullEvent(player);
        Bukkit.getPluginManager().callEvent(inventoryFullEvent);

    }
}
