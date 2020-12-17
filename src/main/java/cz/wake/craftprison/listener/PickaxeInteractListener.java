package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.UUID;

public class PickaxeInteractListener implements Listener {

    private PrisonManager pm = new PrisonManager();
    private static HashSet<UUID> cooldown = new HashSet<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) &&
                (item.getType() == Material.DIAMOND_PICKAXE || item.getType() == Material.NETHERITE_PICKAXE)
                && (p.getLocation().getWorld().getName().equals("spawn") || p.getLocation().getWorld().getName().equals("mines"))
                && (p.hasPermission("craftprison.pickaxe.rightclick.sell"))) {
            if(!cooldown.contains(p.getUniqueId())){
                p.performCommand("sellall " + pm.getPlayerRank(p).getName().toLowerCase());
                cooldown.add(p.getUniqueId());
                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), () -> cooldown.remove(p.getUniqueId()), 20);
            }
        }
    }

    private boolean isSoulbound(ItemStack pickaxe, Player p) {
        if (!pickaxe.getItemMeta().hasLore()) return false;
        for (String line : pickaxe.getItemMeta().getLore()) {
            if (line.contains(p.getName())) return true;
        }
        return false;
    }

}
