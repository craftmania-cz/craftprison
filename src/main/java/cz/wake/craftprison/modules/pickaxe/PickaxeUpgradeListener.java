package cz.wake.craftprison.modules.pickaxe;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

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
        for (CustomEnchantment ce : CustomEnchantment.values()) {
            if (item.getItemMeta().getDisplayName().equals(ce.getColoredName())) {
                //TODO Kontrola penez
                //p.sendMessage(ce.getName());
                if (ce.getEnchantment() != null) {
                    e.getInventory().setItem(13, new ItemBuilder(pickaxe).addEnchant(ce.getEnchantment(), pickaxe.getEnchantmentLevel(ce.getEnchantment()) + 1).build());

                } else {
                    List<String> lore = pickaxe.getItemMeta().getLore();
                    for (int i = 0; i < lore.size(); i++) {
                        if (lore.get(i).contains(ce.getName())) {
                            String item_level = lore.get(i).replace("§7" + ce.getName(), "").trim();
                            int l = Utils.convertRomanToInt(item_level);
                            String finalName = "§7" + ce.getName() + " " + Utils.convertIntToRoman(l + 1);
                            p.sendMessage("§7" + ce.getName() + " " + Utils.convertIntToRoman(l + 1));
                            e.getInventory().setItem(13, new ItemBuilder(pickaxe).removeLoreLine(i).addLoreLine(finalName, i).build());
                        }
                    }
                }
                p.updateInventory();
                p.closeInventory();
                p.sendMessage("§aUpgradoval jsi svuj krumpac");
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
