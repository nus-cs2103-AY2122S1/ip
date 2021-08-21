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
     * Shortened version of System.out.println
     * @param s String to be printed.
     */
    default void print(String s) {
        System.out.println(s);
    }
}
