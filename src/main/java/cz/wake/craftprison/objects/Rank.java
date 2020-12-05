package cz.wake.craftprison.objects;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Seznam ranků pro Prestiges
 * Tento seznam je stejný napříč všemi Prestiges
 */
public enum Rank {

    A(1, "A", 0, 0, Material.BEDROCK),
    B(2, "B", 1000, 1, Material.BEDROCK), // craftprison.mine.nightvision
    C(3, "C", 4000, 1, Material.BEDROCK),
    D(4, "D", 10000, 1, Material.BEDROCK), // "Odemknuti vytvareni ostrova /is", "askyblock.island.create"
    E(5, "E", 20000, 2, Material.BEDROCK), // "Odemknuti pouzivani Aukce /au (Max 1 aukce)", "auctionguiplus.auction", "auctionguiplus.auction.bid", "auctionguiplus.auction.start", "auctionguiplus.auction.bid"
    F(6, "F", 40000, 2, Material.BEDROCK), // "Odemknuti prikazu /pvp", "craftprison.pvp", "quicksell.shop.pvp"),
    G(7, "G", 80000, 2, Material.BEDROCK), // "Odemknuti /shop + sekce Blocks", "shopguiplus.shop", "shopguiplus.shops.blocks"),
    H(8, "H", 150000, 2, Material.BEDROCK), // "", ""),
    I(9, "I", 300000, 2, Material.BEDROCK), // "Odemknuti prikazu /heads", "headdb.allow.buy", "headdb.category.*", "headdb.open"),
    J(10, "J", 500000, 2, Material.BEDROCK), // "", ""),
    K(11, "K", 800000, 3, Material.BEDROCK), // "Moznost pridat 3 hrace na ostrov (+1)", "askyblock.team.maxsize.3"),
    L(12, "L", 1200000, 3, Material.BEDROCK),
    M(13, "M", 1500000, 3, Material.BEDROCK), //, "Odemknuti sekce Decorations & Foods v /shop", "shopguiplus.shops.food", "shopguiplus.shops.dekorace"),
    N(14, "N", 2000000, 3, Material.BEDROCK), //, "", ""),
    O(15, "O", 3000000, 3, Material.BEDROCK), // "Automaticky FLY v dolech", "craftprison.mine.fly"),
    P(16, "P", 5000000, 4, Material.BEDROCK), //, "", ""),
    Q(17, "Q", 10000000, 4, Material.BEDROCK), //, 1, (short) 15), "Odemknuti vsech sekci v /shop", "shopguiplus.shops.*"),
    R(18, "R", 20000000, 4, Material.BEDROCK), // "", ""),
    S(19, "S", 30000000, 4, Material.BEDROCK), //, "Moznost vytvaret 2x Aukce", ""),
    T(20, "T", 50000000, 4, Material.BEDROCK),
    U(21, "U", 100000000, 5, Material.BEDROCK), //, "Moznost menit biom na ostrove", ""),
    V(22, "V", 250000000, 5, Material.BEDROCK), //, 1, (short) 9), "", ""),
    W(23, "W", 500000000, 5, Material.BEDROCK), //, 1, (short) 3), "Moznost pridat 4 hrace na ostrov (+2)", "askyblock.team.maxsize.4"),
    X(24, "X", 1000000000, 5, Material.BEDROCK), //, "", ""),
    Y(25, "Y", 2000000000, 5, Material.BEDROCK), //, "", ""),
    Z(26, "Z", 4000000000L, 6, Material.BEDROCK); //, 1, (short)11), "Vytvareni shopu pomoci Chestshop", "shop.create", "shop.create.buy", "shop.create.sell");

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
    private MineDifficulty difficulty;
    private int enchantToken;
    private String reward;
    private String[] permissions;
    private Material item;

    @Deprecated
    Rank(int weight, String name, long price, MineDifficulty difficulty, int enchantToken, Material item, String reward, String... permissions) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.difficulty = difficulty;
        this.enchantToken = enchantToken;
        this.reward = reward;
        this.permissions = permissions;
        this.item = item;
    }

    Rank(int weight, String name, long price, int enchantToken, Material item) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.enchantToken = enchantToken;
        this.item = item;
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
    public long getPrice() {
        return this.price;
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
     * Vrací obtížnost ranku, jak moc trvá naplnit inventář.
     * @deprecated Jelikož nový systém toto již nevyžaduje
     *
     * @return {@link MineDifficulty}
     */
    @Deprecated
    public MineDifficulty getDifficulty() {
        return difficulty;
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
     * Vrací název odměny pro daný rank
     *
     * @return Název odměny
     */
    public String getReward() {
        return reward;
    }

    /**
     * Vrací list práv, které hráč dostane při rank upu.
     *
     * @return {@link String[]}
     */
    public String[] getRewardPermissions() {
        return permissions;
    }

    /**
     * Vrací item zobrazovaný v menu - /ranks
     *
     * @return {@link Material}
     */
    public Material getItem() {
        return item;
    }
}
