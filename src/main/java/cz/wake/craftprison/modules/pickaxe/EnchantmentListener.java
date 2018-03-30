package cz.wake.craftprison.modules.pickaxe;

import cz.wake.craftcore.utils.effects.ParticleEffect;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class EnchantmentListener implements Listener {

    HashMap<Player, Long> cooldown = new HashMap<>();

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

    @EventHandler
    public void onLaser(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (!e.getPlayer().getLocation().getWorld().getName().equals("doly")) {
            return;
        }
        if (!CustomPickaxe.isPickaxe(e.getMaterial())) {
            return;
        }
        CustomPickaxe cp = new CustomPickaxe(e.getItem());
        if (!cp.hasEnchantment(CustomEnchantment.LASER)) {
            return;
        }

        Player p = e.getPlayer();
        if(this.cooldown.containsKey(p)){
            if(this.cooldown.get(p) > System.currentTimeMillis()){
                p.sendMessage("§cJeste chvili pockej");
                return;
            }
            this.cooldown.remove(p);
        }
            //TODO Cooldown
        int level = cp.getCustomEnchantLevel(CustomEnchantment.LASER);
        Block b = p.getTargetBlock(null, 15);

        if (b.getType() == Material.AIR) {
            return;
        }

        Location first = b.getLocation().clone().add(level, 0, level);
        Location second = b.getLocation().clone().add(-level, 0, -level);

        int minX = first.getBlockX() < second.getBlockX() ? first.getBlockX() : second.getBlockX();
        int maxX = first.getBlockX() > second.getBlockX() ? first.getBlockX() : second.getBlockX();
        int minZ = first.getBlockZ() < second.getBlockZ() ? first.getBlockZ() : second.getBlockZ();
        int maxZ = first.getBlockZ() > second.getBlockZ() ? first.getBlockZ() : second.getBlockZ();

        int blockCount = 0;

        World w = first.getWorld();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                Location loc = new Location(w, x, first.getBlockY(), z);
                if (loc.getBlock().getType() == Material.AIR) {
                    continue;
                }
                // Zabranime zniceni dolu
                BlockBreakEvent be = new BlockBreakEvent(loc.getBlock(), p);
                Bukkit.getPluginManager().callEvent(be);

                if (be.isCancelled()) {
                    continue;
                }
                blockCount++;
                ParticleEffect.LAVA.display(1.0f, 1.0f, 1.0f, 0.1f, 10, loc, p);
                loc.getBlock().breakNaturally();
            }
        }
        if (blockCount > 0) {
            p.sendMessage("§aPouzil jsi laser, pockej 2 minuty do dalsiho pouziti");
            this.cooldown.put(p, System.currentTimeMillis() + 120000);
        }
    }
}
