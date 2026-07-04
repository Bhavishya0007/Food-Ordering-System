package foodorder;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Solution system = new Solution();
        system.init(new ConsoleHelper05());

        system.orderFood("order-0", "restaurant-0", "food-1");
        system.rateOrder("order-0", 3);

        system.orderFood("order-1", "restaurant-2", "food-0");
        system.rateOrder("order-1", 1);

        system.orderFood("order-2", "restaurant-1", "food-0");
        system.rateOrder("order-2", 3);

        system.orderFood("order-3", "restaurant-2", "food-0");
        system.rateOrder("order-3", 5);

        system.orderFood("order-4", "restaurant-0", "food-0");
        system.rateOrder("order-4", 3);

        system.orderFood("order-5", "restaurant-1", "food-0");
        system.rateOrder("order-5", 4);

        system.orderFood("order-6", "restaurant-0", "food-1");
        system.rateOrder("order-6", 2);

        system.orderFood("order-7", "restaurant-0", "food-1");
        system.rateOrder("order-7", 2);

        system.orderFood("order-8", "restaurant-1", "food-0");
        system.rateOrder("order-8", 2);

        system.orderFood("order-9", "restaurant-1", "food-0");
        system.rateOrder("order-9", 4);

        System.out.println("Top restaurants for food-0: " + system.getTopRestaurantsByFood("food-0"));
        System.out.println("Top restaurants for food-1: " + system.getTopRestaurantsByFood("food-1"));
        System.out.println("Top rated restaurants: " + system.getTopRatedRestaurants());
    }
}
