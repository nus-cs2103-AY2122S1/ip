package duke.interfaces;

/**
 * A mixin allowing more convenient calls to System.out.
 */
public interface PrintableMixin {
    default void print() {
        System.out.println(this);
    }

    default void print(String s) {
        System.out.println(s);
    }
}
