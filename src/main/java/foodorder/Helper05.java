package foodorder;

/**
 * Logging contract expected by the grading platform. Injected into
 * {@link Solution} via {@link Solution#init(Helper05)} rather than
 * constructed internally, so the implementation can be swapped freely.
 */
public interface Helper05 {
    void log(String message);
}
