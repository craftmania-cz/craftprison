package cz.wake.craftprison.modules.pickaxe;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomPickaxe {

    private ItemStack pickaxe;

    public CustomPickaxe(ItemStack pickaxe) {
        this.pickaxe = pickaxe;
    }

    public ItemStack getPickaxe() {
        return pickaxe;
    }

    public static boolean isPickaxe(Material m) {
        return m.toString().endsWith("_PICKAXE");
    }

    public int getCustomEnchantLevel(String enchant) {
        if (!pickaxe.getItemMeta().hasLore()) return 0;
        for (String s : pickaxe.getItemMeta().getLore()) {
            if (s.contains(enchant)) {
                String level = s.replace("§7" + enchant, "").trim();
                return Utils.convertRomanToInt(level);
            }
        }
        return 0;
    }

    public int getCustomEnchantLevel(CustomEnchantment enchant) {
        for (String s : pickaxe.getItemMeta().getLore()) {
            if (s.contains(enchant.getName())) {
                String level = s.replace("§7" + enchant.getName(), "").trim();
                return Utils.convertRomanToInt(level);
            }
        }
        return 0;
    }

    public boolean hasEnchantment(CustomEnchantment ce) {
        if (!pickaxe.getItemMeta().hasLore()) return false;
        for (String lore : pickaxe.getItemMeta().getLore()) {
            if (lore.contains(ce.getName())) return true;
        }
        return false;
    }

    public ArrayList<CustomEnchantment> getAllEnchantments() {
        ArrayList<CustomEnchantment> enchantments = new ArrayList<>();
        for (CustomEnchantment ce : CustomEnchantment.values()) {
            if (hasEnchantment(ce)) enchantments.add(ce);
        }
        return enchantments;
    }

    public void addEnchantment(CustomEnchantment ce, int level) throws IllegalArgumentException {
        if (level > ce.getMaxLevel()) throw new IllegalArgumentException("§cVyssi level uz nejde!");

        if (this.hasEnchantment(ce)) {
            this.removeEnchantment(ce);
        }

        HashMap<CustomEnchantment, Integer> enchantments = new HashMap<>();

        for (CustomEnchantment cenchant : getAllEnchantments()) {
            enchantments.put(cenchant, getCustomEnchantLevel(cenchant));
            removeEnchantment(cenchant);
        }

        List<String> newLore = new ArrayList<>();
        List<String> oldLore = new ArrayList<>();
        if (pickaxe.getItemMeta().hasLore()) {
            oldLore = pickaxe.getItemMeta().getLore();
        }

        for (CustomEnchantment cenchant : enchantments.keySet()) {
            newLore.add("§7" + cenchant.getName() + " " + Utils.convertIntToRoman(enchantments.get(cenchant)));
        }
        newLore.add("§7" + ce.getName() + " " + Utils.convertIntToRoman(level));
        newLore.addAll(oldLore);

        this.pickaxe = new ItemBuilder(pickaxe).setLore(newLore).build();
    }

    public void removeEnchantment(CustomEnchantment ce) {
        if (!this.pickaxe.getItemMeta().hasLore()) return;
        List<String> newLore = new ArrayList<>();

        for (String lore : this.pickaxe.getItemMeta().getLore()) {
            if (!lore.contains(ce.getName())) {
                newLore.add(lore);
            }
        }

        this.pickaxe = new ItemBuilder(pickaxe).setLore(newLore).build();
    }

    public void removeAllEnchantments() {
        for (CustomEnchantment ce : this.getAllEnchantments()) {
            this.removeEnchantment(ce);
        }
    }

    public void setSoulbound(String name) {
        ItemBuilder builder = new ItemBuilder(this.pickaxe);
        for (String s : builder.getLore()) {
            if (s.contains("Patri hraci")) {
                builder.removeLoreLine(s);
            }
        }
        this.pickaxe = new ItemBuilder(pickaxe).addLoreLine("§0").addLoreLine("§7Patri hraci §b" + name).addEnchant(Enchantment.DURABILITY, 3).build();
    }
}
