package duke;

/**
 * Represents a subtype of DukeException.
 * Thrown when the description of todolist is empty.
 */
public class DukeTodoException extends DukeException {

    public static final String TODO_ERROR_MESSAGE = "OOPS!!! The description of todo cannot be empty!";

    @Override
    public String getMessage() {
        return TODO_ERROR_MESSAGE;
    }
}
