package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        final List<String> appliedItems = Main.getInstance().getConfig().getStringList("protect-drop");
        final HashMap<Integer, ItemStack> itemsToGive = new HashMap<>();
        for (int slot = 0; slot < 36; ++slot) {
            final ItemStack item = p.getInventory().getItem(slot);
            if (item != null) {
                if(appliedItems.contains(item.getType().toString())){
                    if(isSoulbound(item, p)){
                        itemsToGive.put(slot, item);
                    }
                }
            }
        }
        new BukkitRunnable() {
            public void run() {
                for (final Map.Entry<Integer, ItemStack> itemEntry : itemsToGive.entrySet()) {
                    p.getInventory().setItem(itemEntry.getKey(), itemEntry.getValue());
                }
                p.updateInventory();
            }
        }.runTaskLater(Main.getInstance(), 0L);
    }

    private boolean isSoulbound(ItemStack pickaxe, Player p) {
        if (!pickaxe.getItemMeta().hasLore()) return false;
        for (String line : pickaxe.getItemMeta().getLore()) {
            if (line.contains(p.getName())) return true;
        }
        return false;
    }
}
