package cz.wake.craftprison.objects;

public enum Rank {

    TUTORIAL_A(1, "A", 0, MineDifficulty.TUTORIAL, 0, "", ""),
    TUTORIAL_B(2, "B", 1000, MineDifficulty.TUTORIAL, 1, "NightVision v dolech", "craftprison.mine.nightvision"),
    TUTORIAL_C(3, "C", 4000, MineDifficulty.TUTORIAL, 1, "", ""),
    TUTORIAL_D(4, "D", 10000, MineDifficulty.TUTORIAL, 1, "Odemknuti vytvareni ostrova /is", "askyblock.island.create"),
    GOOFY(5, "Goofy", 20000, MineDifficulty.STANDARD, 1, "Odemknuti pouzivani Aukce /au (Max 1 aukce)", "auctionguiplus.auction", "auctionguiplus.auction.bid", "auctionguiplus.auction.start", "auctionguiplus.auction.bid"),
    BENDER(6, "Bender", 40000, MineDifficulty.STANDARD, 1, "Odemknuti prikazu /pvp", "craftprison.pvp"),
    MAGIKARP(7, "Magikarp", 80000, MineDifficulty.STANDARD, 1, "Odemknuti /shop + sekce Blocks", "shopguiplus.shop", "shopguiplus.shops.blocks"),
    ZOIDBERG(8, "Zoidberg", 150000, MineDifficulty.STANDARD, 1, "", ""),
    MAXWELL(9, "Maxwell", 300000, MineDifficulty.STANDARD, 1, "Odemknuti prikazu /heads", "headdb.allow.buy", "headdb.category.*", "headdb.open"),
    STRANGE(10, "Strange", 500000, MineDifficulty.STANDARD, 1, "", ""),
    SHREK(11, "Shrek", 800000, MineDifficulty.STANDARD, 2, "Moznost pridat 3 hrace na ostrov (+1)", "askyblock.team.maxsize.3"),
    FIONA(12, "Fiona", 1200000, MineDifficulty.STANDARD, 2, "", ""),
    DEADPOOL(13, "Deadpool", 1500000, MineDifficulty.STANDARD, 2, "Odemknuti sekce Decorations & Foods v /shop", "shopguiplus.shops.food", "shopguiplus.shops.dekorace"),
    GANDALF(14, "Gandalf", 2000000, MineDifficulty.STANDARD, 2, "", ""),
    ASTRONAUT(15, "Astronaut", 3000000, MineDifficulty.STANDARD, 2, "Automaticky FLY v dolech", "craftprison.mine.fly"),
    MUMMY(16, "Mummy", 5000000, MineDifficulty.STANDARD, 2, "", ""),
    CRASH(17, "Crash", 10000000, MineDifficulty.STANDARD, 2, "Odemknuti vsech sekci v /shop", "shopguiplus.shops.*"),
    OBELIX(18, "Obelix", 20000000, MineDifficulty.STANDARD, 2, "", ""),
    ELSA(19, "Elsa", 30000000, MineDifficulty.STANDARD, 2, "Moznost vytvaret 2x Aukce", ""), //TODO: Aukce
    ANGEL(20, "Angel", 50000000, MineDifficulty.STANDARD, 2, "", ""),
    CARL(21, "Carl", 100000000, MineDifficulty.STANDARD, 2, "Moznost menit biom na ostrove", ""),
    THANOS(22, "Thanos", 250000000, MineDifficulty.STANDARD, 2, "", ""),
    GROOT(21, "Groot", 500000000, MineDifficulty.STANDARD, 2, "Moznost pridat 4 hrace na ostrov (+2)", "askyblock.team.maxsize.4");

    private int weight;
    private String name;
    private int price;
    private MineDifficulty difficulty;
    private int prisCoins;
    private String reward;
    private String[] array;

    Rank(int weight, String name, int price, MineDifficulty difficulty, int prisCoins, String reward, String... array) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.difficulty = difficulty;
        this.prisCoins = prisCoins;
        this.reward = reward;
        this.array = array;
    }

    Rank(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
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
}
