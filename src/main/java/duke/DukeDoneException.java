package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of done is empty.
 */
public class DukeDoneException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! Please specify which task.";
    }
}
