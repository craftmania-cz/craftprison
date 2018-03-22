package cz.wake.craftprison.modules.pickaxe;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentListener implements Listener {

    //Jump, Speed, Haste
    @EventHandler
    public void onHold(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        ItemStack newSlot = p.getInventory().getItem(e.getNewSlot());
        ItemStack oldSlot = p.getInventory().getItem(e.getPreviousSlot());

        if (oldSlot != null && oldSlot.getType() != Material.AIR) {
            if (CustomPickaxe.isPickaxe(oldSlot.getType())) {
                CustomPickaxe cp = new CustomPickaxe(oldSlot);
                for (CustomEnchantment ce : cp.getAllEnchantments()) {
                    if (ce.getEffect() instanceof PotionEffectType) {
                        p.removePotionEffect((PotionEffectType) ce.getEffect());
                    }
                }
            }
        }

        if (newSlot != null && newSlot.getType() != Material.AIR) {
            if (CustomPickaxe.isPickaxe(newSlot.getType())) {
                CustomPickaxe cp = new CustomPickaxe(newSlot);
                for (CustomEnchantment ce : cp.getAllEnchantments()) {
                    if (ce.getEffect() instanceof PotionEffectType) {
                        PotionEffect potionEffect = new PotionEffect((PotionEffectType) ce.getEffect(), Integer.MAX_VALUE, cp.getCustomEnchantLevel(ce) - 1);
                        p.addPotionEffect(potionEffect);
                    }
                }
            }
        }
    }
}
