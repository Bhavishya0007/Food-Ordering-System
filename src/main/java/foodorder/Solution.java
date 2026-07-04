package foodorder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Solution implements OrderingSystem {
    private static final int TOP_LIMIT = 20;

    private final RestaurantRankingStrategy rankingStrategy;
    private final Map<String, Order> orders = new HashMap<>();
    private final Set<String> restaurantIds = new HashSet<>();
    private final Map<String, RatingStats> restaurantRatings = new HashMap<>();
    private final Map<String, RatingStats> foodRatings = new HashMap<>();

    private Helper05 helper;

    public Solution() {
        this(new DefaultRestaurantRankingStrategy());
    }

    public Solution(RestaurantRankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    @Override
    public void init(Helper05 helper) {
        this.helper = helper;
    }

    @Override
    public void orderFood(String orderId, String restaurantId, String foodItemId) {
        orders.put(orderId, new Order(orderId, restaurantId, foodItemId));
        restaurantIds.add(restaurantId);
        restaurantRatings.putIfAbsent(restaurantId, RatingStats.EMPTY);
        foodRatings.putIfAbsent(foodKey(restaurantId, foodItemId), RatingStats.EMPTY);

        log("Order placed: " + orderId + " -> restaurant=" + restaurantId + ", food=" + foodItemId);
    }

    @Override
    public void rateOrder(String orderId, int rating) {
        Order order = orders.get(orderId);
        String restaurantId = order.getRestaurantId();
        String foodItemId = order.getFoodItemId();

        restaurantRatings.compute(restaurantId, (id, stats) -> stats.plus(rating));
        foodRatings.compute(foodKey(restaurantId, foodItemId), (key, stats) -> stats.plus(rating));

        log("Order rated: " + orderId + " -> " + rating + " stars");
    }

    @Override
    public List<String> getTopRestaurantsByFood(String foodItemId) {
        Map<String, Double> averages = new HashMap<>();
        for (String restaurantId : restaurantIds) {
            RatingStats stats = foodRatings.getOrDefault(foodKey(restaurantId, foodItemId), RatingStats.EMPTY);
            averages.put(restaurantId, stats.average());
        }
        return rankingStrategy.rank(averages, TOP_LIMIT);
    }

    @Override
    public List<String> getTopRatedRestaurants() {
        Map<String, Double> averages = new HashMap<>();
        for (String restaurantId : restaurantIds) {
            averages.put(restaurantId, restaurantRatings.getOrDefault(restaurantId, RatingStats.EMPTY).average());
        }
        return rankingStrategy.rank(averages, TOP_LIMIT);
    }

    private static String foodKey(String restaurantId, String foodItemId) {
        return restaurantId + "#" + foodItemId;
    }

    private void log(String message) {
        if (helper != null) {
            helper.log(message);
        }
    }
}
