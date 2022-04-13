package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of done is empty.
 */
public class DukeDoneException extends DukeException {

    public static final String DONE_ERROR_MESSAGE = "OOPS!!! Please specify which task.";

    @Override
    public String getMessage() {
        return DONE_ERROR_MESSAGE;
    }
}
