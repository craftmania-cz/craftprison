package cz.wake.craftprison.objects;

public enum Rank {

    TUTORIAL_A(1,"A",0),
    TUTORIAL_B(2,"B",1000),
    TUTORIAL_C(3,"C",3000),
    TUTORIAL_D(4,"D",8000),
    OCTOPUS(5,"Octopus",15000),
    VILLAGER(6,"Villager",30000);

    private int weight;
    private String name;
    private int price;

    Rank(int weight, String name, int price) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    Rank(int weight){
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    public Rank getByName(String name){
        for (Rank r : getTypes()){
            if(r.getName().equalsIgnoreCase(name)){
                return r;
            }
        }
        return null;
    }

    public Rank getByWeight(int weight){
        for(Rank r : getTypes()){
            if(r.getWeight() == weight){
                return r;
            }
        }
        return null;
    }

    public boolean isAtLeast(Rank other) {
        return getWeight() >= other.getWeight();
    }

    public static Rank[] getTypes() {
        return new Rank[]{TUTORIAL_A, TUTORIAL_B, TUTORIAL_C, TUTORIAL_D, OCTOPUS, VILLAGER};
    }

    public Rank getNext(){
        return this.ordinal() < Rank.values().length - 1 ? Rank.values()[this.ordinal() + 1] : null;
    }
}
