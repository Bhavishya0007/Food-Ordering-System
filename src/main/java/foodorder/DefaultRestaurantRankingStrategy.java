package foodorder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Ranks restaurants by rating descending, breaking ties by id ascending. */
public final class DefaultRestaurantRankingStrategy implements RestaurantRankingStrategy {
    @Override
    public List<String> rank(Map<String, Double> restaurantAverages, int limit) {
        Comparator<Map.Entry<String, Double>> byRatingDescThenIdAsc =
                Comparator.<Map.Entry<String, Double>>comparingDouble(Map.Entry::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey);

        List<String> ranked = restaurantAverages.entrySet().stream()
                .sorted(byRatingDescThenIdAsc)
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(ranked);
    }
}
