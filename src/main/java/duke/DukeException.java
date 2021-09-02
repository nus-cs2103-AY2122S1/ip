package duke;

/**
 * Represents errors specific to Duke mostly to handle wrong user input.
 */
public class DukeException extends IllegalArgumentException {

    /**
     * Constructor for DukeException.
     *
     * @param message String to print out when Exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
