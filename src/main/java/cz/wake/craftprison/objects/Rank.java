package cz.wake.craftprison.objects;

public enum Rank {

    STARTER("Starter",0),
    OCTOPUS("Octopus",3000);

    private String name;
    private int price;

    Rank(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
