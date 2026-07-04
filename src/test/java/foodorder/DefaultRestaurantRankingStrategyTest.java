package foodorder;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultRestaurantRankingStrategyTest {

    private final RestaurantRankingStrategy strategy = new DefaultRestaurantRankingStrategy();

    @Test
    void sortsByRatingDescending() {
        Map<String, Double> averages = new LinkedHashMap<>();
        averages.put("restaurant-3", 4.2);
        averages.put("restaurant-2", 4.6);
        averages.put("restaurant-5", 4.4);
        averages.put("restaurant-6", 4.6);

        List<String> ranked = strategy.rank(averages, 20);

        assertEquals(List.of("restaurant-2", "restaurant-6", "restaurant-5", "restaurant-3"), ranked);
    }

    @Test
    void breaksTiesLexicographicallyAscending() {
        Map<String, Double> averages = new LinkedHashMap<>();
        averages.put("restaurant-9", 3.0);
        averages.put("restaurant-1", 3.0);

        List<String> ranked = strategy.rank(averages, 20);

        assertEquals(List.of("restaurant-1", "restaurant-9"), ranked);
    }

    @Test
    void respectsLimit() {
        Map<String, Double> averages = new LinkedHashMap<>();
        for (int i = 0; i < 25; i++) {
            averages.put("restaurant-" + i, (double) i);
        }

        List<String> ranked = strategy.rank(averages, 20);

        assertEquals(20, ranked.size());
        assertEquals("restaurant-24", ranked.get(0));
    }
}
