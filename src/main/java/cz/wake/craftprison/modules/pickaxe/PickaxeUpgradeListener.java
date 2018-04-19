package cz.wake.craftprison.modules.pickaxe;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.modules.PrisCoins;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.utils.AnvilContainer;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeUpgradeListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(final InventoryClickEvent e) {
        if (e.getInventory() == null) {
            return;
        }
        if (e.getInventory().getName() == null) {
            return;
        }
        if (!e.getInventory().getName().equals("Pickaxe upgrade")) {
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
            AnvilContainer.openAnvil(p, e.getInventory().getItem(13));
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
        if (e.getInventory() == null) {
            return;
        }
        if (e.getInventory().getName() == null) {
            return;
        }
        if (!e.getInventory().getName().equals("Pickaxe upgrade")) {
            return;
        }
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getPlayer();
        p.getInventory().addItem(e.getInventory().getItem(13));
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void closeAnvil(InventoryCloseEvent e) {
        HumanEntity p = e.getPlayer();
        Inventory inv = e.getInventory();
        if (inv instanceof AnvilInventory) {
            EntityPlayer entityPlayer = ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer) p).getHandle();
            if ((!entityPlayer.activeContainer.checkReachable)) {
                ItemStack pick = inv.getItem(0);

                pick = new ItemBuilder(pick).setName(ChatColor.translateAlternateColorCodes('&', pick.getItemMeta().getDisplayName())).build();
                p.getInventory().addItem(pick);
                inv.clear();
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent e) {
        HumanEntity entity = e.getWhoClicked();
        if ((entity instanceof Player)) {
            Player player = (Player) entity;
            Inventory inv = e.getInventory();
            if (inv instanceof AnvilInventory) {
                InventoryView inventoryView = e.getView();
                net.minecraft.server.v1_12_R1.EntityPlayer localEntityPlayer = ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer) player).getHandle();
                if ((!localEntityPlayer.activeContainer.checkReachable)) {
                    int i = e.getRawSlot();
                    if ((e.getClickedInventory() != null) &&
                            (e.getClickedInventory().equals(player.getInventory()))) {
                        return;
                    }
                    e.setCancelled(true);
                    if ((i == inventoryView.convertSlot(i)) && (i == 2)) {
                        ItemStack currentItem = e.getCurrentItem();
                        if (currentItem != null) {
                            ItemMeta itemMeta = currentItem.getItemMeta();
                            if ((itemMeta != null) && (itemMeta.hasDisplayName())) {
                                String name = ChatColor.translateAlternateColorCodes('&', itemMeta.getDisplayName());
                                player.sendMessage("§aUspesne jsi prejmenoval svuj krumpac");
                                ItemStack newPick = new ItemBuilder(currentItem).setName(name).build();
                                inv.setItem(2, newPick);
                                inv.setItem(0, newPick);
                                player.closeInventory();
                            }
                        }
                    }
                }
            }
        }
    }

}
