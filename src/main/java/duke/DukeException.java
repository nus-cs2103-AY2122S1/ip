package duke;

/**
 * Class that represents all exceptions unique to Duke.
 * It inherits from the Java Exception class.
 */
public class DukeException extends Exception {

    /**
     * The constructor of DukeException.
     *
     * @param message message to be displayed alongside the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
