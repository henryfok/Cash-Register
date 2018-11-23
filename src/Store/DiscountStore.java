package Store;

import java.util.HashMap;

public class DiscountStore {
    private HashMap<String, Discount> discountStore = new HashMap<>();

    public void addDiscount(Discount discount) {
        discountStore.put(discount.name, discount);
    }

    public boolean hasDiscount(String itemName) {
        return discountStore.containsKey(itemName);
    }

    public Discount getDiscount(String itemName) {
        return discountStore.get(itemName);
    }
}
