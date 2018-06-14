package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.events.InventoryFullEvent;
import me.clip.autosell.events.DropsToInventoryEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Iterator;
import java.util.List;

public class AutoSellListener implements Listener {

    private Main plugin;

    public AutoSellListener(final Main i) {
        this.plugin = i;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockToInv(final DropsToInventoryEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final Player p = e.getPlayer();
        if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        if (!p.hasPermission("craftprison.fullinventory")) {
            return;
        }
        final List<ItemStack> drops = e.getDrops();
        if (drops == null || drops.isEmpty()) {
            return;
        }
        final PlayerInventory i = p.getInventory();
        if (!this.plugin.getTools().contains(i.getItemInMainHand().getType())) {
            return;
        }
        ItemStack wont = null;
        final Iterator<ItemStack> iterator = drops.iterator();
        if (iterator.hasNext()) {
            final ItemStack drop = iterator.next();
            if (this.plugin.getIgnored().contains(drop.getType())) {
                return;
            }
            ItemStack[] arrayOfItemStack;
            for (int j = (arrayOfItemStack = i.getStorageContents()).length, k = 0; k < j; ++k) {
                final ItemStack is = arrayOfItemStack[k];
                if (is == null) {
                    return;
                }
                if (is.getType().equals(drop.getType()) && is.getAmount() + drop.getAmount() <= is.getMaxStackSize()) {
                    return;
                }
            }
            wont = drop;
        }
        if (wont == null) {
            return;
        }
        if (this.plugin.getBackpackHook() != null && !this.plugin.getBackpackHook().wontFit(p, wont)) {
            return;
        }
        final String name = p.getName();
        if (this.plugin.isAlerted(name)) {
            final int current = this.plugin.getAlertAmount(name);
            if (current >= 5) {
                return;
            }
            this.plugin.setAlertAmount(name, current + 1);
        } else {
            this.plugin.setAlertAmount(name, 1);
        }
        this.plugin.decreaseAlertAmount(name);
        final InventoryFullEvent event = new InventoryFullEvent(p, wont);
        Bukkit.getPluginManager().callEvent(event);
    }
}
