package Store;

public class Grocery {
    public String name;
    public double price;
    public double units;
    public boolean byItem;
    public boolean byWeight;

    public Grocery(String name, double price, double units, boolean byItem, boolean byWeight) {
        this.name = name;
        this.price = price;
        this.units = units;
        this.byItem = byItem;
        this.byWeight = byWeight;
    }
}