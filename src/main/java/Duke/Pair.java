package duke;

/**
 * This class represents a pair of values.
 *
 * @param <S> The type of the first value.
 * @param <T> The type of the second value.
 */
public class Pair<S, T> {
    private final S first;
    private final T second;

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first value of the pair.
     *
     * @return The pair's first value.
     */
    public S getFirst() {
        return first;
    }

    /**
     * Gets the second value of the pair.
     *
     * @return The pair's second value.
     */
    public T getSecond() {
        return second;
    }
}
