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
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeUpgrade {

    public static ItemStack getDefaultPickaxe(String name) {
        ItemStack item = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§b§lDefault Pickaxe")
                .addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1)
                .addEnchant(Enchantment.DURABILITY, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getDebugPickaxe(String name) {
        ItemStack item = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§c§lDEBUG KRUMPAC").addLore("§0", "§7Vygeneroval §6" + name)
                .addEnchant(Enchantment.DIG_SPEED, 10).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10)
                .addEnchant(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    private boolean isSoulbound(Player player, ItemStack pickaxe) {
        if (!pickaxe.getItemMeta().hasLore()) return false;
        for (String line : pickaxe.getItemMeta().getLore()) {
            if (line.contains(player.getName())) return true;
        }
        return false;
    }
}
