package foodorder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RatingStatsTest {

    @Test
    void emptyStatsHaveZeroAverage() {
        assertEquals(0.0, RatingStats.EMPTY.average());
    }

    @Test
    void averageAccumulatesAcrossRatings() {
        RatingStats stats = RatingStats.EMPTY.plus(3).plus(1).plus(5);
        assertEquals(3.0, stats.average());
    }

    @Test
    void plusReturnsNewInstanceAndLeavesOriginalUnchanged() {
        RatingStats original = RatingStats.EMPTY.plus(4);
        RatingStats updated = original.plus(2);

        assertEquals(4.0, original.average());
        assertEquals(3.0, updated.average());
    }

    @Test
    void averageRoundsHalfUpNotHalfEven() {
        // 13 / 4 = 3.25. Java/Python's default round-half-even would give 3.2,
        // but the spec's examples (4.05 -> 4.1, 4.15 -> 4.2) require round-half-up: 3.3.
        RatingStats stats = RatingStats.EMPTY.plus(3).plus(4).plus(2).plus(4);
        assertEquals(3.3, stats.average());
    }

    @Test
    void averageTruncatesNonTerminatingDecimals() {
        // 7 / 3 = 2.333... -> 2.3
        RatingStats stats = RatingStats.EMPTY.plus(3).plus(2).plus(2);
        assertEquals(2.3, stats.average());
    }
}
