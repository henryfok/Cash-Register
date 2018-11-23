package Store;

import java.util.HashMap;

public class CouponStore {
    private HashMap<String, Coupon> couponStore = new HashMap<>();

    public void addCoupon(Coupon coupon) {
        couponStore.put(coupon.id, coupon);
    }

    public boolean validCoupon(String id) {
        return couponStore.containsKey(id);
    }

    public Coupon getCoupon(String id) {
        return couponStore.get(id);
    }
}