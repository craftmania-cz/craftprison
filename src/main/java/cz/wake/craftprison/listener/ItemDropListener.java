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
