package duke;

import java.util.function.BiFunction;

@FunctionalInterface
public interface ThrowingBiFunction<T, U, R> extends BiFunction<T, U, R> {
    static final String LINEBREAK = "____________________________________________________________";

    @Override
    default R apply(final T t, final U u) {
        try {
            return applyThrows(t, u);
        } catch (final Exception e) {
            System.out.printf("%s\n%s\n%s\n", LINEBREAK, e.getMessage(), LINEBREAK);
        }
        return null;
    }

    R applyThrows(T t, U u) throws Exception;
}
