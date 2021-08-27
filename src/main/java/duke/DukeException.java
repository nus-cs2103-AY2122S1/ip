package duke;

/**
 * DukeException is the class that represents exceptions in the
 * logic of the script components in Duke.
 */
public class DukeException extends Exception {
    /**
     * Class constructor.
     *
     * @param message the message describing the error that occurred.
     */
    public DukeException(String message) {
        super(message);
    }
}
