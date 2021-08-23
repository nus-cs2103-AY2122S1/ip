package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of deadline is empty.
 */
public class DukeDeadlineException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}
