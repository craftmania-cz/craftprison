package cz.wake.craftprison.modules.pickaxe;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class PickaxeUpgrade {

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

        ItemStack glass = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, (byte) 5).setName("§0").hideAllFlags().build();
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(12, glass);

        inv.setItem(13, pickaxe);

        inv.setItem(14, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);

        ItemStack anvil = new ItemBuilder(Material.ANVIL).setName("§a§lPrejmenovat").setLore("", "§7Pro prejmenovani zadej", "§7prikaz /pickaxe rename.").hideAllFlags().build();
        inv.setItem(16, anvil);

        ItemStack barrier = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDurability((short)14).setName("§0").hideAllFlags().build();
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
            price = (price == 0 ? ce.getPrice() : price);

            int level = ce.getEffect() instanceof Enchantment ? pickaxe.getEnchantmentLevel((Enchantment) ce.getEffect()) : cp.getCustomEnchantLevel(ce.getName());
            inv.setItem(slot, new ItemBuilder(ce.getMaterial()).setName(ce.getColoredName()).setLore(ce.getLore()).addLoreLine("").addLoreLine("§7Aktualni level: §a" + level)
                    .addLoreLine("§7Maximalni level: §c" + ce.getMaxLevel()).addLoreLine(level >= ce.getMaxLevel() ? "§cDosahl jsi maximalniho levelu" :
                                    "§7Cena upgradu: §e" + price).hideAllFlags().build());
        }

        ItemStack sign = new ItemBuilder(Material.OAK_SIGN).setName("§b§lPocet PrisCoinu: §70").hideAllFlags().build();
        inv.setItem(49, sign);

        p.openInventory(inv);
    }

    public static ItemStack getDefaultPickaxe(String name) {
        return new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§b§lKrumpac").addLore("§0", "§7Patri hraci §6" + name)
                .addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2)
                .addEnchant(Enchantment.DURABILITY, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
    }

    public static ItemStack getDebugPickaxe(String name) {
        return new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§c§lDEBUG KRUMPAC").addLore("§0", "§7Patri hraci §6" + name)
                .addEnchant(Enchantment.DIG_SPEED, 10).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10)
                .addEnchant(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
    }

    private boolean isSoulbound() {
        if (!pickaxe.getItemMeta().hasLore()) return false;
        for (String line : pickaxe.getItemMeta().getLore()) {
            if (line.contains(p.getName())) return true;
        }
        return false;
    }
}
