package cz.wake.craftprison.modules.pickaxe;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class PickaxeUpgrade implements Listener {

    Player p;
    ItemStack pickaxe;

    public PickaxeUpgrade(Player p, ItemStack pickaxe) {
        this.p = p;
        this.pickaxe = pickaxe;

        p.getInventory().remove(pickaxe);

        this.openMenu();
    }

    private void openMenu() {
        Inventory inv = Bukkit.createInventory(null, 54, "Pickaxe upgrade");

        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 5).setName("§0").hideAllFlags().build();
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(12, glass);

        inv.setItem(13, pickaxe);

        inv.setItem(14, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);

        ItemStack anvil = new ItemBuilder(Material.ANVIL).setName("§a§lPrejmenovat").setLore("", "§7Klikni pro prejmenovani").hideAllFlags().build();
        inv.setItem(16, anvil);

        ItemStack barrier = new ItemBuilder(Material.BARRIER).setName("§0").hideAllFlags().build();
        for (int i = 27; i <= 44; i++) {
            inv.setItem(i, barrier);
        }

        CustomPickaxe cp = new CustomPickaxe(pickaxe);

        int slot = 26;
        for (CustomEnchantment ce : CustomEnchantment.values()) {
            slot++;
            if (isSoulbound() && ce.isSoulbound()) {
                inv.setItem(slot, barrier);
                continue;
            }
            int price = ce.getPrice() * (ce.getEffect() instanceof Enchantment ?
                    pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cp.getCustomEnchantLevel(ce.getName()));
            int level = ce.getEffect() instanceof Enchantment ? pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cp.getCustomEnchantLevel(ce.getName());
            inv.setItem(slot, new ItemBuilder(ce.getMaterial()).setName(ce.getColoredName()).setLore(ce.getLore(), "",
                    "§7Aktualni level: §a" + level,
                    "§7Maximalni level: §c" + ce.getMaxLevel(),
                    level >= ce.getMaxLevel() ? "§cDosahl jsi maximalniho levelu" :
                            "§7Cena upgradu: §e" + (price == 0 ? ce.getPrice() : price)).build());
        }

        ItemStack sign = new ItemBuilder(Material.SIGN).setName("§b§lPocet PrisCoinu: §7" + new PrisonManager().getCraftPlayer(p).getPrisCoins()).hideAllFlags().build();
        inv.setItem(49, sign);

        p.openInventory(inv);
    }

    public static ItemStack getFirstPickaxe(String name) {
        return new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§b§lKrumpac").addLore("§0", "§7Patri hraci §6" + name)
                .addEnchant(Enchantment.DIG_SPEED, 5).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2)
                .addEnchant(Enchantment.DURABILITY, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
    }

    private boolean isSoulbound() {
        if (!pickaxe.getItemMeta().hasLore()) return false;
        for (String line : pickaxe.getItemMeta().getLore()) {
            if (line.contains(p.getName())) return true;
        }
        return false;
    }
}
