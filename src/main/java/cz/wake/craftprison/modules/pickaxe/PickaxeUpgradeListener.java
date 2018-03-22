package cz.wake.craftprison.modules.pickaxe;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.modules.PrisCoins;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Logger;

public class PickaxeUpgradeListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(final InventoryClickEvent e) {
        if (e.getInventory() == null) {
            return;
        }
        if (e.getInventory().getName() == null) {
            return;
        }
        if (!e.getInventory().getName().equals("§3§lPickaxe upgrade")) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (!e.getCurrentItem().hasItemMeta()) {
            return;
        }
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        ItemStack item = e.getCurrentItem();
        if (e.getSlot() == 16) {
            p.sendMessage("§cAnvil click");
            return;
        }
        ItemStack pickaxe = e.getInventory().getItem(13);
        CustomPickaxe cpick = new CustomPickaxe(pickaxe);

        for (CustomEnchantment ce : CustomEnchantment.values()) {
            if (item.getItemMeta().getDisplayName().equals(ce.getColoredName())) {
                //TODO Kontrola penez
                PrisonManager pm = new PrisonManager();
                CraftPlayer cp = pm.getCraftPlayer(p);

                int level = (ce.getEffect() instanceof Enchantment ? pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cpick.getCustomEnchantLevel(ce.getName())) + 1;
                if (level > ce.getMaxLevel()) {
                    p.closeInventory();
                    p.sendMessage("§cVetsi level uz nejde");
                    return;
                }

                int price = ce.getPrice() * (ce.getEffect() instanceof Enchantment ?
                        pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cpick.getCustomEnchantLevel(ce.getName()));
                price = price == 0 ? ce.getPrice() : price;
                if (cp.getPrisCoins() < price) {
                    p.closeInventory();
                    p.sendMessage("§cNemas dostatek PrisCoinu §7(" + price + ")");
                    return;
                }

                if (ce.getEffect() instanceof Enchantment) {
                    if (ce == CustomEnchantment.UNBREAKING) {
                        e.getInventory().setItem(13, new ItemBuilder(pickaxe).addEnchant(Enchantment.DURABILITY, 3).build());
                    } else {
                        e.getInventory().setItem(13, new ItemBuilder(pickaxe).addEnchant((Enchantment) ce.getEffect(), level).build());
                    }
                } else if (ce == CustomEnchantment.SOULBOUND) {
                    cpick.setSoulbound(p.getName());
                } else {
                    try {
                        cpick.addEnchantment(ce, level);
                    } catch (IllegalArgumentException ex) {
                        p.closeInventory();
                        p.sendMessage(ex.getMessage());
                        return;
                    }

                    e.getInventory().setItem(13, cpick.getPickaxe());
                }
                p.updateInventory();
                p.closeInventory();
                PrisCoins.takeCoins(p, price);
                p.sendMessage("§aUpgradoval jsi svuj krumpac za §c" + price + " §aPrisCoinu");
            }
        }

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory() == null) {
            return;
        }
        if (e.getInventory().getName() == null) {
            return;
        }
        if (!e.getInventory().getName().equals("§3§lPickaxe upgrade")) {
            return;
        }
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getPlayer();
        p.getInventory().addItem(e.getInventory().getItem(13));
    }
}
