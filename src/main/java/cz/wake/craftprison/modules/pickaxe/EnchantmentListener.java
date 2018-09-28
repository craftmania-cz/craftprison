package cz.wake.craftprison.modules.pickaxe;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import cz.craftmania.craftcore.spigot.utils.effects.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

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

    @EventHandler
    public void onExplosiveBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack pickaxe = p.getInventory().getItemInMainHand();

        if (!p.getLocation().getWorld().getName().equals("doly")) {
            return;
        }

        if (!CustomPickaxe.isPickaxe(pickaxe.getType())) {
            return;
        }

        CustomPickaxe cp = new CustomPickaxe(pickaxe);
        if (!cp.hasEnchantment(CustomEnchantment.EXPLOSIVE)) {
            return;
        }

        if (p.isOp()) {
            return;
        }

        if (p.hasPermission("craftprison.pickaxe.disable-effects")) {
            return;
        }

        if (!getChance(20)) {
            return;
        }

        Location first = e.getBlock().getLocation().clone().add(1, 1, 1);
        Location second = e.getBlock().getLocation().clone().add(-1, -1, -1);

        int minX = first.getBlockX() < second.getBlockX() ? first.getBlockX() : second.getBlockX();
        int maxX = first.getBlockX() > second.getBlockX() ? first.getBlockX() : second.getBlockX();
        int minY = first.getBlockY() < second.getBlockY() ? first.getBlockY() : second.getBlockY();
        int maxY = first.getBlockY() > second.getBlockY() ? first.getBlockY() : second.getBlockY();
        int minZ = first.getBlockZ() < second.getBlockZ() ? first.getBlockZ() : second.getBlockZ();
        int maxZ = first.getBlockZ() > second.getBlockZ() ? first.getBlockZ() : second.getBlockZ();

        World w = first.getWorld();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Location loc = new Location(w, x, y, z);
                    if (!canBreak(p, loc)) continue;
                    if (loc.getBlock().getType() == Material.BEDROCK
                            || loc.getBlock().getType() == Material.BARRIER) {
                        return;
                    }

                    if (!getChance(30)) continue;

                    //TODO Presunuti itemu do inventara + kontrola plneho inventare

                    loc.getBlock().setType(Material.AIR);
                    loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 0.3F, 0.3F);
                    ParticleEffect.EXPLOSION_NORMAL.display(1.0f, 1.0f, 1.0f, 0.1f, 2, loc, p);
                }
            }
        }
    }

    private boolean canBreak(Player p, Location loc) {
        WorldGuardPlugin wg = WGBukkit.getPlugin();
        return wg.canBuild(p, loc);
    }

    private boolean getChance(int percent) {
        return percent >= new Random().nextInt(100);
    }

}
