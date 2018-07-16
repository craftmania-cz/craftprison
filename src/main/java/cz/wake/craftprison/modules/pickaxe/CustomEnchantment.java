package cz.wake.craftprison.modules.pickaxe;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

public enum CustomEnchantment {

    EFFICIENCY("Efficiency", "§eEfficiency", Material.FEATHER, Enchantment.DIG_SPEED, false, 2, 20, "§7Kopej jako blesk", "§7protoze cim rychlejsi", "§7kopani, tim lepsi Prison."),
    UNBREAKING("Unbreaking", "§cUnbreaking", Material.BEDROCK, Enchantment.DURABILITY, true, 1, 3, "§7Neznicitelny krumpac", "§7je zaklad kazdeho kopani."),
    HASTE("Haste", "§aHaste", Material.BEACON, PotionEffectType.FAST_DIGGING, false, 2, 2, "§7Rychlejsi nez blesk", "§7tak to budes ty."),
    FORTUNE("Fortune", "§dFortune", Material.DIAMOND, Enchantment.LOOT_BONUS_BLOCKS, false, 3, 30, "§7S kazdou urovni dostanes", "§7vic a vic itemu", "§7z vykopanych bloku."),
    JUMP("Jump", "§fJump", Material.DIAMOND_BOOTS, PotionEffectType.JUMP, false, 4, 2, "§7Skakej jako kralik.", "§7Hlavne nepreskoc doly."),
    SOULBOUND("Soulbound", "§7Soulbound", Material.SOUL_SAND, null, true, 3, 1, "§7Podepise krumpac tvym jmenem,", "§7a kazdy pak zjisti, jak jsi super."),
    SPEED("Speed", "§bSpeed", Material.POTION, PotionEffectType.SPEED, false, 5, 2, "§7Rychle a zbesile", "§7na Prisonu."),
    NIGHT("Night Vision", "§8Night Vision", Material.GLOWSTONE_DUST, PotionEffectType.NIGHT_VISION, false, 5, 1, "§7Nevidis v noci?", "§7Co si rozsvitit?"),
    //LASER("Laser", "§cLaser", Material.GLASS, null, false, "§7...", 10, 3),
    EXPLOSIVE("Explosive", "§4Explosive", Material.TNT, null, false, 50, 1, "§7Efekt, se kterym ti", "§7bude krumpac explodovat.", "", "§cPouze kosmeticky efekt!");

    private String name;
    private String coloredName;
    private Material material;
    private String[] lore;
    private Object effect;
    private boolean soulbound;
    private int price;
    private int maxLevel;

    CustomEnchantment(String name, String coloredName, Material material, Object effect, boolean soulbound, int price, int maxLevel, String... lore) {
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

    public String[] getLore() {
        return lore;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
