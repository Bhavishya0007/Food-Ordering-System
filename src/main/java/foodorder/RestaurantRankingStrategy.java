package foodorder;

import java.util.List;
import java.util.Map;

/** Strategy for turning restaurantId -> averageRating into a ranked list. */
public interface RestaurantRankingStrategy {
    List<String> rank(Map<String, Double> restaurantAverages, int limit);
}
