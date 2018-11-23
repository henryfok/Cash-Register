package Store;

public class Coupon {
    public String id;               // ID that identifies the coupon. Used to compare the user inputted coupon code.
    public double spentCondition;   // Requirement for coupon to take effect.
    public double discount;         // Amount deducted off balance.

    public Coupon(String id, double spentCondition, double discount) {
        this.id = id;
        this.spentCondition = spentCondition;
        this.discount = discount;
    }
}
