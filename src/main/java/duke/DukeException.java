package duke;

/**
 * DukeException is a class that provides the basic exception that the bot throws
 * when invalid inputs are provided by users.
 *
 * @author meerian
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with the specified description.
     *
     * @param str The exception's description.
     */
    public DukeException(String str) {
        super(str);
    }
}
