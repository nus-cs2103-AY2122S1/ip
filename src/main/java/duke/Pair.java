package duke;

/**
 * This class represents a {@code Pair}. A {@code Pair} is made up of 2 items.
 * It is mostly used when there is a need to return 2 items.
 *
 * @author Elizabeth Chow
 */
public class Pair<S, T> {
    private final S first;
    private final T second;

    /**
     * Constructs a new Pair with the given first and second arguments.
     *
     * @param first  First generic item.
     * @param second Second generic item.
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first item.
     *
     * @return The first item.
     */
    public S getFirst() {
        return first;
    }

    /**
     * Returns the second item.
     *
     * @return The second item.
     */
    public T getSecond() {
        return second;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair<?, ?> otherPair = (Pair<?, ?>) other;
            return otherPair.first.equals(this.first) && this.second.equals(otherPair.second);
        }
        return false;
    }
}
