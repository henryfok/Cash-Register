package Store;

public class Discount {
    public String name;     // Name of discount, should match with the corresponding Grocery item.
    public int numBought;   // Requirement for discount to apply
    public int numFree;     // Number of items for free if requirement is met

    public Discount(String name, int numBought, int numFree) {
        this.name = name;
        this.numBought = numBought;
        this.numFree = numFree;
    }
}