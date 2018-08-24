package cz.wake.craftprison.objects;

import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Rank {

    TUTORIAL_A(1, "A", 0, MineDifficulty.TUTORIAL, 0, new ItemStack(Material.COBBLESTONE), "", ""),
    TUTORIAL_B(2, "B", 1000, MineDifficulty.TUTORIAL, 1, new ItemStack(Material.STONE, 1, (short) 4), "NightVision v dolech", "craftprison.mine.nightvision"),
    TUTORIAL_C(3, "C", 4000, MineDifficulty.TUTORIAL, 1, new ItemStack(Material.STONE, 1, (short) 1), "", ""),
    TUTORIAL_D(4, "D", 10000, MineDifficulty.TUTORIAL, 1, new ItemStack(Material.LAPIS_ORE), "Odemknuti vytvareni ostrova /is", "askyblock.island.create"),
    GOOFY(5, "Goofy", 20000, MineDifficulty.STANDARD, 1, new ItemStack(Material.STONE, 1, (short) 2), "Odemknuti pouzivani Aukce /au (Max 1 aukce)", "auctionguiplus.auction", "auctionguiplus.auction.bid", "auctionguiplus.auction.start", "auctionguiplus.auction.bid"),
    BENDER(6, "Bender", 40000, MineDifficulty.STANDARD, 1, new ItemStack(Material.COAL_ORE), "Odemknuti prikazu /pvp", "craftprison.pvp", "quicksell.shop.pvp"),
    MAGIKARP(7, "Magikarp", 80000, MineDifficulty.STANDARD, 1, new ItemStack(Material.STONE, 1, (short) 6), "Odemknuti /shop + sekce Blocks", "shopguiplus.shop", "shopguiplus.shops.blocks"),
    ZOIDBERG(8, "Zoidberg", 150000, MineDifficulty.STANDARD, 1, new ItemStack(Material.MOSSY_COBBLESTONE, 1, (short) 1), "", ""),
    MAXWELL(9, "Maxwell", 300000, MineDifficulty.STANDARD, 1, new ItemStack(Material.SANDSTONE), "Odemknuti prikazu /heads", "headdb.allow.buy", "headdb.category.*", "headdb.open"),
    STRANGE(10, "Strange", 500000, MineDifficulty.STANDARD, 1, new ItemStack(Material.PRISMARINE, 1, (short) 2), "", ""),
    SHREK(11, "Shrek", 800000, MineDifficulty.STANDARD, 2, new ItemStack(Material.STAINED_CLAY, 1, (short) 5), "Moznost pridat 3 hrace na ostrov (+1)", "askyblock.team.maxsize.3"),
    FIONA(12, "Fiona", 1200000, MineDifficulty.STANDARD, 2, new ItemStack(Material.STAINED_CLAY, 1, (short) 11), "", ""),
    DEADPOOL(13, "Deadpool", 1500000, MineDifficulty.STANDARD, 2, new ItemStack(Material.IRON_BLOCK), "Odemknuti sekce Decorations & Foods v /shop", "shopguiplus.shops.food", "shopguiplus.shops.dekorace"),
    GANDALF(14, "Gandalf", 2000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.GOLD_ORE), "", ""),
    ASTRONAUT(15, "Astronaut", 3000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.CONCRETE, 1, (short) 14), "Automaticky FLY v dolech", "craftprison.mine.fly"),
    MUMMY(16, "Mummy", 5000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.GOLD_BLOCK), "", ""),
    CRASH(17, "Crash", 10000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.STAINED_CLAY, 1, (short) 15), "Odemknuti vsech sekci v /shop", "shopguiplus.shops.*"),
    OBELIX(18, "Obelix", 20000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.BONE_BLOCK), "", ""),
    ELSA(19, "Elsa", 30000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.CONCRETE, 1, (short) 3), "Moznost vytvaret 2x Aukce", ""),
    ANGEL(20, "Angel", 50000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), "", ""),
    CARL(21, "Carl", 100000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.REDSTONE_BLOCK), "Moznost menit biom na ostrove", ""),
    THANOS(22, "Thanos", 250000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.STAINED_CLAY, 1, (short) 9), "", ""),
    GROOT(23, "Groot", 500000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.SMOOTH_BRICK, 1, (short) 3), "Moznost pridat 4 hrace na ostrov (+2)", "askyblock.team.maxsize.4"),
    JONES(24, "Jones", 1000000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.COAL_BLOCK), "", ""),
    CAPTAIN(25, "Captain", 2000000000, MineDifficulty.STANDARD, 2, new ItemStack(Material.RED_GLAZED_TERRACOTTA), "", ""),
    OPTIMUS(26, "Optimus", 4000000000L, MineDifficulty.STANDARD, 2, new ItemStack(Material.CONCRETE, 1, (short)11), "Vytvareni shopu pomoci Chestshop", "shop.create", "shop.create.buy", "shop.create.sell"),
    PANTHER(27, "Panther", 10000000000L, MineDifficulty.STANDARD, 2, new ItemStack(Material.QUARTZ, 1, (short)1), "", ""),
    DOCTOR(28, "Doctor", 20000000000L, MineDifficulty.STANDARD, 2, new ItemStack(Material.DIAMOND_BLOCK), "Moznost pridat 6 hrace na ostrov (+4)", "askyblock.team.maxsize.6"),
    KRATOS(29, "Kratos", 30000000000L, MineDifficulty.STANDARD, 2, new ItemStack(Material.MAGMA), "", ""),
    LINK(30, "Link", 75000000000L, MineDifficulty.HARD, 3, new ItemStack(Material.GREEN_GLAZED_TERRACOTTA), "", ""),
    KENNY(31, "Kenny", 200000000000L, MineDifficulty.HARD, 3, new ItemStack(Material.ENDER_STONE), "Prodej v dolech pravym kliknutim s krumpacem", "craftprison.pickaxe.rightclick.sell"),
    WITHER(32, "Wither", 400000000000L, MineDifficulty.HARD, 3, new ItemStack(Material.BEDROCK), "", ""),
    EGGMAN(33, "Eggman", 600000000000L, MineDifficulty.HARD, 3, new ItemStack(Material.BROWN_GLAZED_TERRACOTTA), "", ""),
    DOOMFIST(34, "Doomfist", 1200000000000L, MineDifficulty.HARD, 4, new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA), "", ""),
    ORC(35, "Orc", 2000000000000L, MineDifficulty.HARD, 4, new ItemStack(Material.DIAMOND_BLOCK), "", ""),
    STORMTROOPER(36, "Stormtrooper", 4500000000000L, MineDifficulty.HARD, 4, new ItemStack(Material.QUARTZ_BLOCK), "", ""),
    DWARF(37, "Dwarf", 8000000000000L, MineDifficulty.HARD, 4, new ItemStack(Material.BLUE_GLAZED_TERRACOTTA), "", ""),
    PRISMARINER(38, "Prismariner", 15000000000000L, MineDifficulty.HARD, 5, new ItemStack(Material.PRISMARINE, 1, (short)2), "", ""),
    MRPIG(39, "MrPig", 25000000000000L, MineDifficulty.HARD, 5, new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA), "", ""),
    WIZARD(40, "Wizard", 50000000000000L, MineDifficulty.HARD, 5, new ItemStack(Material.PINK_GLAZED_TERRACOTTA), "", "");

    private int weight;
    private String name;
    private long price;
    private MineDifficulty difficulty;
    private int prisCoins;
    private String reward;
    private String[] array;
    private ItemStack item;

    Rank(int weight, String name, long price, MineDifficulty difficulty, int prisCoins, ItemStack item, String reward, String... array) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.difficulty = difficulty;
        this.prisCoins = prisCoins;
        this.reward = reward;
        this.array = array;
        this.item = item;
    }

    Rank(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return this.price;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    public static Rank getByName(String name) {
        for (Rank r : getTypes()) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }

    public static Rank getByWeight(int weight) {
        for (Rank r : getTypes()) {
            if (r.getWeight() == weight) {
                return r;
            }
        }
        return null;
    }

    public MineDifficulty getDifficulty() {
        return difficulty;
    }

    public String getPermission() {
        return "craftprison.rank." + this.getName().toLowerCase(); // craftprison.rank.?
    }

    public String getPermission(Rank r) {
        return "craftprison.rank." + r.getName().toLowerCase(); // craftprison.rank.octopus
    }

    public boolean isAtLeast(Rank other) {
        return getWeight() >= other.getWeight();
    }

    public static Rank[] getTypes() {
        return Rank.values();
    }

    public Rank getNext() {
        return this.ordinal() < Rank.values().length - 1 ? Rank.values()[this.ordinal() + 1] : null;
    }

    public static Rank getLast() {
        return Rank.values()[getTypes().length - 1];
    }

    public int getPrisCoins() {
        return prisCoins;
    }

    public String getReward() {
        return reward;
    }

    public String[] getCommands() {
        return array;
    }

    public ItemStack getItem() {
        return item;
    }
}
