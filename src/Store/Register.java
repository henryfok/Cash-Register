package Store;

public class Register {
    private static DiscountStore ds = new DiscountStore();
    private static CouponStore cs = new CouponStore();

    public static void main(String[] args) {
//        Register r = new Register();

        Grocery testG = new Grocery("apples", 5, 6, true, false);
        Discount testD = new Discount("apples", 2, 1);
        Coupon testC = new Coupon("ABC", 19.99, 10);

        printGrocery(testG);

        ds.addDiscount(testD);
        cs.addCoupon(testC);

        addGrocery(testG);
    }

    private static void printGrocery(Grocery grocery) {
        System.out.println(grocery.units + " " + grocery.name + " at $" + grocery.price);
    }

    private static void addGrocery(Grocery grocery) {
        System.out.println("Adding " + grocery.name);
        double subTotal = 0;
        if (grocery.byItem) {
            subTotal = grocery.price * grocery.units;
            if(ds.hasDiscount(grocery.name)) {
                System.out.println("There is a discount for " + grocery.name);
                System.out.println("subTotal before discount: " + subTotal);
                Discount discount = ds.getDiscount(grocery.name);
                subTotal = applyDiscount(discount, grocery, subTotal);
                System.out.println("subTotal: " + subTotal);
            }

        }
        if (grocery.byWeight) {
            subTotal = grocery.price * grocery.units;
        }
    }

    private static double applyDiscount(Discount discount, Grocery grocery, double subTotal) {
        int discountUnit = discount.numBought + discount.numFree;
        if (discountUnit <= grocery.units) {
            double numOfDiscount = Math.floor(grocery.units / discountUnit);
            System.out.println("Discount: Buy " + discount.numBought + " get " + discount.numFree + " free!");
            System.out.println("num of times discount can be applied: " + numOfDiscount);

            double numFreeItems = numOfDiscount * discount.numFree;
            System.out.println("num of free items: " + numFreeItems);
            System.out.println("$" + (numFreeItems * grocery.price) + " off");
            return (subTotal - (numFreeItems * grocery.price));
        }
        else {
            return subTotal;
        }
    }

    private static void applyCoupon(Coupon coupon, double subTotal) {


    }
}
