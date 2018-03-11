package cz.wake.craftprison.modules.pickaxe;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public enum CustomEnchantment {

    EFFICIENCY("Efficiency", "§eEfficiency", Material.FEATHER, Enchantment.DIG_SPEED, false, "§7Kopej rychleji", 10, 100),
    UNBREAKING("Unbreaking", "§cUnbreaking", Material.BEDROCK, Enchantment.DURABILITY, true, "§7...", 10, 3),
    HASTE("Haste", "§aHaste", Material.BEACON, null, false, "§7...", 10, 100),
    FORTUNE("Fortune", "§dFortune", Material.DIAMOND, Enchantment.LOOT_BONUS_BLOCKS, false, "§7...", 10, 100),
    JUMP("Jump", "§fJump", Material.POTION, null, false, "§7...", 10, 2),
    SOULBOUND("Soulbound", "§7Soulbound", Material.SOUL_SAND, null, true, "§7Podepise tvuj krumpac", 10, 1),
    LASER("Laser", "§cLaser", Material.GLASS, null, false, "§7...", 10, 3);

    private String name;
    private String coloredName;
    private Material material;
    private String lore;
    private Enchantment enchantment;
    private boolean soulbound;
    private int price;
    private int maxLevel;

    CustomEnchantment(String name, String coloredName, Material material, Enchantment enchantment, boolean soulbound, String lore, int price, int maxLevel) {
        this.name = name;
        this.coloredName = coloredName;
        this.material = material;
        this.enchantment = enchantment;
        this.soulbound = soulbound;
        this.lore = lore;
        this.price = price;
        this.maxLevel = maxLevel;
    }

    public String getName() {
        return name;
    }

    public String getColoredName() {
        return coloredName;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isSoulbound() {
        return soulbound;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public String getLore() {
        return lore;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
