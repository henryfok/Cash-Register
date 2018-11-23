package Store;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Register {
    private static ArrayList<Grocery> gs = new ArrayList<>();
    private static DiscountStore ds = new DiscountStore();
    private static CouponStore cs = new CouponStore();

    // Formatter to print commas for every 1000 and two leading zeros after decimal
    private static DecimalFormat twoDecPts = new DecimalFormat("#,##0.00");

    public static void main(String[] args) {
        // Create current store discounts
        Discount CerealDiscount = new Discount("Cereal", 2, 1);
        Discount ChocolateBarDiscount = new Discount("Chocolate Bar", 1, 1);
        // Add current store discounts
        ds.addDiscount(CerealDiscount);
        ds.addDiscount(ChocolateBarDiscount);

        // Create coupons
        Coupon testCoupon = new Coupon("10off20", 20, 10);
        Coupon impossibleCoupon = new Coupon("9999", 9999.99, 10);
        // Add coupons to current valid ones
        cs.addCoupon(testCoupon);
        cs.addCoupon(impossibleCoupon);

        // Add groceries to ArrayList before calculating grand total.
        // gs.add(new Grocery("Name", Price, Unit, byItem?, byWeight?));
        // Price: Cost of item per unit
        // Unit: Number of items / Amount (lb, kg, L, etc...) customer bought

        gs.add(new Grocery("Apples", 2.49, 3, false, true));
        gs.add(new Grocery("Steak", 10.99, 2, false, true));
        gs.add(new Grocery("Cereal", 6.99, 7, true, false));
        gs.add(new Grocery("Milk", 4.99, 1, true, false));
        gs.add(new Grocery("Chocolate Bar", 3.99, 5, true, false));

        double subTotal = 0;
        double subTotalAndCoupon = 0;

        for(Grocery grocery : gs) {
            subTotal += addGrocery(grocery);
            System.out.println("Current subtotal: $" + twoDecPts.format(subTotal));
        }

        subTotalAndCoupon += applyCoupon(subTotal);
        System.out.println("Grand Total: $" + twoDecPts.format(subTotalAndCoupon));
    }

    private static void printGrocery(Grocery grocery) {
        if (grocery.byItem) {
            System.out.println("Adding " + grocery.units + " " + grocery.name + " at $" + twoDecPts.format(grocery.price) + " each.");
        } else {
            System.out.println("Adding " + grocery.units + " pound(s) " + grocery.name + " at $" + twoDecPts.format(grocery.price) + " per pound.");
        }
    }

    private static double addGrocery(Grocery grocery) {
        // System.out.println(grocery.name);
        printGrocery(grocery);
        double subTotal = 0;
        if (grocery.byItem) {
            subTotal = grocery.price * grocery.units;

            // Check Discount hashmap to see if there is a discount for the grocery item
            if(ds.hasDiscount(grocery.name)) {
                System.out.println("> There is a discount for " + grocery.name);
                System.out.println("> Subtotal for " + grocery.name + " before discount: $" + twoDecPts.format(subTotal));

                Discount discount = ds.getDiscount(grocery.name);
                subTotal = applyDiscount(discount, grocery, subTotal);
                System.out.println("> Subtotal for " + grocery.name + " after discount: $" + twoDecPts.format(subTotal));
                return subTotal;
            } else {
                return subTotal;
            }
        } else { //if (grocery.byWeight)
            subTotal = grocery.price * grocery.units;
            return subTotal;
        }
    }

    private static double applyDiscount(Discount discount, Grocery grocery, double subTotal) {
        int discountUnits = discount.numBought + discount.numFree;
        // Customer has bought equal or more number of the grocery item than the discount requirement
        // Ex: Buy two get one free. Customer needs to buy at least 3 to save on the third one
        if (discountUnits <= grocery.units) {
            // if there is a discount, and customer meets the requirements, calculate savings
            double numOfDiscount = Math.floor(grocery.units / discountUnits);
            System.out.println("> Discount: Buy " + discount.numBought + " get " + discount.numFree + " free!");
            System.out.println("> Num of times discount can be applied: " + numOfDiscount);

            // Need to calculate how many times the discount is applied
            // Ex: Buy two get one free. Buying 4 vs buying 5, both only apply the discount once
            // But buying 6 applies the discount 2 times
            double numFreeItems = numOfDiscount * discount.numFree;
            System.out.println("> Num of free items: " + numFreeItems);
            System.out.println("> $" + twoDecPts.format(numFreeItems * grocery.price) + " off");
            return (subTotal - (numFreeItems * grocery.price));
        } else {
            // if there is a discount, but customer does not meet the requirements, just return original subTotal
            return subTotal;
        }
    }

    private static double applyCoupon(double subTotal) {
        Scanner userInput = new Scanner(System.in);
        String userCoupon;
        boolean askedCoupon = false;

        // Check if the user-inputted coupon code matches any coupon ID in the Coupon hashset.
        // If user input is blank (no coupon), return original subtotal.
        do {
            if (askedCoupon) {
                System.out.print("That's not a valid coupon, try again: ");
            } else {
                System.out.print("Enter coupon id (Blank for no coupon): ");
            }
            userCoupon = userInput.nextLine();
            if (userCoupon.isEmpty()) {
                return subTotal;
            }
            askedCoupon = true;
        } while (!(cs.validCoupon(userCoupon)));


        if (cs.validCoupon(userCoupon)) {
            System.out.println("That's a valid coupon");
            Coupon coupon = cs.getCoupon(userCoupon);
            System.out.println("Coupon: $" + twoDecPts.format(coupon.discount) + " off when you spend $" + twoDecPts.format(coupon.spentCondition) + " or more");
            // Check if subtotal meets coupon requirements
            if (subTotal >= coupon.spentCondition) {
                return (subTotal - coupon.discount);
            } else {
                System.out.println("You have not met the coupon requirements!!");
                return subTotal;
            }
        } else {
            System.out.println("That's NOT a valid coupon");
            return subTotal;
        }
    }
}