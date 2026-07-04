package foodorder;

/**
 * Immutable accumulator of rating sum/count for either a restaurant or a
 * restaurant-food pair. Owns the round-half-up averaging rule so it lives
 * in exactly one place instead of being duplicated at every call site.
 */
public final class RatingStats {
    public static final RatingStats EMPTY = new RatingStats(0, 0);

    private final int sum;
    private final int count;

    private RatingStats(int sum, int count) {
        this.sum = sum;
        this.count = count;
    }

    public RatingStats plus(int rating) {
        return new RatingStats(sum + rating, count + 1);
    }

    public double average() {
        if (count == 0) {
            return 0.0;
        }
        double rating = (double) sum / count;
        return (double) ((int) ((rating + 0.05) * 10)) / 10.0;
    }
}
