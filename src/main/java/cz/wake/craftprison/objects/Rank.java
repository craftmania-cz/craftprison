package cz.wake.craftprison.objects;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Seznam ranků pro Prestiges
 * Tento seznam je stejný napříč všemi Prestiges
 */
public enum Rank {

    A(1, "A", 0, 0, Material.COBBLESTONE, new Location(Bukkit.getWorld("spawn"), -110.5, 42, 137.5, 180, 0)),
    B(2, "B", 10000, 1, Material.STONE, new Location(Bukkit.getWorld("spawn"), -41.5, 42, 206.5, -90, 0)), // craftprison.mine.nightvision
    C(3, "C", 25000, 1, Material.DIORITE, new Location(Bukkit.getWorld("spawn"), -110.5, 42, 275.5, 0, 0)),
    D(4, "D", 50000, 1, Material.POLISHED_DIORITE, new Location(Bukkit.getWorld("spawn"), -179.5, 42, 206.5, 90, 0)), // "Odemknuti vytvareni ostrova /is", "askyblock.island.create"
    E(5, "E", 100000, 2, Material.POLISHED_GRANITE, new Location(Bukkit.getWorld("mines"), 62.5, 108, -133.5, 0, 0)), // "Odemknuti pouzivani Aukce /au (Max 1 aukce)", "auctionguiplus.auction", "auctionguiplus.auction.bid", "auctionguiplus.auction.start", "auctionguiplus.auction.bid"
    F(6, "F", 200000, 2, Material.LIME_TERRACOTTA, new Location(Bukkit.getWorld("mines"), -161.5, 109, -49.5, -89, 0)), // "Odemknuti prikazu /pvp", "craftprison.pvp", "quicksell.shop.pvp"),
    G(7, "G", 400000, 2, Material.RED_TERRACOTTA, new Location(Bukkit.getWorld("mines"), -201.5, 118, 257.5, 0, 0)), // "Odemknuti /shop + sekce Blocks", "shopguiplus.shop", "shopguiplus.shops.blocks"),
    H(8, "H", 750000, 2, Material.BONE_BLOCK, new Location(Bukkit.getWorld("mines"), -103.1, 120, 619.8, 0, 0)), // "", ""),
    I(9, "I", 1000000, 3, Material.IRON_BLOCK, new Location(Bukkit.getWorld("mines"), 17.5, 133, 1086, 0, 0)), // "Odemknuti prikazu /heads", "headdb.allow.buy", "headdb.category.*", "headdb.open"),
    J(10, "J", 2000000, 3, Material.MOSSY_COBBLESTONE, new Location(Bukkit.getWorld("mines"), 82.5, 102, 1689.5, -91, 0)), // "", ""),
    K(11, "K", 4000000, 3, Material.LIGHT_BLUE_CONCRETE, new Location(Bukkit.getWorld("mines"), -763.5, 77, 9.5, -90, 0)), // "Moznost pridat 3 hrace na ostrov (+1)", "askyblock.team.maxsize.3"),
    L(12, "L", 10000000, 3, Material.GOLD_ORE, new Location(Bukkit.getWorld("mines"), -1034, 133, 158.5, -89, 0)),
    M(13, "M", 25000000, 3, Material.SANDSTONE, new Location(Bukkit.getWorld("mines"), -1442.1, 113, 292.0, -89, 0)), //, "Odemknuti sekce Decorations & Foods v /shop", "shopguiplus.shops.food", "shopguiplus.shops.dekorace"),
    N(14, "N", 60000000, 4, Material.GOLD_BLOCK, new Location(Bukkit.getWorld("mines"), -1890.5, 113, 481.5, -179, 0)), //, "", ""),
    O(15, "O", 120000000, 4, Material.DARK_PRISMARINE, new Location(Bukkit.getWorld("mines"), -2130.5, 114, 542.5, 0, 0)), // "Automaticky FLY v dolech", "craftprison.mine.fly"),
    P(16, "P", 250000000, 4, Material.REDSTONE_BLOCK, new Location(Bukkit.getWorld("mines"), -2558.5, 114, 694.5, -89, 0)), //, "", ""),
    Q(17, "Q", 500000000, 5, Material.EMERALD_ORE, new Location(Bukkit.getWorld("mines"), -2940.5, 104, 791.5, -179, 0)), //, 1, (short) 15), "Odemknuti vsech sekci v /shop", "shopguiplus.shops.*"),
    R(18, "R", 1000000000, 5, Material.PACKED_ICE, new Location(Bukkit.getWorld("mines"), -3457.5, 104, 838.5, 0, 0)), // "", ""),
    S(19, "S", 2000000000, 5, Material.END_STONE, new Location(Bukkit.getWorld("mines"), -3531.5, 125, 491.5, -89, 0)), //
    T(20, "T", 5000000000L, 6, Material.EMERALD_BLOCK, new Location(Bukkit.getWorld("mines"), -3967.5, 114, 566.5, 90, 0)), //
    U(21, "U", 10000000000L, 6, Material.QUARTZ_BLOCK, new Location(Bukkit.getWorld("mines"), -4511.5, 145, 756.5, 179, 0)), // Calcium Pools (Bundle 1)
    V(22, "V", 20000000000L, 6, Material.NETHER_BRICKS, new Location(Bukkit.getWorld("mines"), -4974.5, 104, 804.5, 0, 0)), //
    W(23, "W", 50000000000L, 7, Material.BASALT, new Location(Bukkit.getWorld("mines"), -5593.5, 111, 969.5, 90, 0)), //
    X(24, "X", 100000000000L, 8, Material.DIAMOND_ORE, new Location(Bukkit.getWorld("mines"), -6064.5, 134, 1054.5, -90, 0)), // StarWars (Bundle 1)
    Y(25, "Y", 200000000000L, 8, Material.LAPIS_BLOCK, new Location(Bukkit.getWorld("mines"), -6619.5, 129, 1118.5, 0, 0)), //
    Z(26, "Z", 400000000000L, 10, Material.DIAMOND_BLOCK, new Location(Bukkit.getWorld("mines"), -6879.5, 119, 1276.5, 0, 0)); //

    /* PANTHER(27, "Panther", 10000000000L, MineDifficulty.STANDARD, 6, new ItemStack(Material.QUARTZ, 1, (short)1), "", ""),
    DOCTOR(28, "Doctor", 20000000000L, MineDifficulty.STANDARD, 6, new ItemStack(Material.DIAMOND_BLOCK), "Moznost pridat 6 hrace na ostrov (+4)", "askyblock.team.maxsize.6"),
    KRATOS(29, "Kratos", 30000000000L, MineDifficulty.STANDARD, 6, new ItemStack(Material.MAGMA), "", ""),
    LINK(30, "Link", 75000000000L, MineDifficulty.HARD, 6, new ItemStack(Material.GREEN_GLAZED_TERRACOTTA), "", ""),
    KENNY(31, "Kenny", 200000000000L, MineDifficulty.HARD, 7, new ItemStack(Material.ENDER_STONE), "Prodej v dolech pravym kliknutim s krumpacem", "craftprison.pickaxe.rightclick.sell"),
    WITHER(32, "Wither", 400000000000L, MineDifficulty.HARD, 7, new ItemStack(Material.BEDROCK), "", ""),
    EGGMAN(33, "Eggman", 600000000000L, MineDifficulty.HARD, 7, new ItemStack(Material.BROWN_GLAZED_TERRACOTTA), "", ""),
    DOOMFIST(34, "Doomfist", 1200000000000L, MineDifficulty.HARD, 7, new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA), "", ""),
    ORC(35, "Orc", 2000000000000L, MineDifficulty.HARD, 7, new ItemStack(Material.DIAMOND_BLOCK), "", ""),
    STORMTROOPER(36, "Stormtrooper", 4500000000000L, MineDifficulty.HARD, 7, new ItemStack(Material.QUARTZ_BLOCK), "", ""),
    DWARF(37, "Dwarf", 8000000000000L, MineDifficulty.HARD, 8, new ItemStack(Material.BLUE_GLAZED_TERRACOTTA), "", ""),
    PRISMARINER(38, "Prismariner", 15000000000000L, MineDifficulty.HARD, 8, new ItemStack(Material.PRISMARINE, 1, (short)2), "", ""),
    MRPIG(39, "MrPig", 25000000000000L, MineDifficulty.HARD, 8, new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA), "", ""),
    WIZARD(40, "Wizard", 50000000000000L, MineDifficulty.HARD, 8, new ItemStack(Material.PINK_GLAZED_TERRACOTTA), "", ""),
    CLEOPATRA(41, "Cleopatra", 75000000000000L, MineDifficulty.HARD, 8, new ItemStack(Material.HARD_CLAY), "", "" ),
    INDIAN(42, "Indian", 150000000000000L, MineDifficulty.HARD, 8, new ItemStack(Material.RED_SANDSTONE), "", "");*/

    private int weight;
    private String name;
    private long price;
    private int enchantToken;
    private Material item;
    private Location location;

    Rank(int weight, String name, long price, int enchantToken, Material item) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.enchantToken = enchantToken;
        this.item = item;
    }

    Rank(int weight, String name, long price, int enchantToken, Material item, Location location) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.enchantToken = enchantToken;
        this.item = item;
        this.location = location;
    }

    Rank(int weight) {
        this.weight = weight;
    }

    /**
     * Vrací název Ranku jako String
     *
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Vrací cenu za rank up do dalšího ranku
     *
     * @return {@link Long}
     */
    public long getBasePrice() {
        return this.price;
    }

    /**
     * Vrací cenu pro rank up, dle zadané Prestige.<br>
     * Základní navyšování pro Prestiges > 1 = 15%.<br>
     * Příklad: Prestige 3 -> cena + 15%
     *
     * @param prestige Číslo prestige
     * @return {@link Long}
     */
    @NotNull
    public long getPriceByPrestige(int prestige) {
        if (prestige == 1) {
            return this.price; // Prestige 1 je startovací, základní ceny
        }
        int percentage = 15 * (prestige - 1); // 15 base navýšení
        return this.price + (this.price * percentage / 100);
    }

    /**
     * Vrací ID ranku, které sloužé také jako váha pro řazení
     *
     * @return {@link Integer}
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Vrací název ranku s prvním písmenem vyšší.
     * Deprecated z důvodu, že od Prisonu 3.0 jsou ranky opět A-Z.
     *
     * @return {@link String}
     */
    @Deprecated
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    /**
     * Vrací {@link Rank} dle zadaného názvu,
     * pokud rank neexistuje vrací první rank
     *
     * @param name  Název ranku
     * @return      {@link Rank}
     */
    @NotNull
    public static Rank getByName(@NotNull String name) {
        for (Rank r : getRankList()) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return Rank.A;
    }

    /**
     * Vrací {@link Rank} dle zadané váhy (ID)
     * pokud rank neexistuje vrací první rank
     *
     * @param weight    Váha nebo-li ID
     * @return          {@link Rank}
     */
    @NotNull
    public static Rank getByWeight(int weight) {
        for (Rank r : getRankList()) {
            if (r.getWeight() == weight) {
                return r;
            }
        }
        return Rank.A;
    }

    /**
     * Vrací string práva, který se nastavuje hráči při rank upu.
     *
     * @return {@link String}
     */
    public String getPermission() {
        return "craftprison.rank." + this.getName().toLowerCase(); // craftprison.rank.?
    }

    /**
     * Vrací string práva pro rank, dle zadaného ranku.
     *
     * @param rank  {@link Rank}
     * @return      {@link String}
     */
    public String getPermission(Rank rank) {
        return "craftprison.rank." + rank.getName().toLowerCase(); // craftprison.rank.octopus
    }

    /**
     * Vrací boolean, podle toho zda je zadaný rank stejný nebo nižší
     * než současný, co hráš má nastavený. Například:<br>
     *
     * Rank.A -> Rank.B: false<br>
     * Rank.B -> Rank.B: true<br>
     * Rank.C -> Rank.B: true<br>
     *
     * @param other {@link Rank}
     * @return      {@link Boolean}
     */
    public boolean isAtLeast(Rank other) {
        return getWeight() >= other.getWeight();
    }

    /**
     * Vrací list všech ranků.
     *
     * @return Array of {@link Rank}
     */
    public static Rank[] getRankList() {
        return Rank.values();
    }

    /**
     * Vrací list všech ranků jako list stringů.
     *
     * @return {@link List}
     */
    public static List<String> getRanksAsList() {
        return Stream.of(Rank.values()).map(Rank::getName).collect(Collectors.toList());
    }

    /**
     * Vrací další rank, dle aktuálně zadaného.
     * Pokud další rank neexistuje, třeba pro rank Z
     * vrací metoda hodnotu null.
     *
     * @return {@link Rank}
     */
    @Nullable
    public Rank getNext() {
        return this.ordinal() < Rank.values().length - 1 ? Rank.values()[this.ordinal() + 1] : null;
    }

    /**
     * Poslední rank z všech Prestiges
     *
     * @return {@link Rank}
     */
    public static Rank getLast() {
        return Rank.values()[getRankList().length - 1];
    }

    /**
     * Vrací hodnotu EnchantTokenu, kterou dostane hráč při rank upu
     *
     * @return {@link Integer} - Hodnota EnchantTokenů
     */
    public int getEnchantToken() {
        return enchantToken;
    }

    /**
     * Vrací item zobrazovaný v menu - /ranks
     *
     * @return {@link Material}
     */
    public Material getItem() {
        return item;
    }

    /**
     * Vrací pozici pro teleport na důl pro rank.
     *
     * @return {@link Location}
     */
    public Location getLocation() {
        return location;
    }
}
