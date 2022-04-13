package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of event is empty.
 */
public class DukeEventException extends DukeException {

    public static final String EVENT_ERROR_MESSAGE = "OOPS!!! The description of an Event cannot be empty.";

    @Override
    public String getMessage() {
        return EVENT_ERROR_MESSAGE;
    }
}
