package Store;

public class Coupon {
    public String id;
    public double spentCondition;
    public double discount;

    public Coupon(String id, double spentCondition, double discount) {
        this.id = id;
        this.spentCondition = spentCondition;
        this.discount = discount;
    }
}
