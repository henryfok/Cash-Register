package Store;

// This class defines each grocery item such as their name and price, as well as whether their price depends on their weight.

public class Grocery {
    public String name;     // Name of item (ex: Apples)
    public double price;    // Price per unit (box, lb, etc...)
    public double units;    // Number of units of the item bought
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