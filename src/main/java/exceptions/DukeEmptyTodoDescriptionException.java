package exceptions;

public class DukeEmptyTodoDescriptionException extends DukeException {

    public DukeEmptyTodoDescriptionException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
