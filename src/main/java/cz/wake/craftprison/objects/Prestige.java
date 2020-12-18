package cz.wake.craftprison.objects;

public enum Prestige {

    ONE(1),
    TWO(2, "Přístup k /backpack", "backpack.user"),
    THREE(3, "Backpack velikost: 2 řádky + /pay příkaz", "backpack.size.2", "crafteconomy.commands.pay"),
    FOUR(4, "Backpack velikost: 4 řádky", "backpack.size.4"),
    FIVE(5, "Backpack velikost: 6 řádků", "backpack.size.6");

    private int weight;
    private String description;
    private String[] permission;

    Prestige(int weight) {
        this.weight = weight;
    }

    Prestige(int weight, String description, String ...permission) {
        this.weight = weight;
        this.description = description;
        this.permission = permission;
    }

    public int getWeight() {
        return weight;
    }

    public String[] getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAtLeast(Prestige other) {
        return getWeight() >= other.getWeight();
    }

    public static Prestige getByWeight(int weight) {
        for (Prestige r : Prestige.values()) {
            if (r.getWeight() == weight) {
                return r;
            }
        }
        return null;
    }
}
