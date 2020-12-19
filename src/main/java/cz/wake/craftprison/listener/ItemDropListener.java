package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemDropListener implements Listener {

    /*private static HashMap<Player, ItemStack> pickaxeDrop_request = new HashMap<>();
    private HashMap<Player, Double> _time = new HashMap<>();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDropItem(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getItemDrop().getItemStack();
        if (Main.getInstance().getConfig().getStringList("protect-drop").contains(item.getType().toString())) {
            if (!pickaxeDrop_request.containsValue(item)) {
                e.setCancelled(true);
                pickaxeDrop_request.put(p, item);
                p.sendMessage("§bPokud chceš znova vyhodit item na zem, stiskni znova Q!");
                this._time.put(p, 5D + 0.1D); //5s cooldown
                this._cdRunnable.put(p, new BukkitRunnable() {
                    @Override
                    public void run() {
                        ItemDropListener.this._time.put(p, ItemDropListener.this._time.get(p) - 0.1D);
                        try {
                            if (ItemDropListener.this._time.get(p) < 0.01D) {
                                ItemDropListener.this._time.remove(p);
                                ItemDropListener.this._cdRunnable.remove(p);
                                pickaxeDrop_request.remove(p);
                                cancel();
                            }
                        } catch (NullPointerException e) {
                            //e.printStackTrace(); - ignore
                        }
                    }
                });
                (ItemDropListener.this._cdRunnable.get(p)).runTaskTimer(Main.getInstance(), 2L, 2L);
                return;
            }
            e.setCancelled(false);
            pickaxeDrop_request.remove(p);
            p.sendMessage("§c§l[!] §cVyhodil jsi chráněný item na zem!");
        }
    }*/

    private HashMap<Player, Pair<ItemStack, Long>> dropRequests = new HashMap<>();

    @EventHandler
    public void onDropItem(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItemDrop().getItemStack();

        if (Main.getInstance().getConfig().getStringList("protect-drop").contains(itemStack.getType().toString())) {
            if (!dropRequests.containsKey(player) || dropRequests.get(player).getValue() < System.currentTimeMillis()) {
                // New request
                event.setCancelled(true);

                player.sendMessage("§bPokud chceš vyhodit item na zem, stiskni znova Q!");

                dropRequests.put(player, Pair.of(itemStack, System.currentTimeMillis() + 5000)); // 5 seconds
            } else {
                // Drop
                event.setCancelled(false);

                player.sendMessage("§c§l[!] §cVyhodil jsi chráněný item na zem!");

                dropRequests.remove(player);
            }
        }
    }
}
