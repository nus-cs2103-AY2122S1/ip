package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of event is empty.
 */
public class DukeEventException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! The description of an Event cannot be empty.";
    }
}
