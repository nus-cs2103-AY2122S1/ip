package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of deadline is empty.
 */
public class DukeDeadlineException extends DukeException {

    public static final String DEADLINE_ERROR_MESSAGE = "OOPS!!! The description of a deadline cannot be empty.";

    @Override
    public String getMessage() {
        return DEADLINE_ERROR_MESSAGE;
    }
}
