package duke;

/**
 * The class to represent an exception thrown in the program.
 */
public class DukeException extends Exception {

    /**
     * Constructor for Duke exceptions.
     *
     * @param msg message shown when exception is thrown
     */
    public DukeException(String msg) {
        super(msg);
    }
}
