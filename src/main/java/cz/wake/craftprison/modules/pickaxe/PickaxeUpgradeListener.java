package cz.wake.craftprison.modules.pickaxe;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.wake.craftprison.modules.PrisCoins;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class PickaxeUpgradeListener implements Listener {
    private static ItemStack pickaxe;
    private static HashSet<Player> editor = new HashSet<>();

    @EventHandler
    public void onInventoryClickEvent(final InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("Pickaxe upgrade")) {
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
        pickaxe = e.getInventory().getItem(13);
        e.setCancelled(true);

        ItemStack item = e.getCurrentItem();
        CustomPickaxe cpick = new CustomPickaxe(pickaxe);

        for (CustomEnchantment ce : CustomEnchantment.values()) {
            if (item.getItemMeta().getDisplayName().equals(ce.getColoredName())) {
                //TODO Kontrola penez
                PrisonManager pm = new PrisonManager();
                CraftPlayer cp = pm.getCraftPlayer(p);

                int level = (ce.getEffect() instanceof Enchantment ? pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cpick.getCustomEnchantLevel(ce.getName())) + 1;
                if (level > ce.getMaxLevel()) {
                    p.closeInventory();
                    p.sendMessage("§c§l(!) §cMaximalni uroven enchantu byla dosazena!");
                    return;
                }

                int price = ce.getPrice() * (ce.getEffect() instanceof Enchantment ?
                        pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cpick.getCustomEnchantLevel(ce.getName()));
                price = price == 0 ? ce.getPrice() : price;
                if (cp.getPrisCoins() < price) {
                    p.closeInventory();
                    p.sendMessage("§c§l(!) §cNemas dostatek PrisCoinu §7(" + price + ")");
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
        if (!e.getView().getTitle().equals("Pickaxe upgrade")) {
            return;
        }
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getPlayer();
        p.getInventory().addItem(e.getInventory().getItem(13));
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String m = e.getMessage();
        if (editor.contains(p)) {
            e.setCancelled(true);
            if (m.equalsIgnoreCase("exit") || m.equalsIgnoreCase("cancel") || m.equalsIgnoreCase("zrusit")) {
                editor.remove(p);
                p.sendMessage("§cZmena nazvu krumpace zrusena!");
                return;
            }
            if (!m.matches("[a-zA-Z0-9\\&]+")){
                p.sendMessage("§cNazev krumpace nesmi obsahovat specialni znaky!");
                return;
            }
            if (m.length() > 16) {
                p.sendMessage("§cMaximalne jde nastavit 16 znaku! Pokud chces zrusit editaci napis - exit, zrusit nebo cancel");
                return;
            }
            if (!p.hasPermission("craftprison.pickaxe.rename.color")){
                m = ChatColor.stripColor(m);
            }
            pickaxe.getItemMeta().setDisplayName(m);
            editor.remove(p);
            p.sendMessage("§eNazev krumpace nastaven na: §f" + m);
        }
    }
}
