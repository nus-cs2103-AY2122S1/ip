package duke.interfaces;

/**
 * A mixin allowing more convenient calls to System.out.
 */
public interface PrintableMixin {
    /**
     * Calls print on self.
     */
    default void print() {
        System.out.println(this);
    }

    /**
     * Prints to console.
     * @param s String to be printed.
     */
    default void print(String s) {
        System.out.println(s);
    }
}
