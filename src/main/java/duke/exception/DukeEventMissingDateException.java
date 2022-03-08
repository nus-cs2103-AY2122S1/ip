package duke.exception;

/**
 * This class encapsulates exception due to missing date descriptions.
 *
 * @author Teo Sin Yee
 */
public class DukeEventMissingDateException extends DukeException {
    /**
     * Constructor for DukeEventMissingDateException.
     */
    public DukeEventMissingDateException() {
        super("Error: Invalid use of 'event' command!! @_@\n\t"
                + "To add a new deadline, use 'event <task> /by <event-time>'.");
    }
}