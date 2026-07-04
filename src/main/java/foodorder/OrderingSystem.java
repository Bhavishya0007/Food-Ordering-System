package foodorder;

import java.util.List;

/** Contract for a restaurant food-ordering-and-rating system. */
public interface OrderingSystem {
    void init(Helper05 helper);

    void orderFood(String orderId, String restaurantId, String foodItemId);

    void rateOrder(String orderId, int rating);

    List<String> getTopRestaurantsByFood(String foodItemId);

    List<String> getTopRatedRestaurants();
}
