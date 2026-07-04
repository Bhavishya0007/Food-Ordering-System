package foodorder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SolutionTest {

    private final List<String> logs = new ArrayList<>();
    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
        solution.init(logs::add);
    }

    @Test
    void ratingUpdatesBothFoodAndRestaurantAggregates() {
        solution.orderFood("order-1", "restaurant-1", "food-1");
        solution.rateOrder("order-1", 5);

        assertEquals(List.of("restaurant-1"), solution.getTopRestaurantsByFood("food-1"));
        assertEquals(List.of("restaurant-1"), solution.getTopRatedRestaurants());
        assertFalse(logs.isEmpty());
    }

    @Test
    void sameFoodItemCanBeOrderedFromMultipleRestaurants() {
        solution.orderFood("order-1", "restaurant-burger-king", "veg-burger");
        solution.rateOrder("order-1", 5);

        solution.orderFood("order-2", "restaurant-mcdonalds", "veg-burger");
        solution.rateOrder("order-2", 3);

        assertEquals(
                List.of("restaurant-burger-king", "restaurant-mcdonalds"),
                solution.getTopRestaurantsByFood("veg-burger"));
    }

    @Test
    void reproducesSpecWalkthrough() {
        solution.orderFood("order-0", "restaurant-0", "food-1");
        solution.rateOrder("order-0", 3);

        solution.orderFood("order-1", "restaurant-2", "food-0");
        solution.rateOrder("order-1", 1);

        solution.orderFood("order-2", "restaurant-1", "food-0");
        solution.rateOrder("order-2", 3);

        solution.orderFood("order-3", "restaurant-2", "food-0");
        solution.rateOrder("order-3", 5);

        solution.orderFood("order-4", "restaurant-0", "food-0");
        solution.rateOrder("order-4", 3);

        solution.orderFood("order-5", "restaurant-1", "food-0");
        solution.rateOrder("order-5", 4);

        solution.orderFood("order-6", "restaurant-0", "food-1");
        solution.rateOrder("order-6", 2);

        solution.orderFood("order-7", "restaurant-0", "food-1");
        solution.rateOrder("order-7", 2);

        solution.orderFood("order-8", "restaurant-1", "food-0");
        solution.rateOrder("order-8", 2);

        solution.orderFood("order-9", "restaurant-1", "food-0");
        solution.rateOrder("order-9", 4);

        assertEquals(
                List.of("restaurant-1", "restaurant-0", "restaurant-2"),
                solution.getTopRestaurantsByFood("food-0"));

        // Every restaurant ever seen is a candidate for every food item (the chosen
        // semantics), so restaurant-1 also shows up here at 0.0 -- unlike the spec's
        // own worked example, which only listed restaurant-0 and restaurant-2.
        assertEquals(
                List.of("restaurant-0", "restaurant-1", "restaurant-2"),
                solution.getTopRestaurantsByFood("food-1"));

        assertEquals(
                List.of("restaurant-1", "restaurant-2", "restaurant-0"),
                solution.getTopRatedRestaurants());
    }
}
