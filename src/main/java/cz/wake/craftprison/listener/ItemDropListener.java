package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ItemDropListener implements Listener {

    private static HashMap<Player, ItemStack> pickaxeDrop_request = new HashMap<>();
    private HashMap<Player, Double> _time = new HashMap<>();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDropItem(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if(Main.getInstance().getConfig().getStringList("protect-drops").contains(item.getType().toString())) {
            if(!pickaxeDrop_request.containsValue(item)) {
                e.setCancelled(true);
                pickaxeDrop_request.put(p, item);
                p.sendMessage("§bPokud chces opravdu vyhodit tento item, stiskni znova Q!");
                this._time.put(p, 5D + 0.1D); //5s cooldown
                this._cdRunnable.put(p, new BukkitRunnable() {
                    @Override
                    public void run() {
                        _time.put(p, ItemDropListener.this._time.get(p) - 0.1D);
                        if (_time.get(p) < 0.01D) {
                            _time.remove(p);
                            _cdRunnable.remove(p);
                            pickaxeDrop_request.remove(p);
                            cancel();
                        }
                    }
                });
                (_cdRunnable.get(p)).runTaskTimer(Main.getInstance(), 2L, 2L);
                return;
            }
            e.setCancelled(false);
            pickaxeDrop_request.remove(p);
            p.sendMessage("§c§l(!) §cVyhodil jsi chraneny item na zem!");
        }

    }
}