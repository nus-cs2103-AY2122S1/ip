package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of todolist is empty.
 */
public class DukeTodoException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! The description of todo cannot be empty.";
    }
}
