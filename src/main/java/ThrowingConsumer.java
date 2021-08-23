import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {
    static final String LINEBREAK = "____________________________________________________________";

    @Override
    default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final Exception e) {
            System.out.printf("%s\n%s\n%s\n", LINEBREAK, e.getMessage(), LINEBREAK);
        }
    }

    void acceptThrows(T elem) throws Exception;

}