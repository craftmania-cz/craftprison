package cz.wake.craftprison.objects;

public enum Rank {

    TUTORIAL_A(1, "A", 0),
    TUTORIAL_B(2, "B", 1000),
    TUTORIAL_C(3, "C", 4000),
    TUTORIAL_D(4, "D", 10000),
    GOOFY(5, "Goofy", 20000),
    BENDER(6, "Bender", 40000),
    MAGIKARP(7, "Magikarp", 80000),
    ZOIDBERG(8, "Zoidberg", 150000),
    MAXWELL(9, "Maxwell", 300000),
    STRANGE(10, "Strange", 500000);

    private int weight;
    private String name;
    private int price;

    Rank(int weight, String name, int price) {
        this.name = name;
        this.price = price;
        this.weight = weight;
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
}
