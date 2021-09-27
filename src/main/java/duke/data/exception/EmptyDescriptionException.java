package duke.data.exception;

/**
 * Describes exception caused by empty description
 */
public class EmptyDescriptionException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! The description cannot be empty.";
    }
}
