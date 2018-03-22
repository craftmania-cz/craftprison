package cz.wake.craftprison.modules.pickaxe;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

public enum CustomEnchantment {

    EFFICIENCY("Efficiency", "§eEfficiency", Material.FEATHER, Enchantment.DIG_SPEED, false, "§7Kopej rychleji", 10, 100),
    UNBREAKING("Unbreaking", "§cUnbreaking", Material.BEDROCK, Enchantment.DURABILITY, true, "§7...", 10, 3),
    HASTE("Haste", "§aHaste", Material.BEACON, PotionEffectType.FAST_DIGGING, false, "§7...", 10, 2),
    FORTUNE("Fortune", "§dFortune", Material.DIAMOND, Enchantment.LOOT_BONUS_BLOCKS, false, "§7...", 10, 100),
    JUMP("Jump", "§fJump", Material.DIAMOND_BOOTS, PotionEffectType.JUMP, false, "§7...", 10, 2),
    SOULBOUND("Soulbound", "§7Soulbound", Material.SOUL_SAND, null, true, "§7Podepise tvuj krumpac", 10, 1),
    SPEED("Speed", "§bSpeed", Material.POTION, PotionEffectType.SPEED, false, "§7...", 10, 2),
    NIGHT("Night Vision", "§8Night Vision", Material.GLOWSTONE_DUST, PotionEffectType.NIGHT_VISION, false, "§7...", 10, 1),
    LASER("Laser", "§cLaser", Material.GLASS, null, false, "§7...", 10, 3);

    private String name;
    private String coloredName;
    private Material material;
    private String lore;
    private Object effect;
    private boolean soulbound;
    private int price;
    private int maxLevel;

    CustomEnchantment(String name, String coloredName, Material material, Object effect, boolean soulbound, String lore, int price, int maxLevel) {
        this.name = name;
        this.coloredName = coloredName;
        this.material = material;
        this.effect = effect;
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

    public Object getEffect() {
        return effect;
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
